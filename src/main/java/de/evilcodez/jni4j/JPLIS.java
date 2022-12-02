package de.evilcodez.jni4j;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import de.evilcodez.jni4j.structs.JPLISAgent;
import de.evilcodez.jni4j.structs.JPLISEnvironment;
import de.evilcodez.jni4j.structs.JvmtiCapabilities;
import de.evilcodez.jni4j.structs.JvmtiEventCallbacks;

import java.lang.instrument.Instrumentation;
import java.util.function.Function;

/**
 * NOT WORKING YET
 */
public class JPLIS implements AutoCloseable {

    private static final Pointer JPLIS_CURRENTLY_INSIDE_TOKEN = new Pointer(0x7EFFC0BBL);
    private static final Pointer JPLIS_CURRENTLY_OUTSIDE_TOKEN = new Pointer(0L);

    private final ThreadLocal<Boolean> currentlyInside = ThreadLocal.withInitial(() -> false);

    private final JavaVM javaVM;
    private final JVMTI jvmti;
    private JVMTI retranformJvmti;
    private final JPLISAgent agent;
    private Instrumentation instrumentation;
    private boolean closed;

    public JPLIS(Pointer javaVM, boolean canRedefineClasses, boolean canRetransformClasses, boolean canSetNativeMethodPrefix, boolean canMaintainOriginalMethodOrder) {
        this.javaVM = new JavaVM(javaVM);
        this.jvmti = JVMTI.getThreadEnv(this.javaVM, JVM.JVMTI_VERSION_1_1);
        final PointerByReference agentPtr = new PointerByReference();
        this.jvmti.Allocate(Native.getNativeSize(JPLISAgent.class), agentPtr);
        this.agent = JPLISAgent.createWithoutRead(agentPtr.getValue());
        try {
            this.initialize(canRedefineClasses, canRetransformClasses, canSetNativeMethodPrefix, canMaintainOriginalMethodOrder);
        } catch (Throwable t) {
            this.close();
            throw t;
        }
    }

    private void initialize(boolean canRedefineClasses, boolean canRetransformClasses, boolean canSetNativeMethodPrefix, boolean canMaintainOriginalMethodOrder) {
        this.agent.mJVM = this.javaVM.getHandle();
        this.agent.mNormalEnvironment = new JPLISEnvironment();
        this.agent.mNormalEnvironment.mJVMTIEnv = this.jvmti.getHandle();
        this.agent.mNormalEnvironment.mAgent = this.agent.getPointer();
        this.agent.mNormalEnvironment.mIsRetransformer = false;
        this.agent.mRetransformEnvironment = new JPLISEnvironment();
        this.agent.mRetransformEnvironment.mJVMTIEnv = null;
        this.agent.mRetransformEnvironment.mAgent = this.agent.getPointer();
        this.agent.mRetransformEnvironment.mIsRetransformer = false;
        this.agent.mAgentmainCaller = null;
        this.agent.mInstrumentationImpl = null;
        this.agent.mPremainCaller = null;
        this.agent.mTransform = null;
        this.agent.mRedefineAvailable = false;
        this.agent.mRedefineAdded = false;
        this.agent.mNativeMethodPrefixAvailable = false;
        this.agent.mNativeMethodPrefixAdded = false;
        this.agent.mAgentClassName = null;
        this.agent.mOptionsString = null;
        // TODO add const char* mJarfile

        final Pointer normalEnvPtr = this.agent.getPointer().share(Native.POINTER_SIZE); // Pointer of mNormalEnvironment
        this.err(this.jvmti.SetEnvironmentLocalStorage(normalEnvPtr));

        final JvmtiCapabilities capabilities = new JvmtiCapabilities();
        this.err(this.jvmti.GetPotentialCapabilities(capabilities));

        if (capabilities.get(JvmtiCapabilities.CAN_REDEFINE_CLASSES)) this.agent.mRedefineAvailable = true;
        if (capabilities.get(JvmtiCapabilities.CAN_SET_NATIVE_METHOD_PREFIX)) this.agent.mNativeMethodPrefixAvailable = true;

        final IntByReference phase = new IntByReference();
        this.err(this.jvmti.GetPhase(phase));
        if (phase.getValue() != JVM.JVMTI_PHASE_LIVE) throw new IllegalStateException("JPLIS can only be used in live phase");

        this.doCapabilities(this.jvmti, c -> {
            c.set(JvmtiCapabilities.CAN_GENERATE_ALL_CLASS_HOOK_EVENTS, true);
            return true;
        });
        if (canRedefineClasses) {
            this.doCapabilities(this.jvmti, c -> {
                if (this.agent.mRedefineAvailable && !this.agent.mRedefineAdded) {
                    c.set(JvmtiCapabilities.CAN_REDEFINE_CLASSES, true);
                    this.agent.mRedefineAdded = true;
                    return true;
                }
                return false;
            });
        }
        if (canRetransformClasses) {
            this.enableRetransformableEnv();
        }
        if (canSetNativeMethodPrefix) {
            if (this.agent.mNativeMethodPrefixAvailable && !this.agent.mNativeMethodPrefixAdded) {
                this.doCapabilities(this.jvmti, c -> {
                    c.set(JvmtiCapabilities.CAN_SET_NATIVE_METHOD_PREFIX, true);
                    return true;
                });
                if (this.agent.mRetransformEnvironment.mJVMTIEnv != null) {
                    this.doCapabilities(this.retranformJvmti, c -> {
                        c.set(JvmtiCapabilities.CAN_SET_NATIVE_METHOD_PREFIX, true);
                        return true;
                    });
                }
                this.agent.mNativeMethodPrefixAdded = true;
            }
        }
        if (canMaintainOriginalMethodOrder) {
            this.doCapabilities(this.jvmti, c -> {
                c.set(JvmtiCapabilities.CAN_MAINTAIN_ORIGINAL_METHOD_ORDER, true);
                return true;
            });
        }
        this.agent.write();

        this.createInstrumentationImpl();

        this.setLivePhaseEventHandlers();

        this.agent.write();
    }

    private void enableRetransformableEnv() {
        final JVMTI jvmti = JVMTI.getThreadEnv(this.javaVM, JVM.JVMTI_VERSION_1_1);
        this.retranformJvmti = jvmti;
        try {
            final JvmtiCapabilities desiredCapabilities = new JvmtiCapabilities();
            this.err(jvmti.GetCapabilities(desiredCapabilities));
            desiredCapabilities.set(JvmtiCapabilities.CAN_GENERATE_ALL_CLASS_HOOK_EVENTS, true);
            desiredCapabilities.set(JvmtiCapabilities.CAN_RETRANSFORM_CLASSES, true);
            if (this.agent.mNativeMethodPrefixAdded) {
                desiredCapabilities.set(JvmtiCapabilities.CAN_SET_NATIVE_METHOD_PREFIX, true);
            }
            this.err(jvmti.AddCapabilities(desiredCapabilities));
            final JvmtiEventCallbacks callbacks = new JvmtiEventCallbacks();
            callbacks.ClassFileLoadHook = this::eventHandlerClassFileLoadHook;
            this.err(jvmti.SetEventCallbacks(callbacks, callbacks.size()));
            this.agent.mRetransformEnvironment.mJVMTIEnv = jvmti.getHandle();
            this.agent.mRetransformEnvironment.mIsRetransformer = true;
            final long offset = Native.POINTER_SIZE + this.agent.mNormalEnvironment.size();
            this.err(jvmti.SetEnvironmentLocalStorage(this.agent.getPointer().share(offset))); // Pointer of mRetransformEnvironment
        } catch (Throwable e) {
            this.err(jvmti.DisposeEnvironment());
            throw e;
        }
    }

    private void createInstrumentationImpl() {
        final JNIEnv env = JNIEnv.getThreadEnv(this.javaVM, JVM.JNI_VERSION_1_2);
        final Pointer instClass = env.NewGlobalRef(env.FindClass("sun/instrument/InstrumentationImpl"));
        try {
            final Pointer constructor = env.GetMethodID(instClass, "<init>", "(JZZ)V");
            try (final Memory memory = new Memory(3 * 8)) {
                memory.setPointer(0L, this.agent.getPointer());
                memory.setInt(8L, this.agent.mRedefineAdded ? 1 : 0);
                memory.setInt(16L, this.agent.mNativeMethodPrefixAdded ? 1 : 0);
                final Pointer impl = env.NewGlobalRef(env.NewObjectA(instClass, constructor, memory));
                try {
                    final Pointer premainCallerMethodID = env.GetMethodID(instClass, "loadClassAndCallPremain", "(Ljava/lang/String;Ljava/lang/String;)V");
                    final Pointer agentmainCallerMethodID = env.GetMethodID(instClass, "loadClassAndCallAgentmain", "(Ljava/lang/String;Ljava/lang/String;)V");
                    final Pointer transformMethodID = env.GetMethodID(instClass, "transform", "(Ljava/lang/ClassLoader;Ljava/lang/String;Ljava/lang/Class;Ljava/security/ProtectionDomain;[BZ)[B");
                    this.agent.mInstrumentationImpl = env.NewGlobalRef(impl);
                    this.agent.mPremainCaller = premainCallerMethodID;
                    this.agent.mAgentmainCaller = agentmainCallerMethodID;
                    this.agent.mTransform = transformMethodID;
                    this.instrumentation = JNIUtils.unwrapHandle(env, impl);
                } finally {
                    env.DeleteGlobalRef(impl);
                }
            }
        } finally {
            env.DeleteGlobalRef(instClass);
        }
    }

    private void setLivePhaseEventHandlers() {
        final JVMTI jvmti = this.jvmti;
        final JvmtiEventCallbacks callbacks = new JvmtiEventCallbacks();
        callbacks.ClassFileLoadHook = this::eventHandlerClassFileLoadHook;
        this.err(jvmti.SetEventCallbacks(callbacks, callbacks.size()));
        this.err(jvmti.SetEventNotificationMode(JVM.JVMTI_ENABLE, JVM.JVMTI_EVENT_CLASS_FILE_LOAD_HOOK, null));
    }

    private void eventHandlerClassFileLoadHook(Pointer jvmtiEnv, Pointer jniEnv, Pointer classBeingRedefined, Pointer loader, String name, Pointer protectionDomain, int classDataLen, Pointer classData, IntByReference newClassDataLen, PointerByReference newClassData) {
        System.out.println("ClassFileLoadHook: " + name);
        if (currentlyInside.get()) return;
        currentlyInside.set(true);
        System.out.println("PUSH Transforming " + name + "...");
        final JVMTI jvmti = new JVMTI(jvmtiEnv);
        final JNIEnv jni = new JNIEnv(jniEnv);
        try {
            final PointerByReference ptr = new PointerByReference();
            this.err(jvmti.GetEnvironmentLocalStorage(ptr));
            if (ptr.getValue() != null) {
                final JPLISEnvironment env = new JPLISEnvironment(ptr.getValue());
                transformClassFile(
                        jni,
                        classBeingRedefined,
                        loader,
                        name,
                        protectionDomain,
                        classDataLen,
                        classData,
                        newClassDataLen,
                        newClassData,
                        env.mIsRetransformer
                );
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            currentlyInside.set(false);
        }
        System.out.println("POP Transforming " + name + "...");
    }

    private void transformClassFile(JNIEnv jni, Pointer classBeingRedefined, Pointer loader, String name, Pointer protectionDomain, int classDataLen, Pointer classData, IntByReference newClassDataLen, PointerByReference newClassData, boolean isRetransformer) {
//        final boolean shouldRun = tryToAcquireReentrancyToken(jvmti, null);
//        if (!shouldRun) return;
        final Pointer classNameStringObject = name == null ? null : jni.NewGlobalRef(jni.NewStringUTF(name));
        final Pointer classFileBufferObject = jni.NewGlobalRef(jni.NewByteArray(classDataLen));
        try {
            jni.SetByteArrayRegion(classFileBufferObject, 0, classDataLen, classData);

            try (final Memory mem = new Memory(48L)) {
                mem.setPointer(0L, loader);
                mem.setPointer(8L, classNameStringObject);
                mem.setPointer(16L, classBeingRedefined);
                mem.setPointer(24L, protectionDomain);
                mem.setPointer(32L, classFileBufferObject);
                mem.setInt(40L, isRetransformer ? 1 : 0);
                final Pointer transformedBufferObject = jni.CallObjectMethodA(
                        this.agent.mInstrumentationImpl,
                        this.agent.mTransform,
                        mem
                );
                if (transformedBufferObject != null) {
                    final Pointer buf = jni.NewGlobalRef(transformedBufferObject);
                    try {
                        final int bufferSize = jni.GetArrayLength(buf);
                        final PointerByReference ptr = new PointerByReference();
                        this.err(jvmti.Allocate(bufferSize, ptr));
                        final Pointer resultBuffer = ptr.getValue();
                        try {
                            jni.GetByteArrayRegion(buf, 0, bufferSize, resultBuffer);
                            newClassDataLen.setValue(bufferSize);
                            newClassData.setPointer(resultBuffer);
                        } catch (Throwable e) {
                            jvmti.Deallocate(resultBuffer);
                            throw e;
                        }
                    } finally {
                        jni.DeleteGlobalRef(buf);
                    }
                }
            }
        } finally {
            jni.DeleteGlobalRef(classFileBufferObject);
            if (classNameStringObject != null) jni.DeleteGlobalRef(classNameStringObject);
        }
//        releaseReentrancyToken(jvmti, null);
    }

    private boolean tryToAcquireReentrancyToken(JVMTI jvmti, Pointer thread) {
        final PointerByReference storedValue = new PointerByReference();
        this.err(jvmti.GetThreadLocalStorage(thread, storedValue));
        final Pointer value = storedValue.getValue();
        if (value != null && value.equals(JPLIS_CURRENTLY_INSIDE_TOKEN)) return false;
        assertTLSValue(jvmti, thread, JPLIS_CURRENTLY_OUTSIDE_TOKEN);
        int error = jvmti.SetThreadLocalStorage(thread, JPLIS_CURRENTLY_INSIDE_TOKEN);
        assertTLSValue(jvmti, thread, JPLIS_CURRENTLY_INSIDE_TOKEN);
        return error == JVM.JVMTI_ERROR_NONE;
    }

    private void releaseReentrancyToken(JVMTI jvmti, Pointer thread) {
        assertTLSValue(jvmti, thread, JPLIS_CURRENTLY_INSIDE_TOKEN);
        this.err(jvmti.SetThreadLocalStorage(thread, JPLIS_CURRENTLY_OUTSIDE_TOKEN));
    }

    private void assertTLSValue(JVMTI jvmti, Pointer thread, Pointer expected) {
        final PointerByReference storedValue = new PointerByReference();
        storedValue.setPointer(new Pointer(0x99999999L));
        this.err(jvmti.GetThreadLocalStorage(thread, storedValue));
        System.out.println("TLS value: " + storedValue.getValue() + ", expected: " + expected);
        if (!storedValue.getValue().equals(expected)) {
            throw new IllegalStateException("TLS value is " + storedValue.getValue() + ", expected " + expected);
        }
    }

    @Override
    public void close() {
        if (this.closed) return;
        this.closed = true;
        if (this.retranformJvmti != null) this.retranformJvmti.DisposeEnvironment();
        this.jvmti.Deallocate(this.agent.getPointer());
        this.jvmti.DisposeEnvironment();
    }

    public JPLISAgent getAgent() {
        return agent;
    }

    public Instrumentation getInstrumentation() {
        return instrumentation;
    }

    private void doCapabilities(JVMTI jvmti, Function<JvmtiCapabilities, Boolean> func) {
        final JvmtiCapabilities capabilities = new JvmtiCapabilities();
        this.err(jvmti.GetCapabilities(capabilities));
        if (func.apply(capabilities)) {
            this.err(jvmti.AddCapabilities(capabilities));
        }
    }

    private void err(int err) {
        if (err != JVM.JVMTI_ERROR_NONE) {
            final PointerByReference ptr = new PointerByReference();
            err(this.jvmti.GetErrorName(err, ptr));
            final Pointer namePtr = ptr.getValue();
            throw new RuntimeException(namePtr == null ? "JVMTI Error " + err : "JVMTI Error (" + err + "): " + namePtr.getString(0L));
        }
    }
}

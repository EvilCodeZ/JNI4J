package de.evilcodez.jni4j.jplis;

import com.sun.jna.Function;
import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;
import de.evilcodez.jni4j.*;
import de.evilcodez.jni4j.structs.JPLISAgent;
import de.evilcodez.jni4j.structs.JvmtiCapabilities;

import java.lang.instrument.Instrumentation;

public class JPLIS {

    private static final String TRANSFORM_SIG_8 = "(Ljava/lang/ClassLoader;Ljava/lang/String;Ljava/lang/Class;Ljava/security/ProtectionDomain;[BZ)[B";
    private static final String TRANSFORM_SIG_9 = "(Ljava/lang/Module;Ljava/lang/ClassLoader;Ljava/lang/String;Ljava/lang/Class;Ljava/security/ProtectionDomain;[BZ)[B";
    private static Pointer FN_createNewJPLISAgent;
    private static Pointer FN_setLivePhaseEventHandlers;
    private static Pointer FN_retransformableEnvironment;

    private final JavaVM javaVM;
    private final JNIEnv jniEnv;
    private JPLISAgent agent;
    private Instrumentation instrumentation;

    public JPLIS(Pointer javaVM, boolean canRedefineClasses, boolean canRetransformClasses, boolean canSetNativeMethodPrefix, boolean canMaintainOriginalMethodOrder) {
        this.javaVM = new JavaVM(javaVM);
        this.jniEnv = JNIEnv.getThreadEnv(this.javaVM, JVM.JNI_VERSION_1_2);
        try {
            this.initialize(canRedefineClasses, canRetransformClasses, canSetNativeMethodPrefix, canMaintainOriginalMethodOrder);
        } catch (Throwable t) {
            // TODO: Unload everything when unloading is implemented
            throw t;
        }
    }

    private void initialize(boolean canRedefineClasses, boolean canRetransformClasses, boolean canSetNativeMethodPrefix, boolean canMaintainOriginalMethodOrder) {
        if (FN_createNewJPLISAgent == null) throw new NoSuchMethodError("Could not find JPLIS agent creation function");
        if (FN_setLivePhaseEventHandlers == null) throw new NoSuchMethodError("Could not find JPLIS live phase event handler function");
        if (FN_retransformableEnvironment == null && canRetransformClasses) throw new NoSuchMethodError("Could not find JPLIS retransformable environment function");
        final Function createNewJPLISAgent = Function.getFunction(FN_createNewJPLISAgent);
        final Function setLivePhaseEventHandlers = Function.getFunction(FN_setLivePhaseEventHandlers);
        final Function retransformableEnvironmnet = canRetransformClasses ? Function.getFunction(FN_retransformableEnvironment) : null;

        final PointerByReference agentPtr = new PointerByReference();
        int err = createNewJPLISAgent.invokeInt(new Object[] {this.javaVM.getHandle(), agentPtr});
        if (err != 0) throw new RuntimeException("Could not create JPLIS agent: Error " + err);
        this.agent = new JPLISAgent(agentPtr.getValue());

        final JVMTI jvmti = new JVMTI(this.agent.mNormalEnvironment.mJVMTIEnv);
        this.doCapabilities(jvmti, c -> {
            c.set(JvmtiCapabilities.CAN_GENERATE_ALL_CLASS_HOOK_EVENTS, true);
            return true;
        });
        if (canRedefineClasses) {
            this.doCapabilities(jvmti, c -> {
                if (this.agent.mRedefineAvailable && !this.agent.mRedefineAdded) {
                    c.set(JvmtiCapabilities.CAN_REDEFINE_CLASSES, true);
                    this.agent.mRedefineAdded = true;
                    return true;
                }
                return false;
            });
        }
        if (canRetransformClasses) {
            retransformableEnvironmnet.invokePointer(new Object[]{this.agent.getPointer()});
            this.agent.read(); // Read again because retransformableEnvironmnet() changes the contents of the JPLISAgent struct
        }
        if (canSetNativeMethodPrefix) {
            if (this.agent.mNativeMethodPrefixAvailable && !this.agent.mNativeMethodPrefixAdded) {
                this.doCapabilities(jvmti, c -> {
                    c.set(JvmtiCapabilities.CAN_SET_NATIVE_METHOD_PREFIX, true);
                    return true;
                });
                if (this.agent.mRetransformEnvironment.mJVMTIEnv != null) {
                    this.doCapabilities(new JVMTI(this.agent.mRetransformEnvironment.mJVMTIEnv), c -> {
                        c.set(JvmtiCapabilities.CAN_SET_NATIVE_METHOD_PREFIX, true);
                        return true;
                    });
                }
                this.agent.mNativeMethodPrefixAdded = true;
            }
        }
        if (canMaintainOriginalMethodOrder) {
            this.doCapabilities(jvmti, c -> {
                c.set(JvmtiCapabilities.CAN_MAINTAIN_ORIGINAL_METHOD_ORDER, true);
                return true;
            });
        }
        this.agent.write();

        this.createInstrumentationImpl();
        this.agent.read();
        this.instrumentation = JNIUtils.unwrapHandle(this.jniEnv, this.agent.mInstrumentationImpl);
        boolean success = setLivePhaseEventHandlers.invokeInt(new Object[] {this.agent.getPointer()}) != 0;
        if (!success) throw new RuntimeException("Could not set JPLIS live phase event handlers");
    }

    private void createInstrumentationImpl() {
        final JNIEnv env = this.jniEnv;
        final Pointer instClass = env.NewGlobalRef(env.FindClass("sun/instrument/InstrumentationImpl"));
        try {
            final Pointer constructor = env.GetMethodID(instClass, "<init>", "(JZZ)V");
            try (final Memory memory = new Memory(3 * 8)) {
                memory.setPointer(0L, this.agent.getPointer());
                memory.setInt(8L, this.agent.mRedefineAdded ? 1 : 0);
                memory.setInt(16L, this.agent.mNativeMethodPrefixAdded ? 1 : 0);
                final Pointer impl = env.NewGlobalRef(env.NewObjectA(instClass, constructor, memory));
                try {
                    final double classVersion = Double.parseDouble(System.getProperty("java.class.version", "52.0"));
                    final Pointer premainCallerMethodID = env.GetMethodID(instClass, "loadClassAndCallPremain", "(Ljava/lang/String;Ljava/lang/String;)V");
                    final Pointer agentmainCallerMethodID = env.GetMethodID(instClass, "loadClassAndCallAgentmain", "(Ljava/lang/String;Ljava/lang/String;)V");
                    final Pointer transformMethodID = env.GetMethodID(instClass, "transform", classVersion >= 53.0 ? TRANSFORM_SIG_9 : TRANSFORM_SIG_8);
                    this.agent.mInstrumentationImpl = env.NewGlobalRef(impl);
                    this.agent.mPremainCaller = premainCallerMethodID;
                    this.agent.mAgentmainCaller = agentmainCallerMethodID;
                    this.agent.mTransform = transformMethodID;
                    this.instrumentation = JNIUtils.unwrapHandle(env, impl);
                    this.agent.write();
                } finally {
                    env.DeleteGlobalRef(impl);
                }
            }
        } finally {
            env.DeleteGlobalRef(instClass);
        }
    }

    private void doCapabilities(JVMTI jvmti, java.util.function.Function<JvmtiCapabilities, Boolean> func) {
        final JvmtiCapabilities capabilities = new JvmtiCapabilities();
        this.err(jvmti, jvmti.GetCapabilities(capabilities));
        if (func.apply(capabilities)) {
            this.err(jvmti, jvmti.AddCapabilities(capabilities));
        }
    }

    private void err(JVMTI jvmti, int err) {
        if (err != JVM.JVMTI_ERROR_NONE) {
            final PointerByReference ptr = new PointerByReference();
            err(jvmti, jvmti.GetErrorName(err, ptr));
            final Pointer namePtr = ptr.getValue();
            throw new RuntimeException(namePtr == null ? "JVMTI Error " + err : "JVMTI Error (" + err + "): " + namePtr.getString(0L));
        }
    }

    public JPLISAgent getAgent() {
        return agent;
    }

    public Instrumentation getInstrumentation() {
        return instrumentation;
    }

    public static boolean isSupported() {
        return FN_createNewJPLISAgent != null && FN_setLivePhaseEventHandlers != null && FN_retransformableEnvironment != null;
    }

    public static void initialize() throws Exception {
        if (isSupported()) return;
        Class.forName("sun.instrument.InstrumentationImpl"); // Loads instrument library for us, so pattern scanning works

        FN_createNewJPLISAgent = PatternScanner.findSymbolOrAnyPattern(
                "instrument",
                "createNewJPLISAgent",
                // Oracle JRE 8u51 (aka Minecraft 1.8 JRE)
                "4C 8B DC 49 89 5B 10 49 89 73 18 57 48 83 EC 20 48 83 22 00 48 8B 01 49 83 63 ? ? 48 8B F2 49 8D 53 08 41 B8 ? ? ? ? 48 8B D9 FF 50 30 85 C0 74 07 BB ? ? ? ? EB 70",
                // Oracle JDK 8u341
                "4C 8B DC 49 89 5B 10 49 89 73 18 57 48 83 EC 20",
                // OpenJDK 11.0.6 debian
                "55 BA ? ? ? ? 48 89 E5 41 56 41 55 41 BD ? ? ? ? 41 54 49 89 FC 53 48 89 F3 48 83 EC 10 48 C7 06 ? ? ? ? 48 8B 07 48 8D 75 D8 48 C7 45 ? ? ? ? ? FF 50 30 85 C0 74 13",
                // AdoptOpenJDK 1.8.0_292-b10 debian
                "55 BA ? ? ? ? 48 89 E5 41 56 41 55 41 54 53 49 89 F5 48 89 FB 41 BC ? ? ? ? 48 83 EC 10 48 C7 06 ? ? ? ? 48 8B 07 48 C7 45 ? ? ? ? ? 48 8D 75 D8 FF 50 30 85 C0 74 13",
                // OpenJDK 11.0.17+8-post-Ubuntu-1ubuntu222.04
                "F3 0F 1E FA 55 BA ? ? ? ? 48 89 E5 41 56 41 55 41 BD ? ? ? ? 41 54 49 89 FC 53 48 89 F3 48 83 EC 10 48 C7 06 ? ? ? ? 48 8B 07 48 8D 75 D8 48 C7 45 ? ? ? ? ? FF 50 30 85 C0 74 17",
                // OpenJDK 1.8.0_352-8u352-ga-1~22.04-b08 Ubuntu 22.04 WSL
                "F3 0F 1E FA 55 BA ? ? ? ? 48 89 E5 41 56 41 55 41 BD ? ? ? ? 41 54 49 89 FC 53 48 89 F3 48 83 EC 10 48 C7 06 ? ? ? ? 48 8B 07 48 8D 75 D8 48 C7 45 ? ? ? ? ? FF 50 30"
        );
        FN_setLivePhaseEventHandlers = PatternScanner.findSymbolOrAnyPattern(
                "instrument",
                "setLivePhaseEventHandlers",
                // Oracle JDK 8u341
                "48 89 5C 24 ? 48 89 74 24 ? 57 48 81 EC ? ? ? ? 48 8B 79 08 BB ? ? ? ? 44 8B C3 48 8D 4C 24 ? 33 D2 E8 ? ? ? ?",
                // Oracle JRE 8u51-b16 (aka Minecraft 1.8 JRE)
                "48 89 5C 24 ? 48 89 74 24 ? 57 48 81 EC ? ? ? ? 48 8B 71 08 BB ? ? ? ? 48 8D 4C 24 ? 44 8B C3 33 D2 E8 ? ? ? ?",
                // GraalVM CE 22.3.0 (build 19.0.1+10-jvmci-22.3-b08)
                "48 89 5C 24 ? 57 48 81 EC ? ? ? ? 48 8B 79 08 BB ? ? ? ? 44 8B C3 48 8D 4C 24 ? 33 D2 E8 ? ? ? ? 48 8D 05 ? ? ? ? 44 8B C3 48 89 44 24 ? 48 8D 54 24 ?",
                // OpenJDK 11.0.6 debian
                "55 31 C0 B9 ? ? ? ? BA ? ? ? ? 48 89 E5 41 54 53 48 8D B5 ? ? ? ? 48 81 EC ? ? ? ? 4C 8B 67 08 48 89 F7 F3 48 AB 48 8D 05 ? ? ? ? 4C 89 E7 48 89 85 ? ? ? ? 49 8B 04 24 FF 90 ? ? ? ?",
                // Oracle JDK 19.0.1 debian
                "55 31 C0 B9 ? ? ? ? BA ? ? ? ? 48 89 E5 41 56 41 55 48 8D B5 ? ? ? ? 41 54 53 48 81 EC ? ? ? ? 4C 8B 67 08 48 89 F7 F3 48 AB 48 8D 05 ? ? ? ?",
                // AdoptOpenJDK 1.8.0_292-b10 debian
                "55 31 C0 B9 ? ? ? ? BA ? ? ? ? 48 89 E5 41 54 53 48 8D B5 ? ? ? ? 48 81 EC ? ? ? ? 48 8B 5F 08 48 89 F7 F3 48 AB 48 8D 05 ? ? ? ?",
                // OpenJDK 1.8.0_352-8u352-ga-1~22.04-b08 Ubuntu 22.04 WSL
                "F3 0F 1E FA 55 31 C0 B9 ? ? ? ? BA ? ? ? ? 48 89 E5 41 56 41 55 48 8D B5 ? ? ? ? 41 54 53 48 81 EC ? ? ? ? 4C 8B 67 08 48 89 F7 F3 48 AB"
        );
        FN_retransformableEnvironment = PatternScanner.findSymbolOrAnyPattern(
                "instrument",
                "retransformableEnvironment",
                // Oracle JDK 8u341
                "48 8B C4 48 89 58 10 48 89 70 18 48 89 78 20 55 48 8D 68 88 48 81 EC ? ? ? ? 48 8B 05 ? ? ? ? 48 33 C4 48 89 45 60 48 83 64 24 ? ? 48 8D 71 20 48 8B 06 48 8B F9 48 85 C0",
                // JBR-17.0.5+1-653.14-nomod
                "48 89 5C 24 ? 48 89 74 24 ? 48 89 7C 24 ? 55 48 8D 6C 24 ? 48 81 EC ? ? ? ? 48 8B 05 ? ? ? ? 48 33 C4 48 89 45 70 48 83 64 24 ? ? 48 8D 71 20 48 8B 06 48 8B F9 48 85 C0 0F 85 ? ? ? ?",
                // Oracle JRE 8u51-b16 (aka Minecraft 1.8 JRE)
                "48 8B C4 48 89 58 10 48 89 70 18 48 89 78 20 55 48 8D 68 88 48 81 EC ? ? ? ? 48 8B 05 ? ? ? ? 48 33 C4 48 89 45 60 48 8B 41 20 48 83 64 24 ? ? 48 8B F9 48 85 C0 0F 85 ? ? ? ?",
                // GraalVM CE 22.3.0 (build 19.0.1+10-jvmci-22.3-b08)
                "48 8B C4 48 89 58 10 48 89 70 18 48 89 78 20 55 48 8D A8 ? ? ? ? 48 81 EC ? ? ? ? 48 8B 05 ? ? ? ? 48 33 C4 48 89 85 ? ? ? ? 48 83 64 24 ? ? 48 8D 71 20 48 8B 06 48 8B F9 48 85 C0 0F 85 ? ? ? ? 48 8B 09 48 8D 54 24 ? 41 B8 ? ? ? ? 48 8B 01 FF 50 30 85 C0 0F 85 ? ? ? ? 48 8B 4C 24 ?",
                // OpenJDK 11.0.6 debian
                "55 48 89 E5 41 55 41 54 53 48 81 EC ? ? ? ? 4C 8B 67 20 48 C7 85 ? ? ? ? ? ? ? ? 4D 85 E4 74 14 48 81 C4 ? ? ? ?",
                // Oracle JDK 19.0.1 debian
                "55 48 89 E5 41 57 41 56 41 55 41 54 53 48 81 EC ? ? ? ? 4C 8B 67 20 48 C7 85 ? ? ? ? ? ? ? ? 4D 85 E4 74 18 48 81 C4 ? ? ? ?",
                // AdoptOpenJDK 1.8.0_292-b10 debian
                "55 48 89 E5 41 55 41 54 53 48 81 EC ? ? ? ? 4C 8B 67 20 48 C7 85 ? ? ? ? ? ? ? ? 4D 85 E4 0F 85 ? ? ? ? 48 89 FB 48 8B 3F 48 8D B5 ? ? ? ? BA ? ? ? ? 48 8B 07 FF 50 30 85 C0 0F 85 ? ? ? ?",
                // OpenJDK 11.0.17+8-post-Ubuntu-1ubuntu222.04
                "F3 0F 1E FA 55 48 89 E5 41 57 41 56 41 55 41 54 53 48 81 EC ? ? ? ? 4C 8B 67 20 48 C7 85 ? ? ? ? ? ? ? ? 4D 85 E4 74 1C",
                // OpenJDK 1.8.0_352-8u352-ga-1~22.04-b08 Ubuntu 22.04 WSL
                "F3 0F 1E FA 55 48 89 E5 41 57 41 56 41 55 41 54 53 48 81 EC ? ? ? ? 4C 8B 67 20 48 C7 85 ? ? ? ? ? ? ? ? 4D 85 E4 74 1C"
        );
    }
}

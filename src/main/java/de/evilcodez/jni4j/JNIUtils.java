package de.evilcodez.jni4j;

import com.sun.jna.*;
import com.sun.jna.ptr.PointerByReference;
import de.evilcodez.jni4j.structs.JNINativeMethod;

import java.lang.annotation.*;
import java.lang.reflect.Method;

public final class JNIUtils {

    private static final ThreadLocal<Object> LOCAL = new ThreadLocal<>();
    private static final ThreadLocal<Pointer> LOCAL_DUMMY_FUNC = new ThreadLocal<>();
    private static Callback dummyCallback;

    /**
     * Retrieves the JNI/JVMTI environment for the current thread.
     * @param vm The JavaVM instance used to get the environment for.
     * @param version The version of the environment to get. {@link JVM} contains all version constants.
     * @return The JNI/JVMTI environment instance.
     */
    public static Pointer getThreadEnv(JavaVM vm, int version) {
        final PointerByReference pEnv = new PointerByReference();
        final int err = vm.GetEnv(pEnv, version);
        if (err != JVM.JNI_OK) throw new RuntimeException("GetEnv failed with error code " + err);
        return pEnv.getValue();
    }

    /**
     * Converts a given JNI handle to a normal Java object.
     * @param env The JNI environment to use.
     * @param handle The handle to object in the jvm heap.
     * @return The object as a normal java object.
     */
    public static <T> T unwrapHandle(JNIEnv env, Pointer handle) {
        if (handle == null) return null;
        Pointer methodId = LOCAL_DUMMY_FUNC.get();
        if (methodId == null) {
            createDummyMethod(env);
            methodId = LOCAL_DUMMY_FUNC.get();
        }
        final Pointer obj = env.NewGlobalRef(handle);
        try (final Memory memory = new Memory(8)) {
            memory.setPointer(0, obj);
            env.CallObjectMethodA(handle, methodId, memory);
        } finally {
            env.DeleteGlobalRef(obj);
        }
        final Object ret = LOCAL.get();
        LOCAL.set(null);
        return (T) ret;
    }

    /**
     * Converts a given Java object to a JNI handle.
     * @param env The JNI environment to use.
     * @param obj The object to convert.
     * @return The object as a JNI handle.
     */
    public static Pointer wrapObject(JNIEnv env, Object obj) {
        if (obj == null) return null;
        if (dummyCallback == null) {
            final Method method = createDummy2Method();
            if (method == null) sneakThrow(new NoSuchMethodError("Failed to find dummy method"));
            final Callback cb = new Callback() {
                public void callback(Pointer envPtr, Pointer clazz, Pointer obj) {
                    if (obj == null) {
                        LOCAL.set(null);
                        return;
                    }
                    final JNIEnv env = new JNIEnv(envPtr);
                    LOCAL.set(env.NewGlobalRef(obj));
                }
            };
            final JNINativeMethod methodEntry = new JNINativeMethod(method.getName(), "(Ljava/lang/Object;)V", CallbackReference.getFunctionPointer(cb));
            methodEntry.write();
            final Pointer thisClass = env.NewGlobalRef(env.FindClass(JNIUtils.class.getName().replace('.', '/')));
            try {
                env.RegisterNatives(thisClass, methodEntry.getPointer(), 1);
                dummyCallback = cb;
            } finally {
                env.DeleteGlobalRef(thisClass);
            }
        }
        dummy2(obj);
        Pointer ret = (Pointer) LOCAL.get();
        if (ret == null) return null;
        ret = env.NewLocalRef(ret);
        env.DeleteGlobalRef((Pointer) LOCAL.get());
        LOCAL.set(null);
        return ret;
    }

    private static void createDummyMethod(JNIEnv env) {
        for (Method method : JNIUtils.class.getDeclaredMethods()) {
            if (method.getDeclaredAnnotation(Dummy.class) != null) {
                final Pointer localClass = env.FindClass(JNIUtils.class.getName().replace('.', '/'));
                if (localClass == null) sneakThrow(new ClassNotFoundException("Could not find class " + JNIUtils.class.getName()));
                final Pointer clazz = env.NewGlobalRef(env.FindClass(JNIUtils.class.getName().replace('.', '/')));
                try {
                    final Pointer methodId = env.GetStaticMethodID(clazz, method.getName(), "(Ljava/lang/Object;)V");
                    if (methodId == null) throw new NoSuchMethodError("Could not find method " + method.getName());
                    LOCAL_DUMMY_FUNC.set(methodId);
                } finally {
                    env.DeleteGlobalRef(clazz);
                }
                return;
            }
        }
    }

    private static Method createDummy2Method() {
        for (Method method : JNIUtils.class.getDeclaredMethods()) {
            if (method.getDeclaredAnnotation(Dummy2.class) != null) {
                return method;
            }
        }
        return null;
    }

    public static <T extends Throwable> void sneakThrow(Throwable t) throws T {
        throw (T) t;
    }

    @Dummy
    private static void dummy(Object obj) {
        LOCAL.set(obj);
    }

    @Dummy2
    private static native void dummy2(Object obj);

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    private @interface Dummy {}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    private @interface Dummy2 {}
}

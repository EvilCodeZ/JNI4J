package de.evilcodez.jni4j;

import com.sun.jna.Function;
import com.sun.jna.Pointer;

public class JVMTI {

    private final Pointer env;
    private final Function[] functions;

    public JVMTI(Pointer env) {
        this.env = env;
        this.functions = new Function[0];
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Pointer getHandle() {
        return env;
    }

    public Function[] getFunctions() {
        return functions;
    }

    /**
     * @param vm The JavaVM instance used to get the JVMTI environment for.
     * @param jvmtiVersion The version of JVMTI to get. {@link JVM} contains all version constants.
     * @return The JVMTI environment instance.
     */
    public static JVMTI getThreadEnv(JavaVM vm, int jvmtiVersion) {
        return new JVMTI(JNIUtils.getThreadEnv(vm, jvmtiVersion));
    }
}

package de.evilcodez.jni4j.callbacks;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;

public interface JvmtiStartFunction extends Callback {

    void callback(Pointer jvmtiEnv, Pointer jniEnv, Pointer arg);
}

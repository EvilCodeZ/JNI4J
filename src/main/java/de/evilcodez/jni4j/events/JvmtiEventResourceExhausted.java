package de.evilcodez.jni4j.events;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;

public interface JvmtiEventResourceExhausted extends Callback {

    void callback(Pointer jvmtiEnv, Pointer jniEnv, int flags, Pointer reserved, String description);
}

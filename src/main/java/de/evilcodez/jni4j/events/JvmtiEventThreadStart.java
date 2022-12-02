package de.evilcodez.jni4j.events;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;

public interface JvmtiEventThreadStart extends Callback {

    void callback(Pointer jvmtiEnv, Pointer JNIEnv, Pointer thread);
}

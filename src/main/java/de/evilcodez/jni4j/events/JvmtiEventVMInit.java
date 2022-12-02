package de.evilcodez.jni4j.events;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;

public interface JvmtiEventVMInit extends Callback {

    void callback(Pointer jvmtiEnv, Pointer jniEnv, Pointer thread);
}

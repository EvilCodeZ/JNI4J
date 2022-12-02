package de.evilcodez.jni4j.events;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;
import de.evilcodez.jni4j.structs.JValue;

public interface JvmtiEventMethodExit extends Callback {

    void callback(Pointer jvmtiEnv, Pointer jniEnv, Pointer thread, Pointer method, boolean wasPoppedByException, JValue returnValue);
}

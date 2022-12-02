package de.evilcodez.jni4j.events;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;

public interface JvmtiEventException extends Callback {

    void callback(Pointer jvmtiEnv, Pointer jniEnv, Pointer thread, Pointer method, long location, Pointer exception, Pointer catch_method, long catch_location);
}

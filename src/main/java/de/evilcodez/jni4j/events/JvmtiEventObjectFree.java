package de.evilcodez.jni4j.events;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;

public interface JvmtiEventObjectFree extends Callback {

    void callback(Pointer jvmtiEnv, long tag);
}

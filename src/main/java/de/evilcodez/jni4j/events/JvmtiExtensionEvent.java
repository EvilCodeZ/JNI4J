package de.evilcodez.jni4j.events;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;

public interface JvmtiExtensionEvent extends Callback {

    int callback(Pointer jvmtiEnv, Object... args);
}

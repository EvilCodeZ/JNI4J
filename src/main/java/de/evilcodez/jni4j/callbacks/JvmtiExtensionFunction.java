package de.evilcodez.jni4j.callbacks;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;

public interface JvmtiExtensionFunction extends Callback {

    int callback(Pointer jvmtiEnv, Object... args);
}

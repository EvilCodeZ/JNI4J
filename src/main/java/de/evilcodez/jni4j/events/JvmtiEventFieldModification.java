package de.evilcodez.jni4j.events;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;
import de.evilcodez.jni4j.structs.JValue;

public interface JvmtiEventFieldModification extends Callback {

    void callback(Pointer jvmtiEnv, Pointer jniEnv, Pointer thread, Pointer method, long location, Pointer fieldKlass, Pointer object, Pointer field, char signature_type, JValue new_value);
}

package de.evilcodez.jni4j.callbacks;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.LongByReference;
import de.evilcodez.jni4j.structs.JValue;

public interface JvmtiPrimitiveFieldCallback extends Callback {

    int callback(int kind, Pointer info, long objectClassTag, LongByReference objectTagPtr, JValue value, int valueType, Pointer userData);
}

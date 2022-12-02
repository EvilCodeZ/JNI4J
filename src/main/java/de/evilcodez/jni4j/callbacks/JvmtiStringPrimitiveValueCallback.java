package de.evilcodez.jni4j.callbacks;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.LongByReference;

public interface JvmtiStringPrimitiveValueCallback extends Callback {

    int callback(long classTag, long size, LongByReference tagPtr, Pointer value, int valueLength, Pointer userData);
}

package de.evilcodez.jni4j.callbacks;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.LongByReference;

public interface JvmtiHeapReferenceCallback extends Callback {

    int callback(int referenceKind, Pointer referenceInfo, long classTag, long referrerClassTag, long size, LongByReference tagPtr, LongByReference referrerTag, int length, Pointer userData);
}

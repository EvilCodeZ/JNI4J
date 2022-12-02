package de.evilcodez.jni4j.callbacks;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.LongByReference;

public interface JvmtiHeapRootCallback extends Callback {

    int callback(int rootKind, long classTag, long size, LongByReference tagPtr, Pointer userData);
}

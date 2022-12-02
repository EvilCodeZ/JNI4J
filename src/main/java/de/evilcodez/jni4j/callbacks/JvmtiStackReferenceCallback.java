package de.evilcodez.jni4j.callbacks;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.LongByReference;

public interface JvmtiStackReferenceCallback extends Callback {

    int callback(int referenceKind, long classTag, long size, LongByReference tagPtr, long threadTag, int depth, Pointer method, int slot, Pointer userData);
}

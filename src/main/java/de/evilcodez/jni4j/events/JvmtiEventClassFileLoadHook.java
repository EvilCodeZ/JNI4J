package de.evilcodez.jni4j.events;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

public interface JvmtiEventClassFileLoadHook extends Callback {

    void callback(Pointer jvmtiEnv, Pointer jniEnv, Pointer classBeingRedefined, Pointer loader, String name, Pointer protectionDomain, int classDataLen, Pointer classData, IntByReference newClassDataLen, PointerByReference newClassData);
}

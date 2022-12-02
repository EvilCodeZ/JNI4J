package de.evilcodez.jni4j.events;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

public interface JvmtiEventNativeMethodBind extends Callback {

    void callback(Pointer jvmtiEnv, Pointer jniEnv, Pointer thread, Pointer method, Pointer address, PointerByReference newAddressPtr);
}

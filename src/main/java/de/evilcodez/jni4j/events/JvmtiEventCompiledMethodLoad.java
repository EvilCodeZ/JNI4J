package de.evilcodez.jni4j.events;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;

public interface JvmtiEventCompiledMethodLoad extends Callback {

    void callback(Pointer jvmtiEnv, Pointer method, int codeSize, Pointer codeAddr, int mapLength, Pointer map, Pointer compileInfo);
}

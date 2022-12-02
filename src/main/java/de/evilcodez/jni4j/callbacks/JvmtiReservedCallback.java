package de.evilcodez.jni4j.callbacks;

import com.sun.jna.Callback;

public interface JvmtiReservedCallback extends Callback {

    int callback();
}

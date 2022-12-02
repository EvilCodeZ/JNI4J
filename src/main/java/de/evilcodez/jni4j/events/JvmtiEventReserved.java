package de.evilcodez.jni4j.events;

import com.sun.jna.Callback;

public interface JvmtiEventReserved extends Callback {

    void callback();
}

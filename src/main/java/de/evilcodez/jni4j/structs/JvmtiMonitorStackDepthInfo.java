package de.evilcodez.jni4j.structs;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

@Structure.FieldOrder({ "monitor", "stack_depth" })
public class JvmtiMonitorStackDepthInfo extends Structure {

    public Pointer monitor;
    public int stack_depth;

    public JvmtiMonitorStackDepthInfo() {}

    public JvmtiMonitorStackDepthInfo(Pointer p) {
        super(p);
        this.read();
    }

    public JvmtiMonitorStackDepthInfo(Pointer monitor, int stack_depth) {
        this.monitor = monitor;
        this.stack_depth = stack_depth;
    }
}

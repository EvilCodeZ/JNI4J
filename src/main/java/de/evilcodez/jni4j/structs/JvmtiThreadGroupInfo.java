package de.evilcodez.jni4j.structs;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

@Structure.FieldOrder({ "parent", "name", "max_priority", "is_daemon" })
public class JvmtiThreadGroupInfo extends Structure {

    public Pointer parent;
    public String name;
    public int max_priority;
    public boolean is_daemon;

    public JvmtiThreadGroupInfo() {}

    public JvmtiThreadGroupInfo(Pointer p) {
        super(p);
        this.read();
    }

    public JvmtiThreadGroupInfo(Pointer parent, String name, int max_priority, boolean is_daemon) {
        this.parent = parent;
        this.name = name;
        this.max_priority = max_priority;
        this.is_daemon = is_daemon;
    }
}

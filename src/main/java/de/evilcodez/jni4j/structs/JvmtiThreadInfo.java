package de.evilcodez.jni4j.structs;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

@Structure.FieldOrder({ "name", "priority", "is_daemon", "thread_group", "context_class_loader" })
public class JvmtiThreadInfo extends Structure {

    public String name;
    public int priority;
    public boolean is_daemon;
    public Pointer thread_group;
    public Pointer context_class_loader;

    public JvmtiThreadInfo() {}

    public JvmtiThreadInfo(Pointer p) {
        super(p);
        this.read();
    }

    public JvmtiThreadInfo(String name, int priority, boolean is_daemon, Pointer thread_group, Pointer context_class_loader) {
        this.name = name;
        this.priority = priority;
        this.is_daemon = is_daemon;
        this.thread_group = thread_group;
        this.context_class_loader = context_class_loader;
    }
}

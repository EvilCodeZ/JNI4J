package de.evilcodez.jni4j.structs;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

@Structure.FieldOrder({ "thread_tag", "thread_id", "depth", "method", "location", "slot" })
public class JvmtiHeapReferenceInfoStackLocal extends Structure {

    public long thread_tag;
    public long thread_id;
    public int depth;
    /**
     * jmethodID
     */
    public Pointer method;
    public long location;
    public int slot;

    public JvmtiHeapReferenceInfoStackLocal() {}

    public JvmtiHeapReferenceInfoStackLocal(Pointer p) {
        super(p);
        this.read();
    }

    public JvmtiHeapReferenceInfoStackLocal(long thread_tag, long thread_id, int depth, Pointer method, long location, int slot) {
        this.thread_tag = thread_tag;
        this.thread_id = thread_id;
        this.depth = depth;
        this.method = method;
        this.location = location;
        this.slot = slot;
    }
}

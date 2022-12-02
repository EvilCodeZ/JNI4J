package de.evilcodez.jni4j.structs;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

@Structure.FieldOrder({ "thread_tag", "method_id", "depth", "method" })
public class JvmtiHeapReferenceInfoJniLocal extends Structure {

    public long thread_tag;
    public long thread_id;
    public int depth;
    /**
     * jmethodID
     */
    public Pointer method;

    public JvmtiHeapReferenceInfoJniLocal() {}

    public JvmtiHeapReferenceInfoJniLocal(Pointer p) {
        super(p);
        this.read();
    }

    public JvmtiHeapReferenceInfoJniLocal(long thread_tag, long thread_id, int depth, Pointer method) {
        this.thread_tag = thread_tag;
        this.thread_id = thread_id;
        this.depth = depth;
        this.method = method;
    }
}

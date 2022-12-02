package de.evilcodez.jni4j.structs;

import com.sun.jna.*;

@Structure.FieldOrder({ "field", "array", "constant_pool", "stack_local", "jni_local", "other" })
public class JvmtiHeapReferenceInfo extends Union {

    public JvmtiHeapReferenceInfoField field;
    public JvmtiHeapReferenceInfoArray array;
    public JvmtiHeapReferenceInfoConstantPool constant_pool;
    public JvmtiHeapReferenceInfoStackLocal stack_local;
    public JvmtiHeapReferenceInfoJniLocal jni_local;
    public JvmtiHeapReferenceInfoReserved other;

    public JvmtiHeapReferenceInfo() {}

    /**
     * WARNING: This constructor does not automatically read the fields of the union. You must call read() yourself.
     */
    public JvmtiHeapReferenceInfo(Pointer p) {
        super(p);
    }
}

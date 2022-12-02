package de.evilcodez.jni4j.structs;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

@Structure.FieldOrder({ "reserved1", "reserved2", "reserved3", "reserved4", "reserved5", "reserved6", "reserved7", "reserved8" })
public class JvmtiHeapReferenceInfoReserved extends Structure {

    public long reserved1;
    public long reserved2;
    public long reserved3;
    public long reserved4;
    public long reserved5;
    public long reserved6;
    public long reserved7;
    public long reserved8;

    public JvmtiHeapReferenceInfoReserved() {}

    public JvmtiHeapReferenceInfoReserved(Pointer p) {
        super(p);
        this.read();
    }
}

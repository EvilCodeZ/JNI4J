package de.evilcodez.jni4j.structs;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

@Structure.FieldOrder({ "index"})
public class JvmtiHeapReferenceInfoField extends Structure {

    public int index;

    public JvmtiHeapReferenceInfoField() {}

    public JvmtiHeapReferenceInfoField(Pointer p) {
        super(p);
        this.read();
    }

    public JvmtiHeapReferenceInfoField(int index) {
        this.index = index;
    }
}

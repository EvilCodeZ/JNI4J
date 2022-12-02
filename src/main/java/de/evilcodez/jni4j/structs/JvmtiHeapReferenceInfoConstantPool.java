package de.evilcodez.jni4j.structs;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

@Structure.FieldOrder({ "index"})
public class JvmtiHeapReferenceInfoConstantPool extends Structure {

    public int index;

    public JvmtiHeapReferenceInfoConstantPool() {}

    public JvmtiHeapReferenceInfoConstantPool(Pointer p) {
        super(p);
        this.read();
    }

    public JvmtiHeapReferenceInfoConstantPool(int index) {
        this.index = index;
    }
}

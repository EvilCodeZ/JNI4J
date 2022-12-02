package de.evilcodez.jni4j.structs;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

@Structure.FieldOrder({ "index"})
public class JvmtiHeapReferenceInfoArray extends Structure {

    public int index;

    public JvmtiHeapReferenceInfoArray() {}

    public JvmtiHeapReferenceInfoArray(Pointer p) {
        super(p);
        this.read();
    }

    public JvmtiHeapReferenceInfoArray(int index) {
        this.index = index;
    }
}

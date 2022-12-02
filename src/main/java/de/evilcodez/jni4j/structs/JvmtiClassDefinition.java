package de.evilcodez.jni4j.structs;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

@Structure.FieldOrder({ "klass", "class_byte_count", "class_bytes" })
public class JvmtiClassDefinition extends Structure {

    /**
     * jclass
     */
    public Pointer klass;
    public int class_byte_count;
    public byte[] class_bytes;

    public JvmtiClassDefinition() {}

    public JvmtiClassDefinition(Pointer p) {
        super(p);
    }
}

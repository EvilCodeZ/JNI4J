package de.evilcodez.jni4j.structs;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

@Structure.FieldOrder({ "method", "location" })
public class JvmtiFrameInfo extends Structure {

    public Pointer method;
    public long location;

    public JvmtiFrameInfo() {}

    public JvmtiFrameInfo(Pointer p) {
        super(p);
        this.read();
    }

    public JvmtiFrameInfo(Pointer method, long location) {
        this.method = method;
        this.location = location;
    }
}

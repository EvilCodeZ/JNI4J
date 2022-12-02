package de.evilcodez.jni4j.structs;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

@Structure.FieldOrder({ "start_address", "location" })
public class JvmtiAddrLocationMap extends Structure {

    public Pointer start_address;
    public long location;

    public JvmtiAddrLocationMap() {}

    public JvmtiAddrLocationMap(Pointer p) {
        super(p);
        this.read();
    }

    public JvmtiAddrLocationMap(Pointer start_address, long location) {
        this.start_address = start_address;
        this.location = location;
    }
}

package de.evilcodez.jni4j.structs;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

@Structure.FieldOrder({ "start_location", "length", "name", "signature", "generic_signature", "slot" })
public class JvmtiLocalVariableEntry extends Structure {

    public long start_location;
    public int length;
    public String name;
    public String signature;
    public String generic_signature;
    public int slot;

    public JvmtiLocalVariableEntry() {}

    public JvmtiLocalVariableEntry(Pointer p) {
        super(p);
        this.read();
    }

    public JvmtiLocalVariableEntry(long start_location, int length, String name, String signature, String generic_signature, int slot) {
        this.start_location = start_location;
        this.length = length;
        this.name = name;
        this.signature = signature;
        this.generic_signature = generic_signature;
        this.slot = slot;
    }
}

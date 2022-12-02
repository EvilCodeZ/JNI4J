package de.evilcodez.jni4j.structs;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

@Structure.FieldOrder({ "start_location", "line_number" })
public class JvmtiLineNumberEntry extends Structure {

    public long start_location;
    public int line_number;

    public JvmtiLineNumberEntry() {}

    public JvmtiLineNumberEntry(Pointer p) {
        super(p);
        this.read();
    }

    public JvmtiLineNumberEntry(long start_location, int line_number) {
        this.start_location = start_location;
        this.line_number = line_number;
    }
}

package de.evilcodez.jni4j.structs;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import de.evilcodez.jni4j.callbacks.*;

@Structure.FieldOrder({ "heap_iteration_callback", "heap_reference_callback", "primitive_field_callback", "array_primitive_value_callback", "string_primitive_value_callback", "reserved5", "reserved6", "reserved7", "reserved8", "reserved9", "reserved10", "reserved11", "reserved12", "reserved13", "reserved14", "reserved15" })
public class JvmtiHeapCallbacks extends Structure {

    public JvmtiHeapIterationCallback heap_iteration_callback;
    public JvmtiHeapReferenceCallback heap_reference_callback;
    public JvmtiPrimitiveFieldCallback primitive_field_callback;
    public JvmtiArrayPrimitiveValueCallback array_primitive_value_callback;
    public JvmtiStringPrimitiveValueCallback string_primitive_value_callback;
    public JvmtiReservedCallback reserved5;
    public JvmtiReservedCallback reserved6;
    public JvmtiReservedCallback reserved7;
    public JvmtiReservedCallback reserved8;
    public JvmtiReservedCallback reserved9;
    public JvmtiReservedCallback reserved10;
    public JvmtiReservedCallback reserved11;
    public JvmtiReservedCallback reserved12;
    public JvmtiReservedCallback reserved13;
    public JvmtiReservedCallback reserved14;
    public JvmtiReservedCallback reserved15;

    public JvmtiHeapCallbacks() {}

    public JvmtiHeapCallbacks(Pointer p) {
        super(p);
        this.read();
    }
}

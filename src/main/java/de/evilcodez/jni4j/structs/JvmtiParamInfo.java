package de.evilcodez.jni4j.structs;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

@Structure.FieldOrder({ "name", "kind", "base_type", "null_ok" })
public class JvmtiParamInfo extends Structure {

    public String name;
    /**
     * @see de.evilcodez.jni4j.JVM under JVMTI_KIND_*
     */
    public int kind;
    /**
     * @see de.evilcodez.jni4j.JVM under JVMTI_TYPE_*
     */
    public int base_type;
    public boolean null_ok;

    public JvmtiParamInfo() {}

    public JvmtiParamInfo(Pointer p) {
        super(p);
        this.read();
    }
}

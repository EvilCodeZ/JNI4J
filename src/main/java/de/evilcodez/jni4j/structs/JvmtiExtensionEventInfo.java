package de.evilcodez.jni4j.structs;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

@Structure.FieldOrder({ "extension_event_index", "id", "short_description", "param_count", "params" })
public class JvmtiExtensionEventInfo extends Structure {

    public int extension_event_index;
    public String id;
    public String short_description;
    public int param_count;
    /**
     * Array of {@link JvmtiParamInfo} structures of size {@link #param_count}.
     * @see JvmtiParamInfo
     */
    public Pointer params;

    public JvmtiExtensionEventInfo() {}

    public JvmtiExtensionEventInfo(Pointer p) {
        super(p);
        this.read();
    }
}

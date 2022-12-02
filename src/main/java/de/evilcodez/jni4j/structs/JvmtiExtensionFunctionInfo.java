package de.evilcodez.jni4j.structs;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import de.evilcodez.jni4j.callbacks.JvmtiExtensionFunction;

@Structure.FieldOrder({ "func", "id", "short_description", "param_count", "params", "error_count", "errors" })
public class JvmtiExtensionFunctionInfo extends Structure {

    public JvmtiExtensionFunction func;
    public String id;
    public String short_description;
    public int param_count;
    /**
     * Array of {@link JvmtiParamInfo} structures of size {@link #param_count}.
     * @see JvmtiParamInfo
     */
    public Pointer params;
    public int error_count;
    /**
     * Array of jvmtiError (int) of size {@link #error_count}.
     * @see de.evilcodez.jni4j.JVM for JVMTI error codes
     */
    public Pointer errors;

    public JvmtiExtensionFunctionInfo() {}

    public JvmtiExtensionFunctionInfo(Pointer p) {
        super(p);
        this.read();
    }
}

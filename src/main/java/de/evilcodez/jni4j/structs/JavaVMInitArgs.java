package de.evilcodez.jni4j.structs;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

@Structure.FieldOrder({"version", "nOptions", "options", "ignoreUnrecognized"})
public class JavaVMInitArgs extends Structure {

    public int version;
    public int nOptions;
    public Pointer options;
    public boolean ignoreUnrecognized;

    public JavaVMInitArgs() {}

    public JavaVMInitArgs(Pointer p) {
        super(p);
        this.read();
    }

    public JavaVMInitArgs(int version, int nOptions, Pointer options, boolean ignoreUnrecognized) {
        this.version = version;
        this.nOptions = nOptions;
        this.options = options;
        this.ignoreUnrecognized = ignoreUnrecognized;
    }
}
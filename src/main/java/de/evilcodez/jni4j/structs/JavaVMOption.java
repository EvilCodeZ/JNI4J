package de.evilcodez.jni4j.structs;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

@Structure.FieldOrder({"optionString", "extraInfo"})
public class JavaVMOption extends Structure {

    public String optionString;
    public Pointer extraInfo;

    public JavaVMOption() {}

    public JavaVMOption(Pointer p) {
        super(p);
        this.read();
    }

    public JavaVMOption(String optionString, Pointer extraInfo) {
        this.optionString = optionString;
        this.extraInfo = extraInfo;
    }
}
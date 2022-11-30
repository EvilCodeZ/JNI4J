package de.evilcodez.jni4j.structs;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

@Structure.FieldOrder({"name", "signature", "fnPtr"})
public class JNINativeMethod extends Structure {

    public String name;
    public String signature;
    public Pointer fnPtr;

    public JNINativeMethod() {}

    public JNINativeMethod(Pointer p) {
        super(p);
        this.read();
    }

    public JNINativeMethod(String name, String signature, Pointer fnPtr) {
        this.name = name;
        this.signature = signature;
        this.fnPtr = fnPtr;
    }
}
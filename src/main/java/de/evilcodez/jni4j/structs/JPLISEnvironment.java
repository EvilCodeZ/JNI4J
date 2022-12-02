package de.evilcodez.jni4j.structs;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

@Structure.FieldOrder({"mJVMTIEnv", "mAgent", "mIsRetransformer"})
public class JPLISEnvironment extends Structure {

    public Pointer mJVMTIEnv;
    public Pointer mAgent;
    public boolean mIsRetransformer;

    public JPLISEnvironment() {}

    public JPLISEnvironment(Pointer p) {
        super(p);
    }
}

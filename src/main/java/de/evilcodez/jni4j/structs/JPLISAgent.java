package de.evilcodez.jni4j.structs;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

@Structure.FieldOrder({"mJVM", "mNormalEnvironment", "mRetransformEnvironment", "mInstrumentationImpl", "mPremainCaller", "mAgentmainCaller", "mTransform", "mRedefineAvailable", "mRedefineAdded", "mNativeMethodPrefixAvailable", "mNativeMethodPrefixAdded", "mAgentClassName", "mOptionsString"})
public class JPLISAgent extends Structure {

    public Pointer mJVM;
    public JPLISEnvironment mNormalEnvironment;
    public JPLISEnvironment mRetransformEnvironment;
    public Pointer mInstrumentationImpl;
    public Pointer mPremainCaller;
    public Pointer mAgentmainCaller;
    public Pointer mTransform;
    public boolean mRedefineAvailable;
    public boolean mRedefineAdded;
    public boolean mNativeMethodPrefixAvailable;
    public boolean mNativeMethodPrefixAdded;
    public String mAgentClassName;
    public String mOptionsString;

    public JPLISAgent() {}

    public JPLISAgent(Pointer p) {
        super(p);
        this.read();
    }

    private JPLISAgent(Pointer p, int dummy) {
        super(p);
    }

    public static JPLISAgent createWithoutRead(Pointer p) {
        return new JPLISAgent(p, 0);
    }
}

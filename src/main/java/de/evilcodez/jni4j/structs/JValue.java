package de.evilcodez.jni4j.structs;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Union;

public class JValue extends Union implements Structure.ByValue {

    public boolean z;
    public byte b;
    public char c;
    public short s;
    public int i;
    public long j;
    public float f;
    public double d;
    public Pointer l;

    public JValue() {}

    public JValue(Pointer p) {
        super(p);
    }
}

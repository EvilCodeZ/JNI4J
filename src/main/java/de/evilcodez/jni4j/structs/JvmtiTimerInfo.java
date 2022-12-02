package de.evilcodez.jni4j.structs;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

@Structure.FieldOrder({ "max_value", "may_skip_forward", "may_skip_backward", "kind", "reserved1", "reserved2" })
public class JvmtiTimerInfo extends Structure {

    public long max_value;
    /**
     * jboolean
     */
    public byte may_skip_forward;
    /**
     * jboolean
     */
    public byte may_skip_backward;
    /**
     * @see de.evilcodez.jni4j.JVM under the name of JVMTI_TIMER_*
     */
    public int kind;
    public long reserved1;
    public long reserved2;

    public JvmtiTimerInfo() {}

    public JvmtiTimerInfo(Pointer p) {
        super(p);
        this.read();
    }

    public JvmtiTimerInfo(long max_value, byte may_skip_forward, byte may_skip_backward, int kind, long reserved1, long reserved2) {
        this.max_value = max_value;
        this.may_skip_forward = may_skip_forward;
        this.may_skip_backward = may_skip_backward;
        this.kind = kind;
        this.reserved1 = reserved1;
        this.reserved2 = reserved2;
    }
}

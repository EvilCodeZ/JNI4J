package de.evilcodez.jni4j.structs;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

@Structure.FieldOrder({ "thread", "state", "frame_buffer", "frame_count" })
public class JvmtiStackInfo extends Structure {

    public Pointer thread;
    public int state;
    /**
     * Array of {@link JvmtiFrameInfo} structures of size {@link #frame_count}.
     * @see JvmtiFrameInfo
     */
    public Pointer frame_buffer;
    public int frame_count;

    public JvmtiStackInfo() {}

    public JvmtiStackInfo(Pointer p) {
        super(p);
        this.read();
    }

    public JvmtiStackInfo(Pointer thread, int state, Pointer frame_buffer, int frame_count) {
        this.thread = thread;
        this.state = state;
        this.frame_buffer = frame_buffer;
        this.frame_count = frame_count;
    }
}

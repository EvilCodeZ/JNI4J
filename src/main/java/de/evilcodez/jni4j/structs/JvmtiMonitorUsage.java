package de.evilcodez.jni4j.structs;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

@Structure.FieldOrder({ "owner", "entry_count", "waiter_count", "waiters", "notify_waiter_count", "notify_waiters" })
public class JvmtiMonitorUsage extends Structure {

    /**
     * jthread
     */
    public Pointer owner;
    public int entry_count;
    public int waiter_count;
    /**
     * jthread* of {@link #waiter_count} elements
     */
    public Pointer waiters;
    public int notify_waiter_count;
    /**
     * jthread* of {@link #notify_waiter_count} elements
     */
    public Pointer notify_waiters;

    public JvmtiMonitorUsage() {}

    public JvmtiMonitorUsage(Pointer p) {
        super(p);
        this.read();
    }

    public JvmtiMonitorUsage(Pointer owner, int entry_count, int waiter_count, Pointer waiters, int notify_waiter_count, Pointer notify_waiters) {
        this.owner = owner;
        this.entry_count = entry_count;
        this.waiter_count = waiter_count;
        this.waiters = waiters;
        this.notify_waiter_count = notify_waiter_count;
        this.notify_waiters = notify_waiters;
    }
}

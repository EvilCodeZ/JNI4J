package de.evilcodez.jni4j.structs;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

@Structure.FieldOrder({ "bitfield_0", "bitfield_1" })
public class JvmtiCapabilities extends Structure {

    public static final long CAN_TAG_OBJECTS = 1L;
    public static final long CAN_GENERATE_FIELD_MODIFICATION_EVENTS = 1L << 1;
    public static final long CAN_GENERATE_FIELD_ACCESS_EVENTS = 1L << 2;
    public static final long CAN_GET_BYTECODES = 1L << 3;
    public static final long CAN_GET_SYNTHETIC_ATTRIBUTE = 1L << 4;
    public static final long CAN_GET_OWNER_MONITOR_INFO = 1L << 5;
    public static final long CAN_GET_CURRENT_CONTENDED_MONITOR = 1L << 6;
    public static final long CAN_GET_MONITOR_INFO = 1L << 7;
    public static final long CAN_POP_FRAME = 1L << 8;
    public static final long CAN_REDEFINE_CLASSES = 1L << 9;
    public static final long CAN_SIGNAL_THREAD = 1L << 10;
    public static final long CAN_GET_SOURCE_FILE_NAME = 1L << 11;
    public static final long CAN_GET_LINE_NUMBERS = 1L << 12;
    public static final long CAN_GET_SOURCE_DEBUG_EXTENSION = 1L << 13;
    public static final long CAN_ACCESS_LOCAL_VARIABLES = 1L << 14;
    public static final long CAN_MAINTAIN_ORIGINAL_METHOD_ORDER = 1L << 15;
    public static final long CAN_GENERATE_SINGLE_STEP_EVENTS = 1L << 16;
    public static final long CAN_GENERATE_EXCEPTION_EVENTS = 1L << 17;
    public static final long CAN_GENERATE_FRAME_POP_EVENTS = 1L << 18;
    public static final long CAN_GENERATE_BREAKPOINT_EVENTS = 1L << 19;
    public static final long CAN_SUSPEND = 1L << 20;
    public static final long CAN_REDEFINE_ANY_CLASS = 1L << 21;
    public static final long CAN_GET_CURRENT_THREAD_CPU_TIME = 1L << 22;
    public static final long CAN_GET_THREAD_CPU_TIME = 1L << 23;
    public static final long CAN_GENERATE_METHOD_ENTRY_EVENTS = 1L << 24;
    public static final long CAN_GENERATE_METHOD_EXIT_EVENTS = 1L << 25;
    public static final long CAN_GENERATE_ALL_CLASS_HOOK_EVENTS = 1L << 26;
    public static final long CAN_GENERATE_COMPILED_METHOD_LOAD_EVENTS = 1L << 27;
    public static final long CAN_GENERATE_MONITOR_EVENTS = 1L << 28;
    public static final long CAN_GENERATE_VM_OBJECT_ALLOC_EVENTS = 1L << 29;
    public static final long CAN_GENERATE_NATIVE_METHOD_BIND_EVENTS = 1L << 30;
    public static final long CAN_GENERATE_GARBAGE_COLLECTION_EVENTS = 1L << 31;
    public static final long CAN_GENERATE_OBJECT_FREE_EVENTS = 1L << 32;
    public static final long CAN_FORCE_EARLY_RETURN = 1L << 33;
    public static final long CAN_GET_OWNED_MONITOR_STACK_DEPTH_INFO = 1L << 34;
    public static final long CAN_GET_CONSTANT_POOL = 1L << 35;
    public static final long CAN_SET_NATIVE_METHOD_PREFIX = 1L << 36;
    public static final long CAN_RETRANSFORM_CLASSES = 1L << 37;
    public static final long CAN_RETRANSFORM_ANY_CLASS = 1L << 38;
    public static final long CAN_GENERATE_RESOURCE_EXHAUSTION_HEAP_EVENTS = 1L << 39;
    public static final long CAN_GENERATE_RESOURCE_EXHAUSTION_THREADS_EVENTS = 1L << 40;
    public static final long[] ALL_CAPABILITIES = new long[] {
            CAN_TAG_OBJECTS, CAN_GENERATE_FIELD_MODIFICATION_EVENTS, CAN_GENERATE_FIELD_ACCESS_EVENTS, CAN_GET_BYTECODES,
            CAN_GET_SYNTHETIC_ATTRIBUTE, CAN_GET_OWNER_MONITOR_INFO, CAN_GET_CURRENT_CONTENDED_MONITOR, CAN_GET_MONITOR_INFO,
            CAN_POP_FRAME, CAN_REDEFINE_CLASSES, CAN_SIGNAL_THREAD, CAN_GET_SOURCE_FILE_NAME,
            CAN_GET_LINE_NUMBERS, CAN_GET_SOURCE_DEBUG_EXTENSION, CAN_ACCESS_LOCAL_VARIABLES, CAN_MAINTAIN_ORIGINAL_METHOD_ORDER,
            CAN_GENERATE_SINGLE_STEP_EVENTS, CAN_GENERATE_EXCEPTION_EVENTS, CAN_GENERATE_FRAME_POP_EVENTS, CAN_GENERATE_BREAKPOINT_EVENTS,
            CAN_SUSPEND, CAN_REDEFINE_ANY_CLASS, CAN_GET_CURRENT_THREAD_CPU_TIME, CAN_GET_THREAD_CPU_TIME,
            CAN_GENERATE_METHOD_ENTRY_EVENTS, CAN_GENERATE_METHOD_EXIT_EVENTS, CAN_GENERATE_ALL_CLASS_HOOK_EVENTS, CAN_GENERATE_COMPILED_METHOD_LOAD_EVENTS,
            CAN_GENERATE_MONITOR_EVENTS, CAN_GENERATE_VM_OBJECT_ALLOC_EVENTS, CAN_GENERATE_NATIVE_METHOD_BIND_EVENTS, CAN_GENERATE_GARBAGE_COLLECTION_EVENTS,
            CAN_GENERATE_OBJECT_FREE_EVENTS, CAN_FORCE_EARLY_RETURN, CAN_GET_OWNED_MONITOR_STACK_DEPTH_INFO, CAN_GET_CONSTANT_POOL,
            CAN_SET_NATIVE_METHOD_PREFIX, CAN_RETRANSFORM_CLASSES, CAN_RETRANSFORM_ANY_CLASS, CAN_GENERATE_RESOURCE_EXHAUSTION_HEAP_EVENTS,
            CAN_GENERATE_RESOURCE_EXHAUSTION_THREADS_EVENTS
    };

    public long bitfield_0;
    public long bitfield_1;

    public JvmtiCapabilities() {}

    public JvmtiCapabilities(Pointer p) {
        super(p);
        this.read();
    }

    public JvmtiCapabilities(long bitfield_0, long bitfield_1) {
        this.bitfield_0 = bitfield_0;
        this.bitfield_1 = bitfield_1;
    }

    public JvmtiCapabilities set(long mask, boolean set) {
        if (set) bitfield_0 |= mask;
        else bitfield_0 &= ~mask;
        return this;
    }

    public boolean get(long mask) {
        return (bitfield_0 & mask) != 0;
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private long bitfield;

        public Builder() {}

        public Builder(JvmtiCapabilities capabilities) {
            this.bitfield = capabilities.bitfield_0;
        }

        public JvmtiCapabilities build() {
            return new JvmtiCapabilities(bitfield, 0L);
        }

        public Builder applyOn(JvmtiCapabilities capabilities) {
            capabilities.bitfield_0 = bitfield;
            capabilities.bitfield_1 = 0L;
            return this;
        }

        private Builder modifyBits(boolean set, long bits) {
            if (set) bitfield |= bits;
            else bitfield &= ~bits;
            return this;
        }

        public Builder canTagObjects(boolean v) {
            return modifyBits(v, CAN_TAG_OBJECTS);
        }

        public Builder canGenerateFieldModificationEvents(boolean v) {
            return modifyBits(v, CAN_GENERATE_FIELD_MODIFICATION_EVENTS);
        }

        public Builder canGenerateFieldAccessEvents(boolean v) {
            return modifyBits(v, CAN_GENERATE_FIELD_ACCESS_EVENTS);
        }

        public Builder canGetBytecodes(boolean v) {
            return modifyBits(v, CAN_GET_BYTECODES);
        }

        public Builder canGetSyntheticAttribute(boolean v) {
            return modifyBits(v, CAN_GET_SYNTHETIC_ATTRIBUTE);
        }

        public Builder canGetOwnedMonitorInfo(boolean v) {
            return modifyBits(v, CAN_GET_OWNER_MONITOR_INFO);
        }

        public Builder canGetCurrentContendedMonitor(boolean v) {
            return modifyBits(v, CAN_GET_CURRENT_CONTENDED_MONITOR);
        }

        public Builder canGetMonitorInfo(boolean v) {
            return modifyBits(v, CAN_GET_MONITOR_INFO);
        }

        public Builder canPopFrame(boolean v) {
            return modifyBits(v, CAN_POP_FRAME);
        }

        public Builder canRedefineClasses(boolean v) {
            return modifyBits(v, CAN_REDEFINE_CLASSES);
        }

        public Builder canSignalThread(boolean v) {
            return modifyBits(v, CAN_SIGNAL_THREAD);
        }

        public Builder canGetSourceFileName(boolean v) {
            return modifyBits(v, CAN_GET_SOURCE_FILE_NAME);
        }

        public Builder canGetLineNumbers(boolean v) {
            return modifyBits(v, CAN_GET_LINE_NUMBERS);
        }

        public Builder canGetSourceDebugExtension(boolean v) {
            return modifyBits(v, CAN_GET_SOURCE_DEBUG_EXTENSION);
        }

        public Builder canAccessLocalVariables(boolean v) {
            return modifyBits(v, CAN_ACCESS_LOCAL_VARIABLES);
        }

        public Builder canMaintainOriginalMethodOrder(boolean v) {
            return modifyBits(v, CAN_MAINTAIN_ORIGINAL_METHOD_ORDER);
        }

        public Builder canGenerateSingleStepEvents(boolean v) {
            return modifyBits(v, CAN_GENERATE_SINGLE_STEP_EVENTS);
        }

        public Builder canGenerateExceptionEvents(boolean v) {
            return modifyBits(v, CAN_GENERATE_EXCEPTION_EVENTS);
        }

        public Builder canGenerateFramePopEvents(boolean v) {
            return modifyBits(v, CAN_GENERATE_FRAME_POP_EVENTS);
        }

        public Builder canGenerateBreakpointEvents(boolean v) {
            return modifyBits(v, CAN_GENERATE_BREAKPOINT_EVENTS);
        }

        public Builder canSuspend(boolean v) {
            return modifyBits(v, CAN_SUSPEND);
        }

        public Builder canRedefineAnyClass(boolean v) {
            return modifyBits(v, CAN_REDEFINE_ANY_CLASS);
        }

        public Builder canGetCurrentThreadCpuTime(boolean v) {
            return modifyBits(v, CAN_GET_CURRENT_THREAD_CPU_TIME);
        }

        public Builder canGetThreadCpuTime(boolean v) {
            return modifyBits(v, CAN_GET_THREAD_CPU_TIME);
        }

        public Builder canGenerateMethodEntryEvents(boolean v) {
            return modifyBits(v, CAN_GENERATE_METHOD_ENTRY_EVENTS);
        }

        public Builder canGenerateMethodExitEvents(boolean v) {
            return modifyBits(v, CAN_GENERATE_METHOD_EXIT_EVENTS);
        }

        public Builder canGenerateAllClassHookEvents(boolean v) {
            return modifyBits(v, CAN_GENERATE_ALL_CLASS_HOOK_EVENTS);
        }

        public Builder canGenerateCompiledMethodLoadEvents(boolean v) {
            return modifyBits(v, CAN_GENERATE_COMPILED_METHOD_LOAD_EVENTS);
        }

        public Builder canGenerateMonitorEvents(boolean v) {
            return modifyBits(v, CAN_GENERATE_MONITOR_EVENTS);
        }

        public Builder canGenerateVmObjectAllocEvents(boolean v) {
            return modifyBits(v, CAN_GENERATE_VM_OBJECT_ALLOC_EVENTS);
        }

        public Builder canGenerateNativeMethodBindEvents(boolean v) {
            return modifyBits(v, CAN_GENERATE_NATIVE_METHOD_BIND_EVENTS);
        }

        public Builder canGenerateGarbageCollectionEvents(boolean v) {
            return modifyBits(v, CAN_GENERATE_GARBAGE_COLLECTION_EVENTS);
        }

        public Builder canGenerateObjectFreeEvents(boolean v) {
            return modifyBits(v, CAN_GENERATE_OBJECT_FREE_EVENTS);
        }

        public Builder canForceEarlyReturn(boolean v) {
            return modifyBits(v, CAN_FORCE_EARLY_RETURN);
        }

        public Builder canGetOwnedMonitorStackDepthInfo(boolean v) {
            return modifyBits(v, CAN_GET_OWNED_MONITOR_STACK_DEPTH_INFO);
        }

        public Builder canGetConstantPool(boolean v) {
            return modifyBits(v, CAN_GET_CONSTANT_POOL);
        }

        public Builder canSetNativeMethodPrefix(boolean v) {
            return modifyBits(v, CAN_SET_NATIVE_METHOD_PREFIX);
        }

        public Builder canRetransformClasses(boolean v) {
            return modifyBits(v, CAN_RETRANSFORM_CLASSES);
        }

        public Builder canRetransformAnyClass(boolean v) {
            return modifyBits(v, CAN_RETRANSFORM_ANY_CLASS);
        }

        public Builder canGenerateResourceExhaustionHeapEvents(boolean v) {
            return modifyBits(v, CAN_GENERATE_RESOURCE_EXHAUSTION_HEAP_EVENTS);
        }

        public Builder canGenerateResourceExhaustionThreadsEvents(boolean v) {
            return modifyBits(v, CAN_GENERATE_RESOURCE_EXHAUSTION_THREADS_EVENTS);
        }
    }
}

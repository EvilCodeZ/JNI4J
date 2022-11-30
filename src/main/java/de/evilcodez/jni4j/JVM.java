package de.evilcodez.jni4j;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import com.sun.jna.win32.StdCallLibrary;

public interface JVM extends StdCallLibrary {

    JVM INSTANCE = Native.load("jvm", JVM.class);

    // possible return values for JNI functions.
    int JNI_OK        = 0;
    int JNI_ERR       = -1;
    int JNI_EDETACHED = -2;
    int JNI_EVERSION  = -3;
    int JNI_ENOMEM    = -4;
    int JNI_EEXIST    = -5;
    int JNI_EINVAL    = -6;

    // JNI versions
    int JNI_VERSION_1_1 = 0x00010001;
    int JNI_VERSION_1_2 = 0x00010002;
    int JNI_VERSION_1_4 = 0x00010004;
    int JNI_VERSION_1_6 = 0x00010006;
    int JNI_VERSION_1_8 = 0x00010008;

    // jboolean constants
    int JNI_TRUE  = 1;
    int JNI_FALSE = 0;

    // enum jobjectRefType
    int JNI_REF_TYPE_INVALID     = 0;
    int JNI_REF_TYPE_LOCAL       = 1;
    int JNI_REF_TYPE_GLOBAL      = 2;
    int JNI_REF_TYPE_WEAK_GLOBAL = 3;

    // JVMTI versions
    int JVMTI_VERSION_1     = 0x30010000;
    int JVMTI_VERSION_1_0   = 0x30010000;
    int JVMTI_VERSION_1_1   = 0x30010001;
    int JVMTI_VERSION_1_2   = 0x30010002;
    int JVMTI_VERSION_1_2_1 = 0x30010201;

    // Thread State Flags
    int JVMTI_THREAD_STATE_ALIVE = 0x0001;
    int JVMTI_THREAD_STATE_TERMINATED = 0x0002;
    int JVMTI_THREAD_STATE_RUNNABLE = 0x0004;
    int JVMTI_THREAD_STATE_BLOCKED_ON_MONITOR_ENTER = 0x0400;
    int JVMTI_THREAD_STATE_WAITING = 0x0080;
    int JVMTI_THREAD_STATE_WAITING_INDEFINITELY = 0x0010;
    int JVMTI_THREAD_STATE_WAITING_WITH_TIMEOUT = 0x0020;
    int JVMTI_THREAD_STATE_SLEEPING = 0x0040;
    int JVMTI_THREAD_STATE_IN_OBJECT_WAIT = 0x0100;
    int JVMTI_THREAD_STATE_PARKED = 0x0200;
    int JVMTI_THREAD_STATE_SUSPENDED = 0x100000;
    int JVMTI_THREAD_STATE_INTERRUPTED = 0x200000;
    int JVMTI_THREAD_STATE_IN_NATIVE = 0x400000;
    int JVMTI_THREAD_STATE_VENDOR_1 = 0x10000000;
    int JVMTI_THREAD_STATE_VENDOR_2 = 0x20000000;
    int JVMTI_THREAD_STATE_VENDOR_3 = 0x40000000;

    // java.lang.Thread.State Conversion Masks
    int JVMTI_JAVA_LANG_THREAD_STATE_MASK = JVMTI_THREAD_STATE_TERMINATED | JVMTI_THREAD_STATE_ALIVE | JVMTI_THREAD_STATE_RUNNABLE | JVMTI_THREAD_STATE_BLOCKED_ON_MONITOR_ENTER | JVMTI_THREAD_STATE_WAITING | JVMTI_THREAD_STATE_WAITING_INDEFINITELY | JVMTI_THREAD_STATE_WAITING_WITH_TIMEOUT;
    int JVMTI_JAVA_LANG_THREAD_STATE_NEW = 0;
    int JVMTI_JAVA_LANG_THREAD_STATE_TERMINATED = JVMTI_THREAD_STATE_TERMINATED;
    int JVMTI_JAVA_LANG_THREAD_STATE_RUNNABLE = JVMTI_THREAD_STATE_ALIVE | JVMTI_THREAD_STATE_RUNNABLE;
    int JVMTI_JAVA_LANG_THREAD_STATE_BLOCKED = JVMTI_THREAD_STATE_ALIVE | JVMTI_THREAD_STATE_BLOCKED_ON_MONITOR_ENTER;
    int JVMTI_JAVA_LANG_THREAD_STATE_WAITING = JVMTI_THREAD_STATE_ALIVE | JVMTI_THREAD_STATE_WAITING | JVMTI_THREAD_STATE_WAITING_INDEFINITELY;
    int JVMTI_JAVA_LANG_THREAD_STATE_TIMED_WAITING = JVMTI_THREAD_STATE_ALIVE | JVMTI_THREAD_STATE_WAITING | JVMTI_THREAD_STATE_WAITING_WITH_TIMEOUT;

    // Thread Priority Constants
    int JVMTI_THREAD_MIN_PRIORITY = 1;
    int JVMTI_THREAD_NORM_PRIORITY = 5;
    int JVMTI_THREAD_MAX_PRIORITY = 10;

    // Heap Filter Flags
    int JVMTI_HEAP_FILTER_TAGGED = 0x4;
    int JVMTI_HEAP_FILTER_UNTAGGED = 0x8;
    int JVMTI_HEAP_FILTER_CLASS_TAGGED = 0x10;
    int JVMTI_HEAP_FILTER_CLASS_UNTAGGED = 0x20;

    // Heap Visit Control Flags
    int JVMTI_VISIT_OBJECTS = 0x100;
    int JVMTI_VISIT_ABORT = 0x8000;

    // Heap Reference Enumeration
    int JVMTI_HEAP_REFERENCE_CLASS = 1;
    int JVMTI_HEAP_REFERENCE_FIELD = 2;
    int JVMTI_HEAP_REFERENCE_ARRAY_ELEMENT = 3;
    int JVMTI_HEAP_REFERENCE_CLASS_LOADER = 4;
    int JVMTI_HEAP_REFERENCE_SIGNERS = 5;
    int JVMTI_HEAP_REFERENCE_PROTECTION_DOMAIN = 6;
    int JVMTI_HEAP_REFERENCE_INTERFACE = 7;
    int JVMTI_HEAP_REFERENCE_STATIC_FIELD = 8;
    int JVMTI_HEAP_REFERENCE_CONSTANT_POOL = 9;
    int JVMTI_HEAP_REFERENCE_SUPERCLASS = 10;
    int JVMTI_HEAP_REFERENCE_JNI_GLOBAL = 21;
    int JVMTI_HEAP_REFERENCE_SYSTEM_CLASS = 22;
    int JVMTI_HEAP_REFERENCE_MONITOR = 23;
    int JVMTI_HEAP_REFERENCE_STACK_LOCAL = 24;
    int JVMTI_HEAP_REFERENCE_JNI_LOCAL = 25;
    int JVMTI_HEAP_REFERENCE_THREAD = 26;
    int JVMTI_HEAP_REFERENCE_OTHER = 27;

    // Primitive Type Enumeration
    int JVMTI_PRIMITIVE_TYPE_BOOLEAN = 90;
    int JVMTI_PRIMITIVE_TYPE_BYTE = 66;
    int JVMTI_PRIMITIVE_TYPE_CHAR = 67;
    int JVMTI_PRIMITIVE_TYPE_SHORT = 83;
    int JVMTI_PRIMITIVE_TYPE_INT = 73;
    int JVMTI_PRIMITIVE_TYPE_LONG = 74;
    int JVMTI_PRIMITIVE_TYPE_FLOAT = 70;
    int JVMTI_PRIMITIVE_TYPE_DOUBLE = 68;

    // Heap Object Filter Enumeration
    int JVMTI_HEAP_OBJECT_TAGGED = 1;
    int JVMTI_HEAP_OBJECT_UNTAGGED = 2;
    int JVMTI_HEAP_OBJECT_EITHER = 3;

    // Heap Root Kind Enumeration
    int JVMTI_HEAP_ROOT_JNI_GLOBAL = 1;
    int JVMTI_HEAP_ROOT_SYSTEM_CLASS = 2;
    int JVMTI_HEAP_ROOT_MONITOR = 3;
    int JVMTI_HEAP_ROOT_STACK_LOCAL = 4;
    int JVMTI_HEAP_ROOT_JNI_LOCAL = 5;
    int JVMTI_HEAP_ROOT_THREAD = 6;
    int JVMTI_HEAP_ROOT_OTHER = 7;

    // Object Reference Enumeration
    int JVMTI_REFERENCE_CLASS = 1;
    int JVMTI_REFERENCE_FIELD = 2;
    int JVMTI_REFERENCE_ARRAY_ELEMENT = 3;
    int JVMTI_REFERENCE_CLASS_LOADER = 4;
    int JVMTI_REFERENCE_SIGNERS = 5;
    int JVMTI_REFERENCE_PROTECTION_DOMAIN = 6;
    int JVMTI_REFERENCE_INTERFACE = 7;
    int JVMTI_REFERENCE_STATIC_FIELD = 8;
    int JVMTI_REFERENCE_CONSTANT_POOL = 9;

    // Iteration Control Enumeration
    int JVMTI_ITERATION_CONTINUE = 1;
    int JVMTI_ITERATION_IGNORE = 2;
    int JVMTI_ITERATION_ABORT = 0;

    // Class Status Flags
    int JVMTI_CLASS_STATUS_VERIFIED = 1;
    int JVMTI_CLASS_STATUS_PREPARED = 2;
    int JVMTI_CLASS_STATUS_INITIALIZED = 4;
    int JVMTI_CLASS_STATUS_ERROR = 8;
    int JVMTI_CLASS_STATUS_ARRAY = 16;
    int JVMTI_CLASS_STATUS_PRIMITIVE = 32;

    // Event Enable/Disable
    int JVMTI_ENABLE = 1;
    int JVMTI_DISABLE = 0;

    // Extension Function/Event Parameter Types
    int JVMTI_TYPE_JBYTE = 101;
    int JVMTI_TYPE_JCHAR = 102;
    int JVMTI_TYPE_JSHORT = 103;
    int JVMTI_TYPE_JINT = 104;
    int JVMTI_TYPE_JLONG = 105;
    int JVMTI_TYPE_JFLOAT = 106;
    int JVMTI_TYPE_JDOUBLE = 107;
    int JVMTI_TYPE_JBOOLEAN = 108;
    int JVMTI_TYPE_JOBJECT = 109;
    int JVMTI_TYPE_JTHREAD = 110;
    int JVMTI_TYPE_JCLASS = 111;
    int JVMTI_TYPE_JVALUE = 112;
    int JVMTI_TYPE_JFIELDID = 113;
    int JVMTI_TYPE_JMETHODID = 114;
    int JVMTI_TYPE_CCHAR = 115;
    int JVMTI_TYPE_CVOID = 116;
    int JVMTI_TYPE_JNIENV = 117;

    // Extension Function/Event Parameter Kinds
    int JVMTI_KIND_IN = 91;
    int JVMTI_KIND_IN_PTR = 92;
    int JVMTI_KIND_IN_BUF = 93;
    int JVMTI_KIND_ALLOC_BUF = 94;
    int JVMTI_KIND_ALLOC_ALLOC_BUF = 95;
    int JVMTI_KIND_OUT = 96;
    int JVMTI_KIND_OUT_BUF = 97;

    // Timer Kinds
    int JVMTI_TIMER_USER_CPU = 30;
    int JVMTI_TIMER_TOTAL_CPU = 31;
    int JVMTI_TIMER_ELAPSED = 32;

    // Phases of execution
    int JVMTI_PHASE_ONLOAD = 1;
    int JVMTI_PHASE_PRIMORDIAL = 2;
    int JVMTI_PHASE_START = 6;
    int JVMTI_PHASE_LIVE = 4;
    int JVMTI_PHASE_DEAD = 8;

    // Version Interface Types
    int JVMTI_VERSION_INTERFACE_JNI = 0x00000000;
    int JVMTI_VERSION_INTERFACE_JVMTI = 0x30000000;

    // Version Masks
    int JVMTI_VERSION_MASK_INTERFACE_TYPE = 0x70000000;
    int JVMTI_VERSION_MASK_MAJOR = 0x0FFF0000;
    int JVMTI_VERSION_MASK_MINOR = 0x0000FF00;
    int JVMTI_VERSION_MASK_MICRO = 0x000000FF;

    // Version Shifts
    int JVMTI_VERSION_SHIFT_MAJOR = 16;
    int JVMTI_VERSION_SHIFT_MINOR = 8;
    int JVMTI_VERSION_SHIFT_MICRO = 0;

    // Verbose Flag Enumeration
    int JVMTI_VERBOSE_OTHER = 0;
    int JVMTI_VERBOSE_GC = 1;
    int JVMTI_VERBOSE_CLASS = 2;
    int JVMTI_VERBOSE_JNI = 3;

    // JLocation Format Enumeration
    int JVMTI_JLOCATION_JVMBCI    = 1;
    int JVMTI_JLOCATION_MACHINEPC = 2;
    int JVMTI_JLOCATION_OTHER     = 0;

    // Resource Exhaustion Flags
    int JVMTI_RESOURCE_EXHAUSTED_OOM_ERROR = 0x0001;
    int JVMTI_RESOURCE_EXHAUSTED_JAVA_HEAP = 0x0002;
    int JVMTI_RESOURCE_EXHAUSTED_THREADS   = 0x0004;

    // Errors
    int JVMTI_ERROR_NONE = 0;
    int JVMTI_ERROR_INVALID_THREAD = 10;
    int JVMTI_ERROR_INVALID_THREAD_GROUP = 11;
    int JVMTI_ERROR_INVALID_PRIORITY = 12;
    int JVMTI_ERROR_THREAD_NOT_SUSPENDED = 13;
    int JVMTI_ERROR_THREAD_SUSPENDED = 14;
    int JVMTI_ERROR_THREAD_NOT_ALIVE = 15;
    int JVMTI_ERROR_INVALID_OBJECT = 20;
    int JVMTI_ERROR_INVALID_CLASS = 21;
    int JVMTI_ERROR_CLASS_NOT_PREPARED = 22;
    int JVMTI_ERROR_INVALID_METHODID = 23;
    int JVMTI_ERROR_INVALID_LOCATION = 24;
    int JVMTI_ERROR_INVALID_FIELDID = 25;
    int JVMTI_ERROR_NO_MORE_FRAMES = 31;
    int JVMTI_ERROR_OPAQUE_FRAME = 32;
    int JVMTI_ERROR_TYPE_MISMATCH = 34;
    int JVMTI_ERROR_INVALID_SLOT = 35;
    int JVMTI_ERROR_DUPLICATE = 40;
    int JVMTI_ERROR_NOT_FOUND = 41;
    int JVMTI_ERROR_INVALID_MONITOR = 50;
    int JVMTI_ERROR_NOT_MONITOR_OWNER = 51;
    int JVMTI_ERROR_INTERRUPT = 52;
    int JVMTI_ERROR_INVALID_CLASS_FORMAT = 60;
    int JVMTI_ERROR_CIRCULAR_CLASS_DEFINITION = 61;
    int JVMTI_ERROR_FAILS_VERIFICATION = 62;
    int JVMTI_ERROR_UNSUPPORTED_REDEFINITION_METHOD_ADDED = 63;
    int JVMTI_ERROR_UNSUPPORTED_REDEFINITION_SCHEMA_CHANGED = 64;
    int JVMTI_ERROR_INVALID_TYPESTATE = 65;
    int JVMTI_ERROR_UNSUPPORTED_REDEFINITION_HIERARCHY_CHANGED = 66;
    int JVMTI_ERROR_UNSUPPORTED_REDEFINITION_METHOD_DELETED = 67;
    int JVMTI_ERROR_UNSUPPORTED_VERSION = 68;
    int JVMTI_ERROR_NAMES_DONT_MATCH = 69;
    int JVMTI_ERROR_UNSUPPORTED_REDEFINITION_CLASS_MODIFIERS_CHANGED = 70;
    int JVMTI_ERROR_UNSUPPORTED_REDEFINITION_METHOD_MODIFIERS_CHANGED = 71;
    int JVMTI_ERROR_UNMODIFIABLE_CLASS = 79;
    int JVMTI_ERROR_NOT_AVAILABLE = 98;
    int JVMTI_ERROR_MUST_POSSESS_CAPABILITY = 99;
    int JVMTI_ERROR_NULL_POINTER = 100;
    int JVMTI_ERROR_ABSENT_INFORMATION = 101;
    int JVMTI_ERROR_INVALID_EVENT_TYPE = 102;
    int JVMTI_ERROR_ILLEGAL_ARGUMENT = 103;
    int JVMTI_ERROR_NATIVE_METHOD = 104;
    int JVMTI_ERROR_CLASS_LOADER_UNSUPPORTED = 106;
    int JVMTI_ERROR_OUT_OF_MEMORY = 110;
    int JVMTI_ERROR_ACCESS_DENIED = 111;
    int JVMTI_ERROR_WRONG_PHASE = 112;
    int JVMTI_ERROR_INTERNAL = 113;
    int JVMTI_ERROR_UNATTACHED_THREAD = 115;
    int JVMTI_ERROR_INVALID_ENVIRONMENT = 116;
    int JVMTI_ERROR_MAX = 116;

    // Event IDs
    int JVMTI_MIN_EVENT_TYPE_VAL = 50;
    int JVMTI_EVENT_VM_INIT = 50;
    int JVMTI_EVENT_VM_DEATH = 51;
    int JVMTI_EVENT_THREAD_START = 52;
    int JVMTI_EVENT_THREAD_END = 53;
    int JVMTI_EVENT_CLASS_FILE_LOAD_HOOK = 54;
    int JVMTI_EVENT_CLASS_LOAD = 55;
    int JVMTI_EVENT_CLASS_PREPARE = 56;
    int JVMTI_EVENT_VM_START = 57;
    int JVMTI_EVENT_EXCEPTION = 58;
    int JVMTI_EVENT_EXCEPTION_CATCH = 59;
    int JVMTI_EVENT_SINGLE_STEP = 60;
    int JVMTI_EVENT_FRAME_POP = 61;
    int JVMTI_EVENT_BREAKPOINT = 62;
    int JVMTI_EVENT_FIELD_ACCESS = 63;
    int JVMTI_EVENT_FIELD_MODIFICATION = 64;
    int JVMTI_EVENT_METHOD_ENTRY = 65;
    int JVMTI_EVENT_METHOD_EXIT = 66;
    int JVMTI_EVENT_NATIVE_METHOD_BIND = 67;
    int JVMTI_EVENT_COMPILED_METHOD_LOAD = 68;
    int JVMTI_EVENT_COMPILED_METHOD_UNLOAD = 69;
    int JVMTI_EVENT_DYNAMIC_CODE_GENERATED = 70;
    int JVMTI_EVENT_DATA_DUMP_REQUEST = 71;
    int JVMTI_EVENT_MONITOR_WAIT = 73;
    int JVMTI_EVENT_MONITOR_WAITED = 74;
    int JVMTI_EVENT_MONITOR_CONTENDED_ENTER = 75;
    int JVMTI_EVENT_MONITOR_CONTENDED_ENTERED = 76;
    int JVMTI_EVENT_RESOURCE_EXHAUSTED = 80;
    int JVMTI_EVENT_GARBAGE_COLLECTION_START = 81;
    int JVMTI_EVENT_GARBAGE_COLLECTION_FINISH = 82;
    int JVMTI_EVENT_OBJECT_FREE = 83;
    int JVMTI_EVENT_VM_OBJECT_ALLOC = 84;
    int JVMTI_MAX_EVENT_TYPE_VAL = 84;

    int JNI_GetDefaultJavaVMInitArgs(Pointer args);

    int JNI_CreateJavaVM(PointerByReference vm, PointerByReference env, Pointer args);

    int JNI_GetCreatedJavaVMs(Pointer vmBuf, int bufLen, IntByReference nVMs);
}

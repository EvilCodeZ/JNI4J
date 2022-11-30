package de.evilcodez.jni4j;

import com.sun.jna.*;
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

    int JNI_GetDefaultJavaVMInitArgs(Pointer args);

    int JNI_CreateJavaVM(PointerByReference vm, PointerByReference env, Pointer args);

    int JNI_GetCreatedJavaVMs(Pointer vmBuf, int bufLen, IntByReference nVMs);

    @Structure.FieldOrder({"version", "nOptions", "options", "ignoreUnrecognized"})
    class JavaVMInitArgs extends Structure {
        int version;
        int nOptions;
        Pointer options;
        boolean ignoreUnrecognized;

        public JavaVMInitArgs() {}

        public JavaVMInitArgs(Pointer p) {
            super(p);
            this.read();
        }

        public JavaVMInitArgs(int version, int nOptions, Pointer options, boolean ignoreUnrecognized) {
            this.version = version;
            this.nOptions = nOptions;
            this.options = options;
            this.ignoreUnrecognized = ignoreUnrecognized;
        }
    }

    @Structure.FieldOrder({"optionString", "extraInfo"})
    class JavaVMOption extends Structure {
        String optionString;
        Pointer extraInfo;

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

    @Structure.FieldOrder({"name", "signature", "fnPtr"})
    class JNINativeMethod extends Structure {
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
}

package de.evilcodez.jni4j;

import com.sun.jna.Function;
import com.sun.jna.Pointer;

public class JVMTI {

    public static final int reserved0 = 1;
    public static final int SetEventNotificationMode = 1;
    public static final int reserved3 = 2;
    public static final int GetAllThreads = 3;
    public static final int SuspendThread = 4;
    public static final int ResumeThread = 5;
    public static final int StopThread = 6;
    public static final int InterruptThread = 7;
    public static final int GetThreadInfo = 8;
    public static final int GetOwnedMonitorInfo = 9;
    public static final int GetCurrentContendedMonitor = 10;
    public static final int RunAgentThread = 11;
    public static final int GetTopThreadGroups = 12;
    public static final int GetThreadGroupInfo = 13;
    public static final int GetThreadGroupChildren = 14;
    public static final int GetFrameCount = 15;
    public static final int GetThreadState = 16;
    public static final int GetCurrentThread = 17;
    public static final int GetFrameLocation = 18;
    public static final int NotifyFramePop = 19;
    public static final int GetLocalObject = 20;
    public static final int GetLocalInt = 21;
    public static final int GetLocalLong = 22;
    public static final int GetLocalFloat = 23;
    public static final int GetLocalDouble = 24;
    public static final int SetLocalObject = 25;
    public static final int SetLocalInt = 26;
    public static final int SetLocalLong = 27;
    public static final int SetLocalFloat = 28;
    public static final int SetLocalDouble = 29;
    public static final int CreateRawMonitor = 30;
    public static final int DestroyRawMonitor = 31;
    public static final int RawMonitorEnter = 32;
    public static final int RawMonitorExit = 33;
    public static final int RawMonitorWait = 34;
    public static final int RawMonitorNotify = 35;
    public static final int RawMonitorNotifyAll = 36;
    public static final int SetBreakpoint = 37;
    public static final int ClearBreakpoint = 38;
    public static final int reserved40 = 39;
    public static final int SetFieldAccessWatch = 40;
    public static final int ClearFieldAccessWatch = 41;
    public static final int SetFieldModificationWatch = 42;
    public static final int ClearFieldModificationWatch = 43;
    public static final int IsModifiableClass = 44;
    public static final int Allocate = 45;
    public static final int Deallocate = 46;
    public static final int GetClassSignature = 47;
    public static final int GetClassStatus = 48;
    public static final int GetSourceFileName = 49;
    public static final int GetClassModifiers = 50;
    public static final int GetClassMethods = 51;
    public static final int GetClassFields = 52;
    public static final int GetImplementedInterfaces = 53;
    public static final int IsInterface = 54;
    public static final int IsArrayClass = 55;
    public static final int GetClassLoader = 56;
    public static final int GetObjectHashCode = 57;
    public static final int GetObjectMonitorUsage = 58;
    public static final int GetFieldName = 59;
    public static final int GetFieldDeclaringClass = 60;
    public static final int GetFieldModifiers = 61;
    public static final int IsFieldSynthetic = 62;
    public static final int GetMethodName = 63;
    public static final int GetMethodDeclaringClass = 64;
    public static final int GetMethodModifiers = 65;
    public static final int reserved67 = 66;
    public static final int GetMaxLocals = 67;
    public static final int GetArgumentsSize = 68;
    public static final int GetLineNumberTable = 69;
    public static final int GetMethodLocation = 70;
    public static final int GetLocalVariableTable = 71;
    public static final int SetNativeMethodPrefix = 72;
    public static final int SetNativeMethodPrefixes = 73;
    public static final int GetBytecodes = 74;
    public static final int IsMethodNative = 75;
    public static final int IsMethodSynthetic = 76;
    public static final int GetLoadedClasses = 77;
    public static final int GetClassLoaderClasses = 78;
    public static final int PopFrame = 79;
    public static final int ForceEarlyReturnObject = 80;
    public static final int ForceEarlyReturnInt = 81;
    public static final int ForceEarlyReturnLong = 82;
    public static final int ForceEarlyReturnFloat = 83;
    public static final int ForceEarlyReturnDouble = 84;
    public static final int ForceEarlyReturnVoid = 85;
    public static final int RedefineClasses = 86;
    public static final int GetVersionNumber = 87;
    public static final int GetCapabilities = 88;
    public static final int GetSourceDebugExtension = 89;
    public static final int IsMethodObsolete = 90;
    public static final int SuspendThreadList = 91;
    public static final int ResumeThreadList = 92;
    public static final int reserved94 = 93;
    public static final int reserved95 = 94;
    public static final int reserved96 = 95;
    public static final int reserved97 = 96;
    public static final int reserved98 = 97;
    public static final int reserved99 = 98;
    public static final int GetAllStackTraces = 99;
    public static final int GetThreadListStackTraces = 100;
    public static final int GetThreadLocalStorage = 101;
    public static final int SetThreadLocalStorage = 102;
    public static final int GetStackTrace = 103;
    public static final int reserved105 = 104;
    public static final int GetTag = 105;
    public static final int SetTag = 106;
    public static final int ForceGarbageCollection = 107;
    public static final int IterateOverObjectsReachableFromObject = 108;
    public static final int IterateOverReachableObjects = 109;
    public static final int IterateOverHeap = 110;
    public static final int IterateOverInstancesOfClass = 111;
    public static final int reserved113 = 112;
    public static final int GetObjectsWithTags = 113;
    public static final int FollowReferences = 114;
    public static final int IterateThroughHeap = 115;
    public static final int reserved117 = 116;
    public static final int reserved118 = 117;
    public static final int reserved119 = 118;
    public static final int SetJNIFunctionTable = 119;
    public static final int GetJNIFunctionTable = 120;
    public static final int SetEventCallbacks = 121;
    public static final int GenerateEvents = 122;
    public static final int GetExtensionFunctions = 123;
    public static final int GetExtensionEvents = 124;
    public static final int SetExtensionEventCallback = 125;
    public static final int DisposeEnvironment = 126;
    public static final int GetErrorName = 127;
    public static final int GetJLocationFormat = 128;
    public static final int GetSystemProperties = 129;
    public static final int GetSystemProperty = 130;
    public static final int SetSystemProperty = 131;
    public static final int GetPhase = 132;
    public static final int GetCurrentThreadCpuTimerInfo = 133;
    public static final int GetCurrentThreadCpuTime = 134;
    public static final int GetThreadCpuTimerInfo = 135;
    public static final int GetThreadCpuTime = 136;
    public static final int GetTimerInfo = 137;
    public static final int GetTime = 138;
    public static final int GetPotentialCapabilities = 139;
    public static final int reserved141 = 140;
    public static final int AddCapabilities = 141;
    public static final int RelinquishCapabilities = 142;
    public static final int GetAvailableProcessors = 143;
    public static final int GetClassVersionNumbers = 144;
    public static final int GetConstantPool = 145;
    public static final int GetEnvironmentLocalStorage = 146;
    public static final int SetEnvironmentLocalStorage = 147;
    public static final int AddToBootstrapClassLoaderSearch = 148;
    public static final int SetVerboseFlag = 149;
    public static final int AddToSystemClassLoaderSearch = 150;
    public static final int RetransformClasses = 151;
    public static final int GetOwnedMonitorStackDepthInfo = 152;
    public static final int GetObjectSize = 153;
    public static final int GetLocalInstance = 154;
    public static final int NumberOfFunctions = 155;

    private final Pointer env;
    private final Function[] functions;

    public JVMTI(Pointer env) {
        this.env = env;
        this.functions = new Function[0];
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Pointer getHandle() {
        return env;
    }

    public Function[] getFunctions() {
        return functions;
    }

    /**
     * @param vm The JavaVM instance used to get the JVMTI environment for.
     * @param jvmtiVersion The version of JVMTI to get. {@link JVM} contains all version constants.
     * @return The JVMTI environment instance.
     */
    public static JVMTI getThreadEnv(JavaVM vm, int jvmtiVersion) {
        return new JVMTI(JNIUtils.getThreadEnv(vm, jvmtiVersion));
    }
}

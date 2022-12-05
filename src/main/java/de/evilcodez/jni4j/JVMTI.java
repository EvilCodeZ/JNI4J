package de.evilcodez.jni4j;

import com.sun.jna.Function;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.*;
import de.evilcodez.jni4j.callbacks.*;
import de.evilcodez.jni4j.events.JvmtiExtensionEvent;
import de.evilcodez.jni4j.structs.*;

public class JVMTI {

    public static final int reserved0 = 0;
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
        if (env == null) throw new NullPointerException("env");
        this.env = env;
        this.functions = new Function[NumberOfFunctions];
        final long addressSize = Native.POINTER_SIZE;
        final Pointer funcs = env.getPointer(0);
        for (int i = 0; i < NumberOfFunctions; i++) {
            final Pointer functionPointer = funcs.getPointer((long) i * addressSize);
            if (functionPointer == null) continue;
            functions[i] = Function.getFunction(functionPointer);
        }
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

    public int SetEventNotificationMode(int mode, int event_type, Pointer thread, Object... args) {
        Object[] funcArgs = new Object[] { env, mode, event_type, thread };
        if (args != null && args.length > 0) {
            final Object[] newArgs = new Object[funcArgs.length + args.length];
            System.arraycopy(funcArgs, 0, newArgs, 0, funcArgs.length);
            System.arraycopy(args, 0, newArgs, funcArgs.length, args.length);
        }
        return functions[SetEventNotificationMode].invokeInt(funcArgs);
    }

    public int GetAllThreads(IntByReference threadsCountPtr, Pointer threadsPtr) {
        return functions[GetAllThreads].invokeInt(new Object[] { env, threadsCountPtr, threadsPtr });
    }

    public int SuspendThread(Pointer thread) {
        return functions[SuspendThread].invokeInt(new Object[] { env, thread });
    }

    public int ResumeThread(Pointer thread) {
        return functions[ResumeThread].invokeInt(new Object[] { env, thread });
    }

    public int StopThread(Pointer thread, Pointer exception) {
        return functions[StopThread].invokeInt(new Object[] { env, thread, exception });
    }

    public int InterruptThread(Pointer thread) {
        return functions[InterruptThread].invokeInt(new Object[] { env, thread });
    }

    public int GetThreadInfo(Pointer thread, JvmtiThreadInfo info) {
        return functions[GetThreadInfo].invokeInt(new Object[] { env, thread, info });
    }

    public int GetOwnedMonitorInfo(Pointer thread, IntByReference ownedMonitorCountPtr, Pointer ownedMonitorPtr) {
        return functions[GetOwnedMonitorInfo].invokeInt(new Object[] { env, thread, ownedMonitorCountPtr, ownedMonitorPtr });
    }

    public int GetCurrentContendedMonitor(Pointer thread, PointerByReference monitorPtr) {
        return functions[GetCurrentContendedMonitor].invokeInt(new Object[] { env, thread, monitorPtr });
    }

    public int RunAgentThread(Pointer thread, JvmtiStartFunction proc, Pointer arg, int priority) {
        return functions[RunAgentThread].invokeInt(new Object[] { env, thread, proc, arg, priority });
    }

    public int GetTopThreadGroups(IntByReference groupCountPtr, Pointer groupPtr) {
        return functions[GetTopThreadGroups].invokeInt(new Object[] { env, groupCountPtr, groupPtr });
    }

    public int GetThreadGroupInfo(Pointer group, JvmtiThreadGroupInfo info) {
        return functions[GetThreadGroupInfo].invokeInt(new Object[] { env, group, info });
    }

    public int GetThreadGroupChildren(Pointer group, IntByReference threadCountPtr, Pointer threadPtr, IntByReference groupCountPtr, Pointer groupPtr) {
        return functions[GetThreadGroupChildren].invokeInt(new Object[] { env, group, threadCountPtr, threadPtr, groupCountPtr, groupPtr });
    }

    public int GetFrameCount(Pointer thread, IntByReference countPtr) {
        return functions[GetFrameCount].invokeInt(new Object[] { env, thread, countPtr });
    }

    public int GetThreadState(Pointer thread, IntByReference threadStatePtr) {
        return functions[GetThreadState].invokeInt(new Object[] { env, thread, threadStatePtr });
    }

    public int GetCurrentThread(PointerByReference threadPtr) {
        return functions[GetCurrentThread].invokeInt(new Object[] { env, threadPtr });
    }

    public int GetFrameLocation(Pointer thread, int depth, PointerByReference methodPtr, LongByReference locationPtr) {
        return functions[GetFrameLocation].invokeInt(new Object[] { env, thread, depth, methodPtr, locationPtr });
    }

    public int NotifyFramePop(Pointer thread, int depth) {
        return functions[NotifyFramePop].invokeInt(new Object[] { env, thread, depth });
    }

    public int GetLocalObject(Pointer thread, int depth, int slot, PointerByReference valuePtr) {
        return functions[GetLocalObject].invokeInt(new Object[] { env, thread, depth, slot, valuePtr });
    }

    public int GetLocalInt(Pointer thread, int depth, int slot, IntByReference valuePtr) {
        return functions[GetLocalInt].invokeInt(new Object[] { env, thread, depth, slot, valuePtr });
    }

    public int GetLocalLong(Pointer thread, int depth, int slot, LongByReference valuePtr) {
        return functions[GetLocalLong].invokeInt(new Object[] { env, thread, depth, slot, valuePtr });
    }

    public int GetLocalFloat(Pointer thread, int depth, int slot, FloatByReference valuePtr) {
        return functions[GetLocalFloat].invokeInt(new Object[] { env, thread, depth, slot, valuePtr });
    }

    public int GetLocalDouble(Pointer thread, int depth, int slot, DoubleByReference valuePtr) {
        return functions[GetLocalDouble].invokeInt(new Object[] { env, thread, depth, slot, valuePtr });
    }

    public int SetLocalObject(Pointer thread, int depth, int slot, Pointer value) {
        return functions[SetLocalObject].invokeInt(new Object[] { env, thread, depth, slot, value });
    }

    public int SetLocalInt(Pointer thread, int depth, int slot, int value) {
        return functions[SetLocalInt].invokeInt(new Object[] { env, thread, depth, slot, value });
    }

    public int SetLocalLong(Pointer thread, int depth, int slot, long value) {
        return functions[SetLocalLong].invokeInt(new Object[] { env, thread, depth, slot, value });
    }

    public int SetLocalFloat(Pointer thread, int depth, int slot, float value) {
        return functions[SetLocalFloat].invokeInt(new Object[] { env, thread, depth, slot, value });
    }

    public int SetLocalDouble(Pointer thread, int depth, int slot, double value) {
        return functions[SetLocalDouble].invokeInt(new Object[] { env, thread, depth, slot, value });
    }

    public int CreateRawMonitor(String name, PointerByReference monitorPtr) {
        return functions[CreateRawMonitor].invokeInt(new Object[] { env, name, monitorPtr });
    }

    public int DestroyRawMonitor(Pointer monitor) {
        return functions[DestroyRawMonitor].invokeInt(new Object[] { env, monitor });
    }

    public int RawMonitorEnter(Pointer monitor) {
        return functions[RawMonitorEnter].invokeInt(new Object[] { env, monitor });
    }

    public int RawMonitorExit(Pointer monitor) {
        return functions[RawMonitorExit].invokeInt(new Object[] { env, monitor });
    }

    public int RawMonitorWait(Pointer monitor, long millis) {
        return functions[RawMonitorWait].invokeInt(new Object[] { env, monitor, millis });
    }

    public int RawMonitorNotify(Pointer monitor) {
        return functions[RawMonitorNotify].invokeInt(new Object[] { env, monitor });
    }

    public int RawMonitorNotifyAll(Pointer monitor) {
        return functions[RawMonitorNotifyAll].invokeInt(new Object[] { env, monitor });
    }

    public int SetBreakpoint(Pointer method, long location) {
        return functions[SetBreakpoint].invokeInt(new Object[] { env, method, location });
    }

    public int ClearBreakpoint(Pointer method, long location) {
        return functions[ClearBreakpoint].invokeInt(new Object[] { env, method, location });
    }

    public int SetFieldAccessWatch(Pointer klass, Pointer field) {
        return functions[SetFieldAccessWatch].invokeInt(new Object[] { env, klass, field });
    }

    public int ClearFieldAccessWatch(Pointer klass, Pointer field) {
        return functions[ClearFieldAccessWatch].invokeInt(new Object[] { env, klass, field });
    }

    public int SetFieldModificationWatch(Pointer klass, Pointer field) {
        return functions[SetFieldModificationWatch].invokeInt(new Object[] { env, klass, field });
    }

    public int ClearFieldModificationWatch(Pointer klass, Pointer field) {
        return functions[ClearFieldModificationWatch].invokeInt(new Object[] { env, klass, field });
    }

    public int IsModifiableClass(Pointer klass, IntByReference isModifiableClassPtr) {
        return functions[IsModifiableClass].invokeInt(new Object[] { env, klass, isModifiableClassPtr });
    }

    public int Allocate(long size, PointerByReference memPtr) {
        return functions[Allocate].invokeInt(new Object[] { env, size, memPtr });
    }

    public int Deallocate(Pointer mem) {
        return functions[Deallocate].invokeInt(new Object[] { env, mem });
    }

    public int GetClassSignature(Pointer klass, PointerByReference signaturePtr, PointerByReference genericPtr) {
        return functions[GetClassSignature].invokeInt(new Object[] { env, klass, signaturePtr, genericPtr });
    }

    public int GetClassStatus(Pointer klass, IntByReference statusPtr) {
        return functions[GetClassStatus].invokeInt(new Object[] { env, klass, statusPtr });
    }

    public int GetSourceFileName(Pointer klass, PointerByReference sourceNamePtr) {
        return functions[GetSourceFileName].invokeInt(new Object[] { env, klass, sourceNamePtr });
    }

    public int GetClassModifiers(Pointer klass, IntByReference modifiersPtr) {
        return functions[GetClassModifiers].invokeInt(new Object[] { env, klass, modifiersPtr });
    }

    public int GetClassMethods(Pointer klass, IntByReference methodCountPtr, Pointer methodsPtr) {
        return functions[GetClassMethods].invokeInt(new Object[] { env, klass, methodCountPtr, methodsPtr });
    }

    public int GetClassFields(Pointer klass, IntByReference fieldCountPtr, Pointer fieldsPtr) {
        return functions[GetClassFields].invokeInt(new Object[] { env, klass, fieldCountPtr, fieldsPtr });
    }

    public int GetImplementedInterfaces(Pointer klass, IntByReference interfaceCountPtr, Pointer interfacesPtr) {
        return functions[GetImplementedInterfaces].invokeInt(new Object[] { env, klass, interfaceCountPtr, interfacesPtr });
    }

    public int IsInterface(Pointer klass, IntByReference isInterfacePtr) {
        return functions[IsInterface].invokeInt(new Object[] { env, klass, isInterfacePtr });
    }

    public int IsArrayClass(Pointer klass, IntByReference isArrayClassPtr) {
        return functions[IsArrayClass].invokeInt(new Object[] { env, klass, isArrayClassPtr });
    }

    public int GetClassLoader(Pointer klass, PointerByReference classLoaderPtr) {
        return functions[GetClassLoader].invokeInt(new Object[] { env, klass, classLoaderPtr });
    }

    public int GetObjectHashCode(Pointer object, IntByReference hashCodePtr) {
        return functions[GetObjectHashCode].invokeInt(new Object[] { env, object, hashCodePtr });
    }

    public int GetObjectMonitorUsage(Pointer object, JvmtiMonitorUsage infoPtr) {
        return functions[GetObjectMonitorUsage].invokeInt(new Object[] { env, object, infoPtr });
    }

    public int GetFieldName(Pointer klass, Pointer field, PointerByReference namePtr, PointerByReference signaturePtr, PointerByReference genericPtr) {
        return functions[GetFieldName].invokeInt(new Object[] { env, klass, field, namePtr, signaturePtr, genericPtr });
    }

    public int GetFieldDeclaringClass(Pointer klass, Pointer field, PointerByReference declaringClassPtr) {
        return functions[GetFieldDeclaringClass].invokeInt(new Object[] { env, klass, field, declaringClassPtr });
    }

    public int GetFieldModifiers(Pointer klass, Pointer field, IntByReference modifiersPtr) {
        return functions[GetFieldModifiers].invokeInt(new Object[] { env, klass, field, modifiersPtr });
    }

    public int IsFieldSynthetic(Pointer klass, Pointer field, IntByReference isSyntheticPtr) {
        return functions[IsFieldSynthetic].invokeInt(new Object[] { env, klass, field, isSyntheticPtr });
    }

    public int GetMethodName(Pointer method, PointerByReference namePtr, PointerByReference signaturePtr, PointerByReference genericPtr) {
        return functions[GetMethodName].invokeInt(new Object[] { env, method, namePtr, signaturePtr, genericPtr });
    }

    public int GetMethodDeclaringClass(Pointer method, PointerByReference declaringClassPtr) {
        return functions[GetMethodDeclaringClass].invokeInt(new Object[] { env, method, declaringClassPtr });
    }

    public int GetMethodModifiers(Pointer method, IntByReference modifiersPtr) {
        return functions[GetMethodModifiers].invokeInt(new Object[] { env, method, modifiersPtr });
    }

    public int GetMaxLocals(Pointer method, IntByReference maxLocalsPtr) {
        return functions[GetMaxLocals].invokeInt(new Object[] { env, method, maxLocalsPtr });
    }

    public int GetArgumentsSize(Pointer method, IntByReference sizePtr) {
        return functions[GetArgumentsSize].invokeInt(new Object[] { env, method, sizePtr });
    }

    public int GetLineNumberTable(Pointer method, IntByReference entryCountPtr, Pointer tablePtr) {
        return functions[GetLineNumberTable].invokeInt(new Object[] { env, method, entryCountPtr, tablePtr });
    }

    public int GetMethodLocation(Pointer method, LongByReference startLocationPtr, LongByReference endLocationPtr) {
        return functions[GetMethodLocation].invokeInt(new Object[] { env, method, startLocationPtr, endLocationPtr });
    }

    public int GetLocalVariableTable(Pointer method, IntByReference entryCountPtr, Pointer tablePtr) {
        return functions[GetLocalVariableTable].invokeInt(new Object[] { env, method, entryCountPtr, tablePtr });
    }

    public int SetNativeMethodPrefix(String prefix) {
        return functions[SetNativeMethodPrefix].invokeInt(new Object[] { env, prefix });
    }

    public int SetNativeMethodPrefixes(String[] prefixes) {
        return functions[SetNativeMethodPrefixes].invokeInt(new Object[] { env, prefixes });
    }

    public int GetBytecodes(Pointer method, IntByReference bytecodeCountPtr, Pointer bytecodesPtr) {
        return functions[GetBytecodes].invokeInt(new Object[] { env, method, bytecodeCountPtr, bytecodesPtr });
    }

    public int IsMethodNative(Pointer method, IntByReference isNativePtr) {
        return functions[IsMethodNative].invokeInt(new Object[] { env, method, isNativePtr });
    }

    public int IsMethodSynthetic(Pointer method, IntByReference isSyntheticPtr) {
        return functions[IsMethodSynthetic].invokeInt(new Object[] { env, method, isSyntheticPtr });
    }

    public int GetLoadedClasses(IntByReference classCountPtr, PointerByReference classesPtr) {
        return functions[GetLoadedClasses].invokeInt(new Object[] { env, classCountPtr, classesPtr });
    }

    public int GetClassLoaderClasses(Pointer initiatingLoader, IntByReference classCountPtr, PointerByReference classesPtr) {
        return functions[GetClassLoaderClasses].invokeInt(new Object[] { env, initiatingLoader, classCountPtr, classesPtr });
    }

    public int PopFrame(Pointer thread) {
        return functions[PopFrame].invokeInt(new Object[] { env, thread });
    }

    public int ForceEarlyReturnObject(Pointer thread, Pointer value) {
        return functions[ForceEarlyReturnObject].invokeInt(new Object[] { env, thread, value });
    }

    public int ForceEarlyReturnInt(Pointer thread, int value) {
        return functions[ForceEarlyReturnInt].invokeInt(new Object[] { env, thread, value });
    }

    public int ForceEarlyReturnLong(Pointer thread, long value) {
        return functions[ForceEarlyReturnLong].invokeInt(new Object[] { env, thread, value });
    }

    public int ForceEarlyReturnFloat(Pointer thread, float value) {
        return functions[ForceEarlyReturnFloat].invokeInt(new Object[] { env, thread, value });
    }

    public int ForceEarlyReturnDouble(Pointer thread, double value) {
        return functions[ForceEarlyReturnDouble].invokeInt(new Object[] { env, thread, value });
    }

    public int ForceEarlyReturnVoid(Pointer thread) {
        return functions[ForceEarlyReturnVoid].invokeInt(new Object[] { env, thread });
    }

    public int RedefineClasses(int classCount, JvmtiClassDefinition[] classDefinitions) {
        return functions[RedefineClasses].invokeInt(new Object[] { env, classCount, classDefinitions });
    }

    public int GetVersionNumber(IntByReference versionPtr) {
        return functions[GetVersionNumber].invokeInt(new Object[] { env, versionPtr });
    }

    public int GetCapabilities(JvmtiCapabilities capabilitiesPtr) {
        return functions[GetCapabilities].invokeInt(new Object[] { env, capabilitiesPtr });
    }

    public int GetSourceDebugExtension(Pointer klass, PointerByReference sourceDebugExtensionPtr) {
        return functions[GetSourceDebugExtension].invokeInt(new Object[] { env, klass, sourceDebugExtensionPtr });
    }

    public int IsMethodObsolete(Pointer method, IntByReference isObsoletePtr) {
        return functions[IsMethodObsolete].invokeInt(new Object[] { env, method, isObsoletePtr });
    }

    public int SuspendThreadList(int requestCount, Pointer[] requestList, IntByReference results) {
        return functions[SuspendThreadList].invokeInt(new Object[] { env, requestCount, requestList, results });
    }

    public int ResumeThreadList(int requestCount, Pointer[] requestList, IntByReference results) {
        return functions[ResumeThreadList].invokeInt(new Object[] { env, requestCount, requestList, results });
    }

    public int GetAllStackTraces(int maxFrameCount, Pointer stackInfoPtr, IntByReference threadCountPtr) {
        return functions[GetAllStackTraces].invokeInt(new Object[] { env, maxFrameCount, stackInfoPtr, threadCountPtr });
    }

    public int GetThreadListStackTraces(int threadCount, Pointer[] threadList, int maxFrameCount, Pointer stackInfoPtr) {
        return functions[GetThreadListStackTraces].invokeInt(new Object[] { env, threadCount, threadList, maxFrameCount, stackInfoPtr });
    }

    public int GetThreadLocalStorage(Pointer thread, PointerByReference dataPtr) {
        return functions[GetThreadLocalStorage].invokeInt(new Object[] { env, thread, dataPtr });
    }

    public int SetThreadLocalStorage(Pointer thread, Pointer data) {
        return functions[SetThreadLocalStorage].invokeInt(new Object[] { env, thread, data });
    }

    public int GetStackTrace(Pointer thread, int startDepth, int maxFrameCount, Pointer frameBuffer, IntByReference countPtr) {
        return functions[GetStackTrace].invokeInt(new Object[] { env, thread, startDepth, maxFrameCount, frameBuffer, countPtr });
    }

    public int GetTag(Pointer object, LongByReference tagPtr) {
        return functions[GetTag].invokeInt(new Object[] { env, object, tagPtr });
    }

    public int SetTag(Pointer object, long tag) {
        return functions[SetTag].invokeInt(new Object[] { env, object, tag });
    }

    public int ForceGarbageCollection() {
        return functions[ForceGarbageCollection].invokeInt(new Object[] { env });
    }

    public int IterateOverObjectsReachableFromObject(Pointer object, JvmtiObjectReferenceCallback objectReferenceCallback, Pointer userData) {
        return functions[IterateOverObjectsReachableFromObject].invokeInt(new Object[] { env, object, objectReferenceCallback, userData });
    }

    public int IterateOverReachableObjects(JvmtiHeapRootCallback heapRootCallback, JvmtiStackReferenceCallback stackRefCallback, JvmtiObjectReferenceCallback objectRefCallback, Pointer userData) {
        return functions[IterateOverReachableObjects].invokeInt(new Object[] { env, heapRootCallback, stackRefCallback, objectRefCallback, userData });
    }

    public int IterateOverHeap(int heapFilter, int objectFilter, JvmtiHeapObjectCallback heapObjectCallback, Pointer userData) {
        return functions[IterateOverHeap].invokeInt(new Object[] { env, heapFilter, objectFilter, heapObjectCallback, userData });
    }

    public int IterateOverInstancesOfClass(Pointer klass, int objectFilter, JvmtiHeapObjectCallback heapObjectCallback, Pointer userData) {
        return functions[IterateOverInstancesOfClass].invokeInt(new Object[] { env, klass, objectFilter, heapObjectCallback, userData });
    }

    public int GetObjectsWithTags(int tagCount, long[] tags, IntByReference countPtr, Pointer objectResultPtr, Pointer tagResultPtr) {
        return functions[GetObjectsWithTags].invokeInt(new Object[] { env, tagCount, tags, countPtr, objectResultPtr, tagResultPtr });
    }

    public int FollowReferences(int heapFilter, Pointer klass, Pointer initialObject, JvmtiHeapCallbacks callbacks, Pointer userData) {
        return functions[FollowReferences].invokeInt(new Object[] { env, heapFilter, klass, initialObject, callbacks, userData });
    }

    public int IterateThroughHeap(int heapFilter, Pointer klass, JvmtiHeapCallbacks callbacks, Pointer userData) {
        return functions[IterateThroughHeap].invokeInt(new Object[] { env, heapFilter, klass, callbacks, userData });
    }

    public int SetJNIFunctionTable(Pointer functionTable) {
        return functions[SetJNIFunctionTable].invokeInt(new Object[] { env, functionTable });
    }

    public int GetJNIFunctionTable(PointerByReference functionTable) {
        return functions[GetJNIFunctionTable].invokeInt(new Object[] { env, functionTable });
    }

    public int SetEventCallbacks(JvmtiEventCallbacks callbacks, int sizeOfCallbacks) {
        return functions[SetEventCallbacks].invokeInt(new Object[] { env, callbacks, sizeOfCallbacks });
    }

    public int GenerateEvents(int event_type) {
        return functions[GenerateEvents].invokeInt(new Object[] { env, event_type });
    }

    public int GetExtensionFunctions(IntByReference extensionCountPtr, Pointer extensions) {
        return functions[GetExtensionFunctions].invokeInt(new Object[] { env, extensionCountPtr, extensions });
    }

    public int GetExtensionEvents(IntByReference extensionCountPtr, Pointer extensions) {
        return functions[GetExtensionEvents].invokeInt(new Object[] { env, extensionCountPtr, extensions });
    }

    public int SetExtensionEventCallback(int extensionEventIndex, JvmtiExtensionEvent callback) {
        return functions[SetExtensionEventCallback].invokeInt(new Object[] { env, extensionEventIndex, callback });
    }

    public int DisposeEnvironment() {
        return functions[DisposeEnvironment].invokeInt(new Object[] { env });
    }

    public int GetErrorName(int error, PointerByReference namePtr) {
        return functions[GetErrorName].invokeInt(new Object[] { env, error, namePtr });
    }

    public int GetJLocationFormat(IntByReference formatPtr) {
        return functions[GetJLocationFormat].invokeInt(new Object[] { env, formatPtr });
    }

    public int GetSystemProperties(IntByReference countPtr, PointerByReference propertyPtr) {
        return functions[GetSystemProperties].invokeInt(new Object[] { env, countPtr, propertyPtr });
    }

    public int GetSystemProperty(String property, PointerByReference valuePtr) {
        return functions[GetSystemProperty].invokeInt(new Object[] { env, property, valuePtr });
    }

    public int SetSystemProperty(String property, String value) {
        return functions[SetSystemProperty].invokeInt(new Object[] { env, property, value });
    }

    public int GetPhase(IntByReference phasePtr) {
        return functions[GetPhase].invokeInt(new Object[] { env, phasePtr });
    }

    public int GetCurrentThreadCpuTimerInfo(JvmtiTimerInfo info) {
        return functions[GetCurrentThreadCpuTimerInfo].invokeInt(new Object[] { env, info });
    }

    public int GetCurrentThreadCpuTime(LongByReference nanos) {
        return functions[GetCurrentThreadCpuTime].invokeInt(new Object[] { env, nanos });
    }

    public int GetThreadCpuTimerInfo(JvmtiTimerInfo info) {
        return functions[GetThreadCpuTimerInfo].invokeInt(new Object[] { env, info });
    }

    public int GetThreadCpuTime(Pointer thread, LongByReference nanos) {
        return functions[GetThreadCpuTime].invokeInt(new Object[] { env, thread, nanos });
    }

    public int GetTimerInfo(JvmtiTimerInfo info) {
        return functions[GetTimerInfo].invokeInt(new Object[] { env, info });
    }

    public int GetTime(LongByReference nanos) {
        return functions[GetTime].invokeInt(new Object[] { env, nanos });
    }

    public int GetPotentialCapabilities(JvmtiCapabilities capabilitiesPtr) {
        return functions[GetPotentialCapabilities].invokeInt(new Object[] { env, capabilitiesPtr });
    }

    public int AddCapabilities(JvmtiCapabilities capabilitiesPtr) {
        return functions[AddCapabilities].invokeInt(new Object[] { env, capabilitiesPtr });
    }

    public int RelinquishCapabilities(JvmtiCapabilities capabilitiesPtr) {
        return functions[RelinquishCapabilities].invokeInt(new Object[] { env, capabilitiesPtr });
    }

    public int GetAvailableProcessors(IntByReference processorCountPtr) {
        return functions[GetAvailableProcessors].invokeInt(new Object[] { env, processorCountPtr });
    }

    public int GetClassVersionNumbers(Pointer klass, IntByReference minorVersionPtr, IntByReference majorVersionPtr) {
        return functions[GetClassVersionNumbers].invokeInt(new Object[] { env, klass, minorVersionPtr, majorVersionPtr });
    }

    public int GetConstantPool(Pointer klass, IntByReference constantPoolCountPtr, IntByReference constantPoolByteCountPtr, PointerByReference constantPoolBytesPtr) {
        return functions[GetConstantPool].invokeInt(new Object[] { env, klass, constantPoolCountPtr, constantPoolByteCountPtr, constantPoolBytesPtr });
    }

    public int GetEnvironmentLocalStorage(PointerByReference dataPtr) {
        return functions[GetEnvironmentLocalStorage].invokeInt(new Object[] { env, dataPtr });
    }

    public int SetEnvironmentLocalStorage(Pointer data) {
        return functions[SetEnvironmentLocalStorage].invokeInt(new Object[] { env, data });
    }

    public int AddToBootstrapClassLoaderSearch(String segment) {
        return functions[AddToBootstrapClassLoaderSearch].invokeInt(new Object[] { env, segment });
    }

    public int SetVerboseFlag(int flag, boolean value) {
        return functions[SetVerboseFlag].invokeInt(new Object[] { env, flag, value });
    }

    public int AddToSystemClassLoaderSearch(String segment) {
        return functions[AddToSystemClassLoaderSearch].invokeInt(new Object[] { env, segment });
    }

    public int RetransformClasses(int classCount, Pointer[] classes) {
        return functions[RetransformClasses].invokeInt(new Object[] { env, classCount, classes });
    }

    public int GetOwnedMonitorStackDepthInfo(Pointer thread, IntByReference infoCountPtr, PointerByReference infoPtr) {
        return functions[GetOwnedMonitorStackDepthInfo].invokeInt(new Object[] { env, thread, infoCountPtr, infoPtr });
    }

    public int GetObjectSize(Pointer object, LongByReference sizePtr) {
        return functions[GetObjectSize].invokeInt(new Object[] { env, object, sizePtr });
    }

    public int GetLocalInstance(Pointer thread, int depth, PointerByReference valuePtr) {
        return functions[GetLocalInstance].invokeInt(new Object[] { env, thread, depth, valuePtr });
    }
}

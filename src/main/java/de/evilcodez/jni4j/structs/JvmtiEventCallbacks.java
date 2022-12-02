package de.evilcodez.jni4j.structs;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import de.evilcodez.jni4j.events.*;

@Structure.FieldOrder({"VMInit", "VMDeath", "ThreadStart", "ThreadEnd", "ClassFileLoadHook", "ClassLoad", "ClassPrepare", "VMStart", "Exception", "ExceptionCatch", "SingleStep", "FramePop", "Breakpoint", "FieldAccess", "FieldModification", "MethodEntry", "MethodExit", "NativeMethodBind", "CompiledMethodLoad", "CompiledMethodUnload", "DynamicCodeGenerated", "DataDumpRequest", "reserved72", "MonitorWait", "MonitorWaited", "MonitorContendedEnter", "MonitorContendedEntered", "reserved77", "reserved78", "reserved79", "ResourceExhausted", "GarbageCollectionStart", "GarbageCollectionFinish", "ObjectFree", "VMObjectAlloc"})
public class JvmtiEventCallbacks extends Structure {

    public JvmtiEventVMInit VMInit;
    public JvmtiEventVMDeath VMDeath;
    public JvmtiEventThreadStart ThreadStart;
    public JvmtiEventThreadEnd ThreadEnd;
    public JvmtiEventClassFileLoadHook ClassFileLoadHook;
    public JvmtiEventClassLoad ClassLoad;
    public JvmtiEventClassPrepare ClassPrepare;
    public JvmtiEventVMStart VMStart;
    public JvmtiEventException Exception;
    public JvmtiEventExceptionCatch ExceptionCatch;
    public JvmtiEventSingleStep SingleStep;
    public JvmtiEventFramePop FramePop;
    public JvmtiEventBreakpoint Breakpoint;
    public JvmtiEventFieldAccess FieldAccess;
    public JvmtiEventFieldModification FieldModification;
    public JvmtiEventMethodEntry MethodEntry;
    public JvmtiEventMethodExit MethodExit;
    public JvmtiEventNativeMethodBind NativeMethodBind;
    public JvmtiEventCompiledMethodLoad CompiledMethodLoad;
    public JvmtiEventCompiledMethodUnload CompiledMethodUnload;
    public JvmtiEventDynamicCodeGenerated DynamicCodeGenerated;
    public JvmtiEventDataDumpRequest DataDumpRequest;
    public JvmtiEventReserved reserved72;
    public JvmtiEventMonitorWait MonitorWait;
    public JvmtiEventMonitorWaited MonitorWaited;
    public JvmtiEventMonitorContendedEnter MonitorContendedEnter;
    public JvmtiEventMonitorContendedEntered MonitorContendedEntered;
    public JvmtiEventReserved reserved77;
    public JvmtiEventReserved reserved78;
    public JvmtiEventReserved reserved79;
    public JvmtiEventResourceExhausted ResourceExhausted;
    public JvmtiEventGarbageCollectionStart GarbageCollectionStart;
    public JvmtiEventGarbageCollectionFinish GarbageCollectionFinish;
    public JvmtiEventObjectFree ObjectFree;
    public JvmtiEventVMObjectAlloc VMObjectAlloc;

    public JvmtiEventCallbacks() {}

    public JvmtiEventCallbacks(Pointer p) {
        super(p);
        this.read();
    }
}

package de.evilcodez.jni4j;

import com.sun.jna.*;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

public class JavaVM {

    public static final int Reserved0 = 0;
    public static final int Reserved1 = 1;
    public static final int Reserved2 = 2;
    public static final int DestroyJavaVM = 3;
    public static final int AttachCurrentThread = 4;
    public static final int DetachCurrentThread = 5;
    public static final int GetEnv = 6;
    public static final int AttachCurrentThreadAsDaemon = 7;
    public static final int NumberOfFunctions = 8;

    private final Pointer vm;
    private final Function[] functions;

    public JavaVM(Pointer vm) {
        if (vm == null) throw new NullPointerException("vm");
        this.vm = vm;
        this.functions = new Function[NumberOfFunctions];
        final long addressSize = Native.POINTER_SIZE;
        final Pointer funcs = vm.getPointer(0);
        for (int i = 0; i < NumberOfFunctions; i++) {
            final Pointer functionPointer = funcs.getPointer((long) i * addressSize);
            if (functionPointer == null) continue;
            functions[i] = Function.getFunction(functionPointer);
        }
    }

    /**
     * @return Returns the JavaVM instance of the current process.
     */
    public static JavaVM getRunningJavaVM() {
        final JVM jvm = JVM.INSTANCE;
        try (final Memory memory = new Memory(Native.POINTER_SIZE)) {
            final IntByReference actualVMs = new IntByReference();
            int err = jvm.JNI_GetCreatedJavaVMs(null, 0, actualVMs);
            if (err != JVM.JNI_OK) throw new RuntimeException("JNI_GetCreatedJavaVMs() failed with error code " + err);
            if (actualVMs.getValue() < 1) throw new RuntimeException("No JVMs found");
            err = jvm.JNI_GetCreatedJavaVMs(memory, 1, actualVMs);
            if (err != JVM.JNI_OK) throw new RuntimeException("JNI_GetCreatedJavaVMs() failed with error code " + err);
            if (actualVMs.getValue() < 1) throw new RuntimeException("Expected at least 1 JVM, but got " + actualVMs.getValue());
            return new JavaVM(memory.getPointer(0));
        }
    }

    public Pointer getHandle() {
        return vm;
    }

    public Function[] getFunctions() {
        return functions;
    }

    public int DestroyJavaVM() {
        return functions[DestroyJavaVM].invokeInt(new Object[] {vm});
    }

    public int AttachCurrentThread(Pointer env, Pointer args) {
        return functions[AttachCurrentThread].invokeInt(new Object[] {vm, env, args});
    }

    public int DetachCurrentThread() {
        return functions[DetachCurrentThread].invokeInt(new Object[] {vm});
    }

    public int GetEnv(PointerByReference pEnv, int version) {
        return functions[GetEnv].invokeInt(new Object[] {vm, pEnv, version});
    }

    public int AttachCurrentThreadAsDaemon(Pointer env, Pointer args) {
        return functions[AttachCurrentThreadAsDaemon].invokeInt(new Object[] {vm, env, args});
    }
}

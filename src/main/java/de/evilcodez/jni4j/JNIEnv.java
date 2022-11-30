package de.evilcodez.jni4j;

import com.sun.jna.*;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

public class JNIEnv {

    public static final int Reserved0 = 0;
    public static final int Reserved1 = 1;
    public static final int Reserved2 = 2;
    public static final int Reserved3 = 3;
    public static final int GetVersion = 4;
    public static final int DefineClass = 5;
    public static final int FindClass = 6;
    public static final int FromReflectedMethod = 7;
    public static final int FromReflectedField = 8;
    public static final int ToReflectedMethod = 9;
    public static final int GetSuperclass = 10;
    public static final int IsAssignableFrom = 11;
    public static final int ToReflectedField = 12;
    public static final int Throw = 13;
    public static final int ThrowNew = 14;
    public static final int ExceptionOccurred = 15;
    public static final int ExceptionDescribe = 16;
    public static final int ExceptionClear = 17;
    public static final int FatalError = 18;
    public static final int PushLocalFrame = 19;
    public static final int PopLocalFrame = 20;
    public static final int NewGlobalRef = 21;
    public static final int DeleteGlobalRef = 22;
    public static final int DeleteLocalRef = 23;
    public static final int IsSameObject = 24;
    public static final int NewLocalRef = 25;
    public static final int EnsureLocalCapacity = 26;
    public static final int AllocObject = 27;
    public static final int NewObject = 28;
    public static final int NewObjectV = 29;
    public static final int NewObjectA = 30;
    public static final int GetObjectClass = 31;
    public static final int IsInstanceOf = 32;
    public static final int GetMethodID = 33;
    public static final int CallObjectMethod = 34;
    public static final int CallObjectMethodV = 35;
    public static final int CallObjectMethodA = 36;
    public static final int CallBooleanMethod = 37;
    public static final int CallBooleanMethodV = 38;
    public static final int CallBooleanMethodA = 39;
    public static final int CallByteMethod = 40;
    public static final int CallByteMethodV = 41;
    public static final int CallByteMethodA = 42;
    public static final int CallCharMethod = 43;
    public static final int CallCharMethodV = 44;
    public static final int CallCharMethodA = 45;
    public static final int CallShortMethod = 46;
    public static final int CallShortMethodV = 47;
    public static final int CallShortMethodA = 48;
    public static final int CallIntMethod = 49;
    public static final int CallIntMethodV = 50;
    public static final int CallIntMethodA = 51;
    public static final int CallLongMethod = 52;
    public static final int CallLongMethodV = 53;
    public static final int CallLongMethodA = 54;
    public static final int CallFloatMethod = 55;
    public static final int CallFloatMethodV = 56;
    public static final int CallFloatMethodA = 57;
    public static final int CallDoubleMethod = 58;
    public static final int CallDoubleMethodV = 59;
    public static final int CallDoubleMethodA = 60;
    public static final int CallVoidMethod = 61;
    public static final int CallVoidMethodV = 62;
    public static final int CallVoidMethodA = 63;
    public static final int CallNonvirtualObjectMethod = 64;
    public static final int CallNonvirtualObjectMethodV = 65;
    public static final int CallNonvirtualObjectMethodA = 66;
    public static final int CallNonvirtualBooleanMethod = 67;
    public static final int CallNonvirtualBooleanMethodV = 68;
    public static final int CallNonvirtualBooleanMethodA = 69;
    public static final int CallNonvirtualByteMethod = 70;
    public static final int CallNonvirtualByteMethodV = 71;
    public static final int CallNonvirtualByteMethodA = 72;
    public static final int CallNonvirtualCharMethod = 73;
    public static final int CallNonvirtualCharMethodV = 74;
    public static final int CallNonvirtualCharMethodA = 75;
    public static final int CallNonvirtualShortMethod = 76;
    public static final int CallNonvirtualShortMethodV = 77;
    public static final int CallNonvirtualShortMethodA = 78;
    public static final int CallNonvirtualIntMethod = 79;
    public static final int CallNonvirtualIntMethodV = 80;
    public static final int CallNonvirtualIntMethodA = 81;
    public static final int CallNonvirtualLongMethod = 82;
    public static final int CallNonvirtualLongMethodV = 83;
    public static final int CallNonvirtualLongMethodA = 84;
    public static final int CallNonvirtualFloatMethod = 85;
    public static final int CallNonvirtualFloatMethodV = 86;
    public static final int CallNonvirtualFloatMethodA = 87;
    public static final int CallNonvirtualDoubleMethod = 88;
    public static final int CallNonvirtualDoubleMethodV = 89;
    public static final int CallNonvirtualDoubleMethodA = 90;
    public static final int CallNonvirtualVoidMethod = 91;
    public static final int CallNonvirtualVoidMethodV = 92;
    public static final int CallNonvirtualVoidMethodA = 93;
    public static final int GetFieldID = 94;
    public static final int GetObjectField = 95;
    public static final int GetBooleanField = 96;
    public static final int GetByteField = 97;
    public static final int GetCharField = 98;
    public static final int GetShortField = 99;
    public static final int GetIntField = 100;
    public static final int GetLongField = 101;
    public static final int GetFloatField = 102;
    public static final int GetDoubleField = 103;
    public static final int SetObjectField = 104;
    public static final int SetBooleanField = 105;
    public static final int SetByteField = 106;
    public static final int SetCharField = 107;
    public static final int SetShortField = 108;
    public static final int SetIntField = 109;
    public static final int SetLongField = 110;
    public static final int SetFloatField = 111;
    public static final int SetDoubleField = 112;
    public static final int GetStaticMethodID = 113;
    public static final int CallStaticObjectMethod = 114;
    public static final int CallStaticObjectMethodV = 115;
    public static final int CallStaticObjectMethodA = 116;
    public static final int CallStaticBooleanMethod = 117;
    public static final int CallStaticBooleanMethodV = 118;
    public static final int CallStaticBooleanMethodA = 119;
    public static final int CallStaticByteMethod = 120;
    public static final int CallStaticByteMethodV = 121;
    public static final int CallStaticByteMethodA = 122;
    public static final int CallStaticCharMethod = 123;
    public static final int CallStaticCharMethodV = 124;
    public static final int CallStaticCharMethodA = 125;
    public static final int CallStaticShortMethod = 126;
    public static final int CallStaticShortMethodV = 127;
    public static final int CallStaticShortMethodA = 128;
    public static final int CallStaticIntMethod = 129;
    public static final int CallStaticIntMethodV = 130;
    public static final int CallStaticIntMethodA = 131;
    public static final int CallStaticLongMethod = 132;
    public static final int CallStaticLongMethodV = 133;
    public static final int CallStaticLongMethodA = 134;
    public static final int CallStaticFloatMethod = 135;
    public static final int CallStaticFloatMethodV = 136;
    public static final int CallStaticFloatMethodA = 137;
    public static final int CallStaticDoubleMethod = 138;
    public static final int CallStaticDoubleMethodV = 139;
    public static final int CallStaticDoubleMethodA = 140;
    public static final int CallStaticVoidMethod = 141;
    public static final int CallStaticVoidMethodV = 142;
    public static final int CallStaticVoidMethodA = 143;
    public static final int GetStaticFieldID = 144;
    public static final int GetStaticObjectField = 145;
    public static final int GetStaticBooleanField = 146;
    public static final int GetStaticByteField = 147;
    public static final int GetStaticCharField = 148;
    public static final int GetStaticShortField = 149;
    public static final int GetStaticIntField = 150;
    public static final int GetStaticLongField = 151;
    public static final int GetStaticFloatField = 152;
    public static final int GetStaticDoubleField = 153;
    public static final int SetStaticObjectField = 154;
    public static final int SetStaticBooleanField = 155;
    public static final int SetStaticByteField = 156;
    public static final int SetStaticCharField = 157;
    public static final int SetStaticShortField = 158;
    public static final int SetStaticIntField = 159;
    public static final int SetStaticLongField = 160;
    public static final int SetStaticFloatField = 161;
    public static final int SetStaticDoubleField = 162;
    public static final int NewString = 163;
    public static final int GetStringLength = 164;
    public static final int GetStringChars = 165;
    public static final int ReleaseStringChars = 166;
    public static final int NewStringUTF = 167;
    public static final int GetStringUTFLength = 168;
    public static final int GetStringUTFChars = 169;
    public static final int ReleaseStringUTFChars = 170;
    public static final int GetArrayLength = 171;
    public static final int NewObjectArray = 172;
    public static final int GetObjectArrayElement = 173;
    public static final int SetObjectArrayElement = 174;
    public static final int NewBooleanArray = 175;
    public static final int NewByteArray = 176;
    public static final int NewCharArray = 177;
    public static final int NewShortArray = 178;
    public static final int NewIntArray = 179;
    public static final int NewLongArray = 180;
    public static final int NewFloatArray = 181;
    public static final int NewDoubleArray = 182;
    public static final int GetBooleanArrayElements = 183;
    public static final int GetByteArrayElements = 184;
    public static final int GetCharArrayElements = 185;
    public static final int GetShortArrayElements = 186;
    public static final int GetIntArrayElements = 187;
    public static final int GetLongArrayElements = 188;
    public static final int GetFloatArrayElements = 189;
    public static final int GetDoubleArrayElements = 190;
    public static final int ReleaseBooleanArrayElements = 191;
    public static final int ReleaseByteArrayElements = 192;
    public static final int ReleaseCharArrayElements = 193;
    public static final int ReleaseShortArrayElements = 194;
    public static final int ReleaseIntArrayElements = 195;
    public static final int ReleaseLongArrayElements = 196;
    public static final int ReleaseFloatArrayElements = 197;
    public static final int ReleaseDoubleArrayElements = 198;
    public static final int GetBooleanArrayRegion = 199;
    public static final int GetByteArrayRegion = 200;
    public static final int GetCharArrayRegion = 201;
    public static final int GetShortArrayRegion = 202;
    public static final int GetIntArrayRegion = 203;
    public static final int GetLongArrayRegion = 204;
    public static final int GetFloatArrayRegion = 205;
    public static final int GetDoubleArrayRegion = 206;
    public static final int SetBooleanArrayRegion = 207;
    public static final int SetByteArrayRegion = 208;
    public static final int SetCharArrayRegion = 209;
    public static final int SetShortArrayRegion = 210;
    public static final int SetIntArrayRegion = 211;
    public static final int SetLongArrayRegion = 212;
    public static final int SetFloatArrayRegion = 213;
    public static final int SetDoubleArrayRegion = 214;
    public static final int RegisterNatives = 215;
    public static final int UnregisterNatives = 216;
    public static final int MonitorEnter = 217;
    public static final int MonitorExit = 218;
    public static final int GetJavaVM = 219;
    public static final int GetStringRegion = 220;
    public static final int GetStringUTFRegion = 221;
    public static final int GetPrimitiveArrayCritical = 222;
    public static final int ReleasePrimitiveArrayCritical = 223;
    public static final int GetStringCritical = 224;
    public static final int ReleaseStringCritical = 225;
    public static final int NewWeakGlobalRef = 226;
    public static final int DeleteWeakGlobalRef = 227;
    public static final int ExceptionCheck = 228;
    public static final int NewDirectByteBuffer = 229;
    public static final int GetDirectBufferAddress = 230;
    public static final int GetDirectBufferCapacity = 231;
    public static final int GetObjectRefType = 232;
    public static final int NumberOfFunctions = 233;

    private final Pointer env;
    private final Function[] functions;

    public JNIEnv(Pointer env) {
        if (env == null) throw new NullPointerException("env");
        this.env = env;
        this.functions = new Function[NumberOfFunctions];
        final long addressSize = Native.POINTER_SIZE;
        final Pointer funcs = env.getPointer(0);
        for (int i = 0; i < NumberOfFunctions; i++) {
            final Pointer functionPointer = funcs.getPointer((long) i * addressSize);
            if (functionPointer == null) continue;
            functions[i] = Function.getFunction(functionPointer, Function.ALT_CONVENTION);
        }
    }

    public static JNIEnv getThreadEnv(JavaVM vm, int jniVersion) {
        return new JNIEnv(JNIUtils.getThreadEnv(vm, jniVersion));
    }

    public Pointer getHandle() {
        return env;
    }

    public Function[] getFunctions() {
        return functions;
    }

    public int GetVersion() {
        return functions[GetVersion].invokeInt(new Object[] {env});
    }

    public Pointer DefineClass(String name, Pointer loader, Pointer buf, int len) {
        return functions[DefineClass].invokePointer(new Object[] {env, name, loader, buf, new Pointer(len)});
    }

    public Pointer FindClass(String name) {
        return functions[FindClass].invokePointer(new Object[] {env, name});
    }

    public Pointer FromReflectedMethod(Pointer method) {
        return functions[FromReflectedMethod].invokePointer(new Object[] {env, method});
    }

    public Pointer FromReflectedField(Pointer field) {
        return functions[FromReflectedField].invokePointer(new Object[] {env, field});
    }

    public Pointer ToReflectedMethod(Pointer cls, Pointer methodID, boolean isStatic) {
        return functions[ToReflectedMethod].invokePointer(new Object[] {env, cls, methodID, isStatic});
    }

    public Pointer GetSuperclass(Pointer cls) {
        return functions[GetSuperclass].invokePointer(new Object[] {env, cls});
    }

    public boolean IsAssignableFrom(Pointer cls1, Pointer cls2) {
        return functions[IsAssignableFrom].invokeInt(new Object[] {env, cls1, cls2}) != 0;
    }

    public Pointer ToReflectedField(Pointer cls, Pointer fieldID, boolean isStatic) {
        return functions[ToReflectedField].invokePointer(new Object[] {env, cls, fieldID, isStatic});
    }

    public int Throw(Pointer obj) {
        return functions[Throw].invokeInt(new Object[] {env, obj});
    }

    public int ThrowNew(Pointer clazz, String msg) {
        return functions[ThrowNew].invokeInt(new Object[] {env, clazz, msg});
    }

    public Pointer ExceptionOccurred() {
        return functions[ExceptionOccurred].invokePointer(new Object[] {env});
    }

    public void ExceptionDescribe() {
        functions[ExceptionDescribe].invokeVoid(new Object[] {env});
    }

    public void ExceptionClear() {
        functions[ExceptionClear].invokeVoid(new Object[] {env});
    }

    public void FatalError(String msg) {
        functions[FatalError].invokeVoid(new Object[] {env, msg});
    }

    public int PushLocalFrame(int capacity) {
        return functions[PushLocalFrame].invokeInt(new Object[] {env, capacity});
    }

    public Pointer PopLocalFrame(Pointer result) {
        return functions[PopLocalFrame].invokePointer(new Object[] {env, result});
    }

    public Pointer NewGlobalRef(Pointer obj) {
        return functions[NewGlobalRef].invokePointer(new Object[] {env, obj});
    }

    public void DeleteGlobalRef(Pointer globalRef) {
        functions[DeleteGlobalRef].invokeVoid(new Object[] {env, globalRef});
    }

    public void DeleteLocalRef(Pointer localRef) {
        functions[DeleteLocalRef].invokeVoid(new Object[] {env, localRef});
    }

    public boolean IsSameObject(Pointer ref1, Pointer ref2) {
        return functions[IsSameObject].invokeInt(new Object[] {env, ref1, ref2}) != 0;
    }

    public Pointer NewLocalRef(Pointer ref) {
        return functions[NewLocalRef].invokePointer(new Object[] {env, ref});
    }

    public int EnsureLocalCapacity(int capacity) {
        return functions[EnsureLocalCapacity].invokeInt(new Object[] {env, capacity});
    }

    public Pointer AllocObject(Pointer cls) {
        return functions[AllocObject].invokePointer(new Object[] {env, cls});
    }

    public Pointer NewObjectV(Pointer cls, Pointer methodID, Pointer args) {
        return functions[NewObjectV].invokePointer(new Object[] {env, cls, methodID, args});
    }

    public Pointer NewObjectA(Pointer cls, Pointer methodID, Pointer args) {
        return functions[NewObjectA].invokePointer(new Object[] {env, cls, methodID, args});
    }

    public Pointer GetObjectClass(Pointer obj) {
        return functions[GetObjectClass].invokePointer(new Object[] {env, obj});
    }

    public boolean IsInstanceOf(Pointer obj, Pointer cls) {
        return functions[IsInstanceOf].invokeInt(new Object[] {env, obj, cls}) != 0;
    }

    public Pointer GetMethodID(Pointer cls, String name, String sig) {
        return functions[GetMethodID].invokePointer(new Object[] {env, cls, name, sig});
    }

    // Only use va_list and pointer types for args not the default one

    public Pointer CallObjectMethodV(Pointer obj, Pointer methodID, Pointer vaList) {
        return functions[CallObjectMethodV].invokePointer(new Object[] {env, obj, methodID, vaList});
    }

    public Pointer CallObjectMethodA(Pointer obj, Pointer methodID, Pointer args) {
        return functions[CallObjectMethodA].invokePointer(new Object[] {env, obj, methodID, args});
    }

    public boolean CallBooleanMethodV(Pointer obj, Pointer methodID, Pointer vaList) {
        return functions[CallBooleanMethodV].invokeInt(new Object[] {env, obj, methodID, vaList}) != 0;
    }

    public boolean CallBooleanMethodA(Pointer obj, Pointer methodID, Pointer args) {
        return functions[CallBooleanMethodA].invokeInt(new Object[] {env, obj, methodID, args}) != 0;
    }

    public byte CallByteMethodV(Pointer obj, Pointer methodID, Pointer vaList) {
        return (byte) (functions[CallByteMethodV].invokeInt(new Object[] {env, obj, methodID, vaList}) & 0xFF);
    }

    public byte CallByteMethodA(Pointer obj, Pointer methodID, Pointer args) {
        return (byte) (functions[CallByteMethodA].invokeInt(new Object[] {env, obj, methodID, args}) & 0xFF);
    }

    public char CallCharMethodV(Pointer obj, Pointer methodID, Pointer vaList) {
        return (char) (functions[CallCharMethodV].invokeInt(new Object[] {env, obj, methodID, vaList}) & 0xFFFF);
    }

    public char CallCharMethodA(Pointer obj, Pointer methodID, Pointer args) {
        return (char) (functions[CallCharMethodA].invokeInt(new Object[] {env, obj, methodID, args}) & 0xFFFF);
    }

    public short CallShortMethodV(Pointer obj, Pointer methodID, Pointer vaList) {
        return (short) (functions[CallShortMethodV].invokeInt(new Object[] {env, obj, methodID, vaList}) & 0xFFFF);
    }

    public short CallShortMethodA(Pointer obj, Pointer methodID, Pointer args) {
        return (short) (functions[CallShortMethodA].invokeInt(new Object[] {env, obj, methodID, args}) & 0xFFFF);
    }

    public int CallIntMethodV(Pointer obj, Pointer methodID, Pointer vaList) {
        return functions[CallIntMethodV].invokeInt(new Object[] {env, obj, methodID, vaList});
    }

    public int CallIntMethodA(Pointer obj, Pointer methodID, Pointer args) {
        return functions[CallIntMethodA].invokeInt(new Object[] {env, obj, methodID, args});
    }

    public long CallLongMethodV(Pointer obj, Pointer methodID, Pointer vaList) {
        return functions[CallLongMethodV].invokeLong(new Object[] {env, obj, methodID, vaList});
    }

    public long CallLongMethodA(Pointer obj, Pointer methodID, Pointer args) {
        return functions[CallLongMethodA].invokeLong(new Object[] {env, obj, methodID, args});
    }

    public float CallFloatMethodV(Pointer obj, Pointer methodID, Pointer vaList) {
        return functions[CallFloatMethodV].invokeFloat(new Object[] {env, obj, methodID, vaList});
    }

    public float CallFloatMethodA(Pointer obj, Pointer methodID, Pointer args) {
        return functions[CallFloatMethodA].invokeFloat(new Object[] {env, obj, methodID, args});
    }

    public double CallDoubleMethodV(Pointer obj, Pointer methodID, Pointer vaList) {
        return functions[CallDoubleMethodV].invokeDouble(new Object[] {env, obj, methodID, vaList});
    }

    public double CallDoubleMethodA(Pointer obj, Pointer methodID, Pointer args) {
        return functions[CallDoubleMethodA].invokeDouble(new Object[] {env, obj, methodID, args});
    }

    public void CallVoidMethodV(Pointer obj, Pointer methodID, Pointer vaList) {
        functions[CallVoidMethodV].invokeVoid(new Object[] {env, obj, methodID, vaList});
    }

    public void CallVoidMethodA(Pointer obj, Pointer methodID, Pointer args) {
        functions[CallVoidMethodA].invokeVoid(new Object[] {env, obj, methodID, args});
    }

    public Pointer CallNonvirtualObjectMethodV(Pointer obj, Pointer cls, Pointer methodID, Pointer vaList) {
        return functions[CallNonvirtualObjectMethodV].invokePointer(new Object[] {env, obj, cls, methodID, vaList});
    }

    public Pointer CallNonvirtualObjectMethodA(Pointer obj, Pointer cls, Pointer methodID, Pointer args) {
        return functions[CallNonvirtualObjectMethodA].invokePointer(new Object[] {env, obj, cls, methodID, args});
    }

    public boolean CallNonvirtualBooleanMethodV(Pointer obj, Pointer cls, Pointer methodID, Pointer vaList) {
        return functions[CallNonvirtualBooleanMethodV].invokeInt(new Object[] {env, obj, cls, methodID, vaList}) != 0;
    }

    public boolean CallNonvirtualBooleanMethodA(Pointer obj, Pointer cls, Pointer methodID, Pointer args) {
        return functions[CallNonvirtualBooleanMethodA].invokeInt(new Object[] {env, obj, cls, methodID, args}) != 0;
    }

    public byte CallNonvirtualByteMethodV(Pointer obj, Pointer cls, Pointer methodID, Pointer vaList) {
        return (byte) (functions[CallNonvirtualByteMethodV].invokeInt(new Object[] {env, obj, cls, methodID, vaList}) & 0xFF);
    }

    public byte CallNonvirtualByteMethodA(Pointer obj, Pointer cls, Pointer methodID, Pointer args) {
        return (byte) (functions[CallNonvirtualByteMethodA].invokeInt(new Object[] {env, obj, cls, methodID, args}) & 0xFF);
    }

    public char CallNonvirtualCharMethodV(Pointer obj, Pointer cls, Pointer methodID, Pointer vaList) {
        return (char) (functions[CallNonvirtualCharMethodV].invokeInt(new Object[] {env, obj, cls, methodID, vaList}) & 0xFFFF);
    }

    public char CallNonvirtualCharMethodA(Pointer obj, Pointer cls, Pointer methodID, Pointer args) {
        return (char) (functions[CallNonvirtualCharMethodA].invokeInt(new Object[] {env, obj, cls, methodID, args}) & 0xFFFF);
    }

    public short CallNonvirtualShortMethodV(Pointer obj, Pointer cls, Pointer methodID, Pointer vaList) {
        return (short) (functions[CallNonvirtualShortMethodV].invokeInt(new Object[] {env, obj, cls, methodID, vaList}) & 0xFFFF);
    }

    public short CallNonvirtualShortMethodA(Pointer obj, Pointer cls, Pointer methodID, Pointer args) {
        return (short) (functions[CallNonvirtualShortMethodA].invokeInt(new Object[] {env, obj, cls, methodID, args}) & 0xFFFF);
    }

    public int CallNonvirtualIntMethodV(Pointer obj, Pointer cls, Pointer methodID, Pointer vaList) {
        return functions[CallNonvirtualIntMethodV].invokeInt(new Object[] {env, obj, cls, methodID, vaList});
    }

    public int CallNonvirtualIntMethodA(Pointer obj, Pointer cls, Pointer methodID, Pointer args) {
        return functions[CallNonvirtualIntMethodA].invokeInt(new Object[] {env, obj, cls, methodID, args});
    }

    public long CallNonvirtualLongMethodV(Pointer obj, Pointer cls, Pointer methodID, Pointer vaList) {
        return functions[CallNonvirtualLongMethodV].invokeLong(new Object[] {env, obj, cls, methodID, vaList});
    }

    public long CallNonvirtualLongMethodA(Pointer obj, Pointer cls, Pointer methodID, Pointer args) {
        return functions[CallNonvirtualLongMethodA].invokeLong(new Object[] {env, obj, cls, methodID, args});
    }

    public float CallNonvirtualFloatMethodV(Pointer obj, Pointer cls, Pointer methodID, Pointer vaList) {
        return functions[CallNonvirtualFloatMethodV].invokeFloat(new Object[] {env, obj, cls, methodID, vaList});
    }

    public float CallNonvirtualFloatMethodA(Pointer obj, Pointer cls, Pointer methodID, Pointer args) {
        return functions[CallNonvirtualFloatMethodA].invokeFloat(new Object[] {env, obj, cls, methodID, args});
    }

    public double CallNonvirtualDoubleMethodV(Pointer obj, Pointer cls, Pointer methodID, Pointer vaList) {
        return functions[CallNonvirtualDoubleMethodV].invokeDouble(new Object[] {env, obj, cls, methodID, vaList});
    }

    public double CallNonvirtualDoubleMethodA(Pointer obj, Pointer cls, Pointer methodID, Pointer args) {
        return functions[CallNonvirtualDoubleMethodA].invokeDouble(new Object[] {env, obj, cls, methodID, args});
    }

    public void CallNonvirtualVoidMethodV(Pointer obj, Pointer cls, Pointer methodID, Pointer vaList) {
        functions[CallNonvirtualVoidMethodV].invokeVoid(new Object[] {env, obj, cls, methodID, vaList});
    }

    public void CallNonvirtualVoidMethodA(Pointer obj, Pointer cls, Pointer methodID, Pointer args) {
        functions[CallNonvirtualVoidMethodA].invokeVoid(new Object[] {env, obj, cls, methodID, args});
    }

    public Pointer GetFieldID(Pointer cls, String name, String sig) {
        return functions[GetFieldID].invokePointer(new Object[] {env, cls, name, sig});
    }

    public Pointer GetObjectField(Pointer obj, Pointer fieldID) {
        return functions[GetObjectField].invokePointer(new Object[] {env, obj, fieldID});
    }

    public boolean GetBooleanField(Pointer obj, Pointer fieldID) {
        return functions[GetBooleanField].invokeInt(new Object[] {env, obj, fieldID}) != 0;
    }

    public byte GetByteField(Pointer obj, Pointer fieldID) {
        return (byte) (functions[GetByteField].invokeInt(new Object[] {env, obj, fieldID}) & 0xFF);
    }

    public char GetCharField(Pointer obj, Pointer fieldID) {
        return (char) (functions[GetCharField].invokeInt(new Object[] {env, obj, fieldID}) & 0xFFFF);
    }

    public short GetShortField(Pointer obj, Pointer fieldID) {
        return (short) (functions[GetShortField].invokeInt(new Object[] {env, obj, fieldID}) & 0xFFFF);
    }

    public int GetIntField(Pointer obj, Pointer fieldID) {
        return functions[GetIntField].invokeInt(new Object[] {env, obj, fieldID});
    }

    public long GetLongField(Pointer obj, Pointer fieldID) {
        return functions[GetLongField].invokeLong(new Object[] {env, obj, fieldID});
    }

    public float GetFloatField(Pointer obj, Pointer fieldID) {
        return functions[GetFloatField].invokeFloat(new Object[] {env, obj, fieldID});
    }

    public double GetDoubleField(Pointer obj, Pointer fieldID) {
        return functions[GetDoubleField].invokeDouble(new Object[] {env, obj, fieldID});
    }

    public void SetObjectField(Pointer obj, Pointer fieldID, Pointer val) {
        functions[SetObjectField].invokeVoid(new Object[] {env, obj, fieldID, val});
    }

    public void SetBooleanField(Pointer obj, Pointer fieldID, boolean val) {
        functions[SetBooleanField].invokeVoid(new Object[] {env, obj, fieldID, val ? 1 : 0});
    }

    public void SetByteField(Pointer obj, Pointer fieldID, byte val) {
        functions[SetByteField].invokeVoid(new Object[] {env, obj, fieldID, val});
    }

    public void SetCharField(Pointer obj, Pointer fieldID, char val) {
        functions[SetCharField].invokeVoid(new Object[] {env, obj, fieldID, val});
    }

    public void SetShortField(Pointer obj, Pointer fieldID, short val) {
        functions[SetShortField].invokeVoid(new Object[] {env, obj, fieldID, val});
    }

    public void SetIntField(Pointer obj, Pointer fieldID, int val) {
        functions[SetIntField].invokeVoid(new Object[] {env, obj, fieldID, val});
    }

    public void SetLongField(Pointer obj, Pointer fieldID, long val) {
        functions[SetLongField].invokeVoid(new Object[] {env, obj, fieldID, val});
    }

    public void SetFloatField(Pointer obj, Pointer fieldID, float val) {
        functions[SetFloatField].invokeVoid(new Object[] {env, obj, fieldID, val});
    }

    public void SetDoubleField(Pointer obj, Pointer fieldID, double val) {
        functions[SetDoubleField].invokeVoid(new Object[] {env, obj, fieldID, val});
    }

    public Pointer GetStaticMethodID(Pointer cls, String name, String sig) {
        return functions[GetStaticMethodID].invokePointer(new Object[] {env, cls, name, sig});
    }

    public Pointer CallStaticObjectMethodV(Pointer cls, Pointer methodID, Pointer vaList) {
        return functions[CallStaticObjectMethodV].invokePointer(new Object[] {env, cls, methodID, vaList});
    }

    public Pointer CallStaticObjectMethodA(Pointer cls, Pointer methodID, Pointer args) {
        return functions[CallStaticObjectMethodA].invokePointer(new Object[] {env, cls, methodID, args});
    }

    public boolean CallStaticBooleanMethodV(Pointer cls, Pointer methodID, Pointer vaList) {
        return functions[CallStaticBooleanMethodV].invokeInt(new Object[] {env, cls, methodID, vaList}) != 0;
    }

    public boolean CallStaticBooleanMethodA(Pointer cls, Pointer methodID, Pointer args) {
        return functions[CallStaticBooleanMethodA].invokeInt(new Object[] {env, cls, methodID, args}) != 0;
    }

    public byte CallStaticByteMethodV(Pointer cls, Pointer methodID, Pointer vaList) {
        return (byte) (functions[CallStaticByteMethodV].invokeInt(new Object[] {env, cls, methodID, vaList}) & 0xFF);
    }

    public byte CallStaticByteMethodA(Pointer cls, Pointer methodID, Pointer args) {
        return (byte) (functions[CallStaticByteMethodA].invokeInt(new Object[] {env, cls, methodID, args}) & 0xFF);
    }

    public char CallStaticCharMethodV(Pointer cls, Pointer methodID, Pointer vaList) {
        return (char) (functions[CallStaticCharMethodV].invokeInt(new Object[] {env, cls, methodID, vaList}) & 0xFFFF);
    }

    public char CallStaticCharMethodA(Pointer cls, Pointer methodID, Pointer args) {
        return (char) (functions[CallStaticCharMethodA].invokeInt(new Object[] {env, cls, methodID, args}) & 0xFFFF);
    }

    public short CallStaticShortMethodV(Pointer cls, Pointer methodID, Pointer vaList) {
        return (short) (functions[CallStaticShortMethodV].invokeInt(new Object[] {env, cls, methodID, vaList}) & 0xFFFF);
    }

    public short CallStaticShortMethodA(Pointer cls, Pointer methodID, Pointer args) {
        return (short) (functions[CallStaticShortMethodA].invokeInt(new Object[] {env, cls, methodID, args}) & 0xFFFF);
    }

    public int CallStaticIntMethodV(Pointer cls, Pointer methodID, Pointer vaList) {
        return functions[CallStaticIntMethodV].invokeInt(new Object[] {env, cls, methodID, vaList});
    }

    public int CallStaticIntMethodA(Pointer cls, Pointer methodID, Pointer args) {
        return functions[CallStaticIntMethodA].invokeInt(new Object[] {env, cls, methodID, args});
    }

    public long CallStaticLongMethodV(Pointer cls, Pointer methodID, Pointer vaList) {
        return functions[CallStaticLongMethodV].invokeLong(new Object[] {env, cls, methodID, vaList});
    }

    public long CallStaticLongMethodA(Pointer cls, Pointer methodID, Pointer args) {
        return functions[CallStaticLongMethodA].invokeLong(new Object[] {env, cls, methodID, args});
    }

    public float CallStaticFloatMethodV(Pointer cls, Pointer methodID, Pointer vaList) {
        return functions[CallStaticFloatMethodV].invokeFloat(new Object[] {env, cls, methodID, vaList});
    }

    public float CallStaticFloatMethodA(Pointer cls, Pointer methodID, Pointer args) {
        return functions[CallStaticFloatMethodA].invokeFloat(new Object[] {env, cls, methodID, args});
    }

    public double CallStaticDoubleMethodV(Pointer cls, Pointer methodID, Pointer vaList) {
        return functions[CallStaticDoubleMethodV].invokeDouble(new Object[] {env, cls, methodID, vaList});
    }

    public double CallStaticDoubleMethodA(Pointer cls, Pointer methodID, Pointer args) {
        return functions[CallStaticDoubleMethodA].invokeDouble(new Object[] {env, cls, methodID, args});
    }

    public void CallStaticVoidMethod(Pointer cls, Pointer methodID, Object... args) {
        functions[CallStaticVoidMethod].invokeVoid(new Object[] {env, cls, methodID, args});
    }

    public void CallStaticVoidMethodV(Pointer cls, Pointer methodID, Pointer vaList) {
        functions[CallStaticVoidMethodV].invokeVoid(new Object[] {env, cls, methodID, vaList});
    }

    public void CallStaticVoidMethodA(Pointer cls, Pointer methodID, Pointer args) {
        functions[CallStaticVoidMethodA].invokeVoid(new Object[] {env, cls, methodID, args});
    }

    public Pointer GetStaticFieldID(Pointer cls, String name, String sig) {
        return functions[GetStaticFieldID].invokePointer(new Object[] {env, cls, name, sig});
    }

    public Pointer GetStaticObjectField(Pointer cls, Pointer fieldID) {
        return functions[GetStaticObjectField].invokePointer(new Object[] {env, cls, fieldID});
    }

    public boolean GetStaticBooleanField(Pointer cls, Pointer fieldID) {
        return functions[GetStaticBooleanField].invokeInt(new Object[] {env, cls, fieldID}) != 0;
    }

    public byte GetStaticByteField(Pointer cls, Pointer fieldID) {
        return (byte) (functions[GetStaticByteField].invokeInt(new Object[] {env, cls, fieldID}) & 0xFF);
    }

    public char GetStaticCharField(Pointer cls, Pointer fieldID) {
        return (char) (functions[GetStaticCharField].invokeInt(new Object[] {env, cls, fieldID}) & 0xFFFF);
    }

    public short GetStaticShortField(Pointer cls, Pointer fieldID) {
        return (short) (functions[GetStaticShortField].invokeInt(new Object[] {env, cls, fieldID}) & 0xFFFF);
    }

    public int GetStaticIntField(Pointer cls, Pointer fieldID) {
        return functions[GetStaticIntField].invokeInt(new Object[] {env, cls, fieldID});
    }

    public long GetStaticLongField(Pointer cls, Pointer fieldID) {
        return functions[GetStaticLongField].invokeLong(new Object[] {env, cls, fieldID});
    }

    public float GetStaticFloatField(Pointer cls, Pointer fieldID) {
        return functions[GetStaticFloatField].invokeFloat(new Object[] {env, cls, fieldID});
    }

    public double GetStaticDoubleField(Pointer cls, Pointer fieldID) {
        return functions[GetStaticDoubleField].invokeDouble(new Object[] {env, cls, fieldID});
    }

    public void SetStaticObjectField(Pointer cls, Pointer fieldID, Pointer val) {
        functions[SetStaticObjectField].invokeVoid(new Object[] {env, cls, fieldID, val});
    }

    public void SetStaticBooleanField(Pointer cls, Pointer fieldID, boolean val) {
        functions[SetStaticBooleanField].invokeVoid(new Object[] {env, cls, fieldID, val ? 1 : 0});
    }

    public void SetStaticByteField(Pointer cls, Pointer fieldID, byte val) {
        functions[SetStaticByteField].invokeVoid(new Object[] {env, cls, fieldID, val & 0xFF});
    }

    public void SetStaticCharField(Pointer cls, Pointer fieldID, char val) {
        functions[SetStaticCharField].invokeVoid(new Object[] {env, cls, fieldID, val & 0xFFFF});
    }

    public void SetStaticShortField(Pointer cls, Pointer fieldID, short val) {
        functions[SetStaticShortField].invokeVoid(new Object[] {env, cls, fieldID, val & 0xFFFF});
    }

    public void SetStaticIntField(Pointer cls, Pointer fieldID, int val) {
        functions[SetStaticIntField].invokeVoid(new Object[] {env, cls, fieldID, val});
    }

    public void SetStaticLongField(Pointer cls, Pointer fieldID, long val) {
        functions[SetStaticLongField].invokeVoid(new Object[] {env, cls, fieldID, val});
    }

    public void SetStaticFloatField(Pointer cls, Pointer fieldID, float val) {
        functions[SetStaticFloatField].invokeVoid(new Object[] {env, cls, fieldID, val});
    }

    public void SetStaticDoubleField(Pointer cls, Pointer fieldID, double val) {
        functions[SetStaticDoubleField].invokeVoid(new Object[] {env, cls, fieldID, val});
    }

    public Pointer NewString(Pointer unicodeChars, int len) {
        return functions[NewString].invokePointer(new Object[] {env, unicodeChars, len});
    }

    public int GetStringLength(Pointer str) {
        return functions[GetStringLength].invokeInt(new Object[] {env, str});
    }

    public Pointer GetStringChars(Pointer str, IntByReference isCopy) {
        return functions[GetStringChars].invokePointer(new Object[] {env, str, isCopy});
    }

    public void ReleaseStringChars(Pointer str, Pointer chars) {
        functions[ReleaseStringChars].invokeVoid(new Object[] {env, str, chars});
    }

    public Pointer NewStringUTF(String utf) {
        return functions[NewStringUTF].invokePointer(new Object[] {env, utf});
    }

    public int GetStringUTFLength(Pointer str) {
        return functions[GetStringUTFLength].invokeInt(new Object[] {env, str});
    }

    public Pointer GetStringUTFChars(Pointer str, IntByReference isCopy) {
        return functions[GetStringUTFChars].invokePointer(new Object[] {env, str, isCopy});
    }

    public void ReleaseStringUTFChars(Pointer str, Pointer chars) {
        functions[ReleaseStringUTFChars].invokeVoid(new Object[] {env, str, chars});
    }

    public int GetArrayLength(Pointer array) {
        return functions[GetArrayLength].invokeInt(new Object[] {env, array});
    }

    public Pointer NewObjectArray(int len, Pointer cls, Pointer init) {
        return functions[NewObjectArray].invokePointer(new Object[] {env, len, cls, init});
    }

    public Pointer GetObjectArrayElement(Pointer array, int index) {
        return functions[GetObjectArrayElement].invokePointer(new Object[] {env, array, index});
    }

    public void SetObjectArrayElement(Pointer array, int index, Pointer val) {
        functions[SetObjectArrayElement].invokeVoid(new Object[] {env, array, index, val});
    }

    public Pointer NewBooleanArray(int len) {
        return functions[NewBooleanArray].invokePointer(new Object[] {env, len});
    }

    public Pointer NewByteArray(int len) {
        return functions[NewByteArray].invokePointer(new Object[] {env, len});
    }

    public Pointer NewCharArray(int len) {
        return functions[NewCharArray].invokePointer(new Object[] {env, len});
    }

    public Pointer NewShortArray(int len) {
        return functions[NewShortArray].invokePointer(new Object[] {env, len});
    }

    public Pointer NewIntArray(int len) {
        return functions[NewIntArray].invokePointer(new Object[] {env, len});
    }

    public Pointer NewLongArray(int len) {
        return functions[NewLongArray].invokePointer(new Object[] {env, len});
    }

    public Pointer NewFloatArray(int len) {
        return functions[NewFloatArray].invokePointer(new Object[] {env, len});
    }

    public Pointer NewDoubleArray(int len) {
        return functions[NewDoubleArray].invokePointer(new Object[] {env, len});
    }

    public Pointer GetBooleanArrayElements(Pointer array, IntByReference isCopy) {
        return functions[GetBooleanArrayElements].invokePointer(new Object[] {env, array, isCopy});
    }

    public Pointer GetByteArrayElements(Pointer array, IntByReference isCopy) {
        return functions[GetByteArrayElements].invokePointer(new Object[] {env, array, isCopy});
    }

    public Pointer GetCharArrayElements(Pointer array, IntByReference isCopy) {
        return functions[GetCharArrayElements].invokePointer(new Object[] {env, array, isCopy});
    }

    public Pointer GetShortArrayElements(Pointer array, IntByReference isCopy) {
        return functions[GetShortArrayElements].invokePointer(new Object[] {env, array, isCopy});
    }

    public Pointer GetIntArrayElements(Pointer array, IntByReference isCopy) {
        return functions[GetIntArrayElements].invokePointer(new Object[] {env, array, isCopy});
    }

    public Pointer GetLongArrayElements(Pointer array, IntByReference isCopy) {
        return functions[GetLongArrayElements].invokePointer(new Object[] {env, array, isCopy});
    }

    public Pointer GetFloatArrayElements(Pointer array, IntByReference isCopy) {
        return functions[GetFloatArrayElements].invokePointer(new Object[] {env, array, isCopy});
    }

    public Pointer GetDoubleArrayElements(Pointer array, IntByReference isCopy) {
        return functions[GetDoubleArrayElements].invokePointer(new Object[] {env, array, isCopy});
    }

    public void ReleaseBooleanArrayElements(Pointer array, Pointer elems, int mode) {
        functions[ReleaseBooleanArrayElements].invokeVoid(new Object[] {env, array, elems, mode});
    }

    public void ReleaseByteArrayElements(Pointer array, Pointer elems, int mode) {
        functions[ReleaseByteArrayElements].invokeVoid(new Object[] {env, array, elems, mode});
    }

    public void ReleaseCharArrayElements(Pointer array, Pointer elems, int mode) {
        functions[ReleaseCharArrayElements].invokeVoid(new Object[] {env, array, elems, mode});
    }

    public void ReleaseShortArrayElements(Pointer array, Pointer elems, int mode) {
        functions[ReleaseShortArrayElements].invokeVoid(new Object[] {env, array, elems, mode});
    }

    public void ReleaseIntArrayElements(Pointer array, Pointer elems, int mode) {
        functions[ReleaseIntArrayElements].invokeVoid(new Object[] {env, array, elems, mode});
    }

    public void ReleaseLongArrayElements(Pointer array, Pointer elems, int mode) {
        functions[ReleaseLongArrayElements].invokeVoid(new Object[] {env, array, elems, mode});
    }

    public void ReleaseFloatArrayElements(Pointer array, Pointer elems, int mode) {
        functions[ReleaseFloatArrayElements].invokeVoid(new Object[] {env, array, elems, mode});
    }

    public void ReleaseDoubleArrayElements(Pointer array, Pointer elems, int mode) {
        functions[ReleaseDoubleArrayElements].invokeVoid(new Object[] {env, array, elems, mode});
    }

    public void GetBooleanArrayRegion(Pointer array, int start, int len, Pointer buf) {
        functions[GetBooleanArrayRegion].invokeVoid(new Object[] {env, array, start, len, buf});
    }

    public void GetByteArrayRegion(Pointer array, int start, int len, Pointer buf) {
        functions[GetByteArrayRegion].invokeVoid(new Object[] {env, array, start, len, buf});
    }

    public void GetCharArrayRegion(Pointer array, int start, int len, Pointer buf) {
        functions[GetCharArrayRegion].invokeVoid(new Object[] {env, array, start, len, buf});
    }

    public void GetShortArrayRegion(Pointer array, int start, int len, Pointer buf) {
        functions[GetShortArrayRegion].invokeVoid(new Object[] {env, array, start, len, buf});
    }

    public void GetIntArrayRegion(Pointer array, int start, int len, Pointer buf) {
        functions[GetIntArrayRegion].invokeVoid(new Object[] {env, array, start, len, buf});
    }

    public void GetLongArrayRegion(Pointer array, int start, int len, Pointer buf) {
        functions[GetLongArrayRegion].invokeVoid(new Object[] {env, array, start, len, buf});
    }

    public void GetFloatArrayRegion(Pointer array, int start, int len, Pointer buf) {
        functions[GetFloatArrayRegion].invokeVoid(new Object[] {env, array, start, len, buf});
    }

    public void GetDoubleArrayRegion(Pointer array, int start, int len, Pointer buf) {
        functions[GetDoubleArrayRegion].invokeVoid(new Object[] {env, array, start, len, buf});
    }

    public void SetBooleanArrayRegion(Pointer array, int start, int len, Pointer buf) {
        functions[SetBooleanArrayRegion].invokeVoid(new Object[] {env, array, start, len, buf});
    }

    public void SetByteArrayRegion(Pointer array, int start, int len, Pointer buf) {
        functions[SetByteArrayRegion].invokeVoid(new Object[] {env, array, start, len, buf});
    }

    public void SetCharArrayRegion(Pointer array, int start, int len, Pointer buf) {
        functions[SetCharArrayRegion].invokeVoid(new Object[] {env, array, start, len, buf});
    }

    public void SetShortArrayRegion(Pointer array, int start, int len, Pointer buf) {
        functions[SetShortArrayRegion].invokeVoid(new Object[] {env, array, start, len, buf});
    }

    public void SetIntArrayRegion(Pointer array, int start, int len, Pointer buf) {
        functions[SetIntArrayRegion].invokeVoid(new Object[] {env, array, start, len, buf});
    }

    public void SetLongArrayRegion(Pointer array, int start, int len, Pointer buf) {
        functions[SetLongArrayRegion].invokeVoid(new Object[] {env, array, start, len, buf});
    }

    public void SetFloatArrayRegion(Pointer array, int start, int len, Pointer buf) {
        functions[SetFloatArrayRegion].invokeVoid(new Object[] {env, array, start, len, buf});
    }

    public void SetDoubleArrayRegion(Pointer array, int start, int len, Pointer buf) {
        functions[SetDoubleArrayRegion].invokeVoid(new Object[] {env, array, start, len, buf});
    }

    public int RegisterNatives(Pointer clazz, Pointer methods, int nMethods) {
        return functions[RegisterNatives].invokeInt(new Object[] {env, clazz, methods, nMethods});
    }

    public int UnregisterNatives(Pointer clazz) {
        return functions[UnregisterNatives].invokeInt(new Object[] {env, clazz});
    }

    public int MonitorEnter(Pointer obj) {
        return functions[MonitorEnter].invokeInt(new Object[] {env, obj});
    }

    public int MonitorExit(Pointer obj) {
        return functions[MonitorExit].invokeInt(new Object[] {env, obj});
    }

    public int GetJavaVM(PointerByReference vm) {
        return functions[GetJavaVM].invokeInt(new Object[] {env, vm});
    }

    public int GetStringRegion(Pointer str, int start, int len, Pointer buf) {
        return functions[GetStringRegion].invokeInt(new Object[] {env, str, start, len, buf});
    }

    public int GetStringUTFRegion(Pointer str, int start, int len, Pointer buf) {
        return functions[GetStringUTFRegion].invokeInt(new Object[] {env, str, start, len, buf});
    }

    public Pointer GetPrimitiveArrayCritical(Pointer array, IntByReference isCopy) {
        return functions[GetPrimitiveArrayCritical].invokePointer(new Object[] {env, array, isCopy});
    }

    public void ReleasePrimitiveArrayCritical(Pointer array, Pointer carray, int mode) {
        functions[ReleasePrimitiveArrayCritical].invokeVoid(new Object[] {env, array, carray, mode});
    }

    public Pointer GetStringCritical(Pointer str, IntByReference isCopy) {
        return functions[GetStringCritical].invokePointer(new Object[] {env, str, isCopy});
    }

    public void ReleaseStringCritical(Pointer str, Pointer cstring) {
        functions[ReleaseStringCritical].invokeVoid(new Object[] {env, str, cstring});
    }

    public Pointer NewWeakGlobalRef(Pointer obj) {
        return functions[NewWeakGlobalRef].invokePointer(new Object[] {env, obj});
    }

    public void DeleteWeakGlobalRef(Pointer obj) {
        functions[DeleteWeakGlobalRef].invokeVoid(new Object[] {env, obj});
    }

    public boolean ExceptionCheck() {
        return functions[ExceptionCheck].invokeInt(new Object[] {env}) != 0;
    }

    public Pointer NewDirectByteBuffer(Pointer address, long capacity) {
        return functions[NewDirectByteBuffer].invokePointer(new Object[] {env, address, capacity});
    }

    public Pointer GetDirectBufferAddress(Pointer buf) {
        return functions[GetDirectBufferAddress].invokePointer(new Object[] {env, buf});
    }

    public long GetDirectBufferCapacity(Pointer buf) {
        return functions[GetDirectBufferCapacity].invokeLong(new Object[] {env, buf});
    }

    public int GetObjectRefType(Pointer obj) {
        return functions[GetObjectRefType].invokeInt(new Object[] {env, obj});
    }
}

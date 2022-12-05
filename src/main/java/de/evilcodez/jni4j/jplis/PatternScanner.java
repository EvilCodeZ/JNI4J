package de.evilcodez.jni4j.jplis;

import com.sun.jna.Platform;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.Psapi;
import com.sun.jna.platform.win32.WinDef;
import de.evilcodez.jni4j.JNIUtils;
import de.evilcodez.jni4j.utils.LibCExt;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public class PatternScanner {

    private static Pointer findPattern0(int[] arr, Pointer start, Pointer end) {
        Pointer ptr = start;
        int in = 0;
        while (!ptr.equals(end)) {
            boolean found = true;
            final byte[] bytes = ptr.getByteArray(0, arr.length);
            for (int i = 0; i < arr.length; i++) {
                final int b = arr[i];
                if (b == -1) continue;
                if (bytes[i] != (byte) b) {
                    found = false;
                    break;
                }
            }
            if (found) return ptr;
            ptr = ptr.share(1);
        }
        return null;
    }

    private static int[] parsePattern(String pattern) {
        final String[] split = pattern.split("( )+");
        final int[] arr = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            final String s = split[i];
            arr[i] = s.equals("?") ? -1 : Integer.parseInt(s, 16);
        }
        return arr;
    }

    public static Pointer findPattern(String pattern, Pointer start, Pointer end) {
        return findPattern0(parsePattern(pattern), start, end);
    }

    private static Pointer findPattern(Object moduleBase, String pattern) {
        final Pointer modulePointer = getModuleBasePointer(moduleBase);
        final long moduleSize = getModuleSize(moduleBase);
        final int[] arr = parsePattern(pattern);
        if (arr.length > moduleSize) return null;
        return findPattern0(arr, modulePointer, modulePointer.share(moduleSize - arr.length));
    }

    public static Pointer findPattern(String moduleName, String pattern) {
        final Object mod = getModuleBase(moduleName);
        if (mod == null) return null;
        return findPattern(mod, pattern);
    }

    public static Pointer findAnyPattern(String moduleName, String... patterns) {
        for (String pattern : patterns) {
            final Pointer ptr = findPattern(moduleName, pattern);
            if (ptr != null) return ptr;
        }
        return null;
    }

    public static Pointer findSymbolOrAnyPattern(String moduleName, String symbol, String... patterns) {
        final Object mod = getModuleBase(moduleName);
        if (mod == null) return null;
        final Pointer ptr = findSymbol(mod, symbol);
        if (ptr != null) return ptr;
        return findAnyPattern(moduleName, patterns);
    }

    private static int findSectionByName(ByteBuffer buf, long e_shoff, int e_shentsize, int e_shnum, int shstrtab, String sectionName) {
        for (int i = 0; i < e_shnum; i++) {
            final int sectionHeader = (int) (e_shoff + (long) i * e_shentsize);
            final String name = getSectionName(buf, sectionHeader, shstrtab);
            if (name.equals(sectionName)) {
                return sectionHeader;
            }
        }
        return -1;
    }

    private static int findSegmentByVA(ByteBuffer buf, boolean is64, long e_phoff, int e_phentsize, int e_phnum, int va) {
        for (int i = 0; i < e_phnum; i++) {
            final int programHeader = (int) (e_phoff + (long) i * e_phentsize);
            final long p_vaddr = is64 ? buf.getLong(programHeader + 0x10) : buf.getInt(programHeader + 0x08);
            final long p_filesz = is64 ? buf.getLong(programHeader + 0x20) : buf.getInt(programHeader + 0x10);
            if (p_filesz == 0) continue;

            if (va >= p_vaddr && va < p_vaddr + p_filesz) {
                return programHeader;
            }
        }
        return -1;
    }

    private static String getSectionName(ByteBuffer buf, int section, int shstrtab) {
        final int sh_name = buf.getInt(section);
        return readStringAt(buf, shstrtab + sh_name);
    }

    // Read a null-terminated string
    private static String readStringAt(ByteBuffer buf, int offset) {
        int j = offset;
        byte b;
        final ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
        while ((b = buf.get(j++)) != 0) {
            byteOutput.write(b);
        }
        return new String(byteOutput.toByteArray(), StandardCharsets.UTF_8);
    }

    // https://en.wikipedia.org/wiki/Executable_and_Linkable_Format
    // https://docs.oracle.com/cd/E23824_01/html/819-0690/chapter6-79797.html
    // https://docs.oracle.com/cd/E23824_01/html/819-0690/chapter6-79797.html#chapter6-35166
    public static Pointer findSymbol(Object moduleBase, String symbolName) {
        try {
            if (isAnyOSUsingElf()) {
                // Unfortunately, the section headers are not mapped into memory, so we have to read the ELF file from the disk
                final LibCExt.dl_phdr_info info = (LibCExt.dl_phdr_info) moduleBase;
                final String moduleName = info.dlpi_name.getString(0L);
                final File file = new File(moduleName);
                if (!file.exists()) return null;
                final ByteBuffer buf = ByteBuffer.wrap(Files.readAllBytes(file.toPath())).order(ByteOrder.nativeOrder());
                validateElfHeader(buf);
                final boolean is64 = buf.get(4) == 2; // e_ident[EI_CLASS]
                final boolean isLittleEndian = buf.get(5) == 1; // e_ident[EI_DATA]
                buf.order(isLittleEndian ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN);
                final long e_phoff = is64 ? buf.getLong(0x20) : buf.getInt(0x1C);
                final int e_phentsize = buf.getShort(is64 ? 0x36 : 0x2A);
                final int e_phnum = buf.getShort(is64 ? 0x38 : 0x2C);
                final long e_shoff = buf.getLong(is64 ? 0x28 : 0x20);
                final int e_shentsize = buf.getShort(is64 ? 0x3A : 0x2E) & 0xFFFF;
                final int e_shnum = buf.getShort(is64 ? 0x3C : 0x30) & 0xFFFF;
                final int e_shstrndx = buf.getShort(is64 ? 0x3E : 0x32) & 0xFFFF;
                final int shstrtab = (int) e_shoff + e_shstrndx * e_shentsize; // Section Header String Table
                final int shstrtab_off = is64 ? (int) buf.getLong(shstrtab + 0x18) : buf.getInt(shstrtab + 0x10);
                final int symtab = findSectionByName(buf, e_shoff, e_shentsize, e_shnum, shstrtab_off, ".symtab");
                final int strtab = findSectionByName(buf, e_shoff, e_shentsize, e_shnum, shstrtab_off, ".strtab");

                if (symtab == -1 || strtab == -1 || e_shstrndx == 0xFFFF) return null;

                final int strtab_off = is64 ? (int) buf.getLong(strtab + 0x18) : buf.getInt(strtab + 0x10);
                final int strtab_size = is64 ? (int) buf.getLong(strtab + 0x20) : buf.getInt(strtab + 0x14);
                final int symtab_off = is64 ? (int) buf.getLong(symtab + 0x18) : buf.getInt(symtab + 0x10);
                final int symtab_size = is64 ? (int) buf.getLong(symtab + 0x20) : buf.getInt(symtab + 0x14);
                if (strtab_size == 0 || symtab_size == 0) return null;

                // struct Elf32_Sym: size = 16
                // struct Elf64_Sym: size = 24
                long symbolVA = -1L;
                final int stride = is64 ? 24 : 16;
                for (int off = symtab_off; off < symtab_off + symtab_size; off += stride) {
                    // Read .symtab entries until we find the symbol we're looking for
                    final int st_name = buf.getInt(off);
                    final String name = readStringAt(buf, strtab_off + st_name);
                    final long st_value = is64 ? buf.getLong(off + 8) : buf.getInt(off + 4);
                    if (name.equals(symbolName)) {
                        symbolVA = st_value;
                        break;
                    }
                }
                if (symbolVA == -1) return null;

                final int symbolSegment = findSegmentByVA(buf, is64, e_phoff, e_phentsize, e_phnum, (int) symbolVA); // Finds the segment to which the symbol belongs
                if (symbolSegment == -1) return null;
                final int segmentIndex = (int) ((symbolSegment - e_phoff) / e_phentsize); // Calculates the index of the segment from the segment header's offset
                int p_vaddr_off = symbolSegment + (is64 ? 0x10 : 0x08);
                long p_vaddr = is64 ? buf.getLong(p_vaddr_off) : buf.getInt(p_vaddr_off);
                final long rva = symbolVA - p_vaddr; // Symbol Address relative to the segment

                final Pointer base = info.dlpi_addr;
                final int mem_e_phoff = is64 ? base.getInt(0x20) : base.getInt(0x1C);
                p_vaddr_off = mem_e_phoff + segmentIndex * e_phentsize + (is64 ? 0x10 : 0x08);
                p_vaddr = is64 ? base.getLong(p_vaddr_off) : base.getInt(p_vaddr_off);
                final long realVA = Pointer.nativeValue(base) + p_vaddr + rva;
                return new Pointer(realVA);
            }
        }catch (Throwable e) {
            JNIUtils.sneakThrow(e);
        }
        return null;
    }

    public static Pointer findSymbol(String moduleName, String symbolName) {
        final Object mod = getModuleBase(moduleName);
        if (mod == null) return null;
        return findSymbol(mod, symbolName);
    }

    public static Object getModuleBase(String moduleName) {
        if (Platform.isWindows() || Platform.isWindowsCE()) {
            return Kernel32.INSTANCE.GetModuleHandle(moduleName);
        } else if (isAnyOSUsingElf()) {
            final String soName = "lib" + moduleName + ".so";
            final AtomicReference<LibCExt.dl_phdr_info> ref = new AtomicReference<>();
            LibCExt.INSTANCE.dl_iterate_phdr((info, size, data) -> {
                String name = info.dlpi_name.getString(0L);
                if (name.contains("/")) name = name.substring(name.lastIndexOf('/') + 1);
                if (name.contains(".so") && !name.endsWith(".so")) name = name.substring(0, name.lastIndexOf(".so"));
                if (soName.equals(name)) ref.set(info);
                return 0;
            }, null);
            return ref.get();
        } else {
            throw new UnsupportedOperationException("Unsupported platform");
        }
    }

    public static long getModuleSize(Object moduleBase) {
        if (Platform.isWindows() || Platform.isWindowsCE()) {
            final Psapi.MODULEINFO info = new Psapi.MODULEINFO();
            if (!Psapi.INSTANCE.GetModuleInformation(Kernel32.INSTANCE.GetCurrentProcess(), (WinDef.HMODULE) moduleBase, info, info.size())) {
                return 0L;
            }
            return info.SizeOfImage;
        } else if (isAnyOSUsingElf()) {
            final LibCExt.dl_phdr_info info = (LibCExt.dl_phdr_info) moduleBase;
            Pointer ptr = info.dlpi_addr;
            validateElfHeader(ptr);
            final boolean is64 = ptr.getByte(4) == 2; // ELFHeader.e_ident[EI_CLASS]

            // We have to calculate the size of the module by ourselves, because I don't know how to get it using api calls
            final long e_shoff	 = is64 ? ptr.getLong(0x28) : ptr.getInt(0x20);
            final int e_shentsize = ptr.getShort(is64 ? 0x3A : 0x2E) & 0xFFFF;
            final int e_shnum = ptr.getShort(is64 ? 0x3C : 0x30) & 0xFFFF;
            return e_shoff + (long) e_shentsize * e_shnum; // Assumes that the section headers are at the end of the file
        } else {
            throw new UnsupportedOperationException("Unsupported platform");
        }
    }

    public static Pointer getModuleBasePointer(Object moduleBase) {
        if (Platform.isWindows() || Platform.isWindowsCE()) {
            return ((WinDef.HMODULE) moduleBase).getPointer();
        } else if (isAnyOSUsingElf()) {
            return ((LibCExt.dl_phdr_info) moduleBase).dlpi_addr;
        } else {
            throw new UnsupportedOperationException("Unsupported platform");
        }
    }

    private static void validateElfHeader(Pointer elfHeader) {
        final byte[] magic = elfHeader.getByteArray(0L, 4);
        if (!Arrays.equals(magic, new byte[] {0x7F, 'E', 'L', 'F'})) throw new IllegalArgumentException("Invalid ELF header");
    }

    private static void validateElfHeader(ByteBuffer elfHeader) {
        final byte[] magic = new byte[4];
        final int pos = elfHeader.position();
        elfHeader.get(magic);
        elfHeader.position(pos);
        if (!Arrays.equals(magic, new byte[] {0x7F, 'E', 'L', 'F'})) throw new IllegalArgumentException("Invalid ELF header");
    }

    private static boolean isAnyOSUsingElf() {
        return Platform.isLinux()
                || Platform.isGNU()
                || Platform.isFreeBSD()
                || Platform.isOpenBSD()
                || Platform.isNetBSD()
                || Platform.iskFreeBSD()
                || Platform.isAndroid()
                || Platform.isMac();
    }
}

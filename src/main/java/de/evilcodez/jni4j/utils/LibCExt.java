package de.evilcodez.jni4j.utils;

import com.sun.jna.*;
import com.sun.jna.platform.unix.LibCAPI;

public interface LibCExt extends Library {

    LibCExt INSTANCE = Native.load("c", LibCExt.class);

    int dl_iterate_phdr(dl_phdr_info_callback info, Pointer data);

    @Structure.FieldOrder({"dlpi_addr", "dlpi_name", "dlpi_phdr", "dlpi_phnum"})
    class dl_phdr_info extends Structure {
        public Pointer dlpi_addr;
        public Pointer dlpi_name;
        public Pointer dlpi_phdr;
        public short dlpi_phnum;

        public dl_phdr_info() {}

        public dl_phdr_info(Pointer p) {
            super(p);
            this.read();
        }
    }

    interface dl_phdr_info_callback extends Callback {
        int callback(LibCExt.dl_phdr_info info, LibCAPI.size_t size, Pointer data);
    }
}

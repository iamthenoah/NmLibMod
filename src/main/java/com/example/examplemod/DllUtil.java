package com.example.examplemod;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public final class DllUtil {

    public static String dllArch() {
        var arch = System.getProperty("os.arch");

        if ("amd64".equals(arch) || "x86_64".equals(arch)) {
            return "x64";
        } else if ("x86".equals(arch) || "i386".equals(arch)) {
            return "Win32";
        } else if ("aarch64".equals(arch) || "arm64".equals(arch)) {
            return "arm64";
        } else {
            throw new UnsupportedOperationException("Unsupported architecture: " + arch);
        }
    }

    public static void load(String arch, String dll) {
        try {
            var tmp = new File(System.getProperty("java.io.tmpdir")).getCanonicalPath();
            var tempDir = new File(tmp, "BleAdvertiser");
            var file = new File(tempDir, dll);

//            if (!file.exists()) {
                var input = DllUtil.class.getResourceAsStream("/" + Path.of(arch, dll));

                if (input != null && (tempDir.exists() || tempDir.mkdirs())) {
                    Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                } else {
                    throw new RuntimeException("Failed to get DLL resource or create directory");
                }
//            }
            System.load(file.getAbsolutePath());
        } catch (Exception exception) {
            throw new RuntimeException("Failed to copy DLL to temp dir", exception);
        }
    }
}

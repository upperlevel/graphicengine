package xyz.upperlevel.ulge.util;

import java.io.File;

public final class FileUtil {
    private FileUtil() {
    }

    public static String getExtension(File file) {
        return getExtension(file.getPath());
    }

    public static String getExtension(String filename) {
        try {
            return filename.substring(filename.lastIndexOf(".") + 1);
        } catch (Exception ignored) {
            return "";
        }
    }

    public static String stripExtension(String filename) {
        return filename.replaceFirst("[.][^.]+$", "");
    }

    public static String stripExtension(File file) {
        return stripExtension(file.getName());
    }
}

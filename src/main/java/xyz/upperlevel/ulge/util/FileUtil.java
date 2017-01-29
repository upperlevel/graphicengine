package xyz.upperlevel.ulge.util;

import java.io.File;

public final class FileUtil {

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

    private FileUtil() {}
}

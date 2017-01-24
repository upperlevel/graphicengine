package xyz.upperlevel.ulge.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.File;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileUtil {

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
}

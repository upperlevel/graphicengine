package xyz.upperlevel.ulge.opengl.texture.loader;


import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ImageFormat {

    public static final ImageFormat
            JPEG = new ImageFormat("jpeg", "jpg"),
            PNG = new ImageFormat("png"),
            TGA = new ImageFormat("tga"),
            BMP = new ImageFormat("bmp"),
            PSD = new ImageFormat("psd"),
            GIF = new ImageFormat("gif"),
            HDR = new ImageFormat("hdr"),
            PIC = new ImageFormat("pic"),
            PNM = new ImageFormat("ppm", "pgm", "pnm");

    @Getter public final List<String> formats;

    public ImageFormat(String... extensions) {
        formats = Collections.unmodifiableList(Arrays.asList(extensions));
    }
}

package xyz.upperlevel.graphicengine.api.opengl.texture.loader;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TextureFormat {

    public static final TextureFormat
            JPEG = new TextureFormat("jpeg", "jpg"),
            PNG = new TextureFormat("png"),
            TGA = new TextureFormat("tga"),
            BMP = new TextureFormat("bmp"),
            PSD = new TextureFormat("psd"),
            GIF = new TextureFormat("gif"),
            HDR = new TextureFormat("hdr"),
            PIC = new TextureFormat("pic"),
            PNM = new TextureFormat("ppm", "pgm", "pnm");

    @Getter public final List<String> formats;

    public TextureFormat(String... extensions) {
        formats = Collections.unmodifiableList(Arrays.asList(extensions));
    }
}

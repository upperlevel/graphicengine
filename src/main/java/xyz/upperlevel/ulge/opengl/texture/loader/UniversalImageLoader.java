package xyz.upperlevel.ulge.opengl.texture.loader;

import org.lwjgl.stb.STBImage;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.lwjgl.BufferUtils.createIntBuffer;

public class UniversalImageLoader implements ImageLoader {

    public static final UniversalImageLoader INSTANCE = new UniversalImageLoader();

    public static final List<ImageFormat> SUPPORTED = Collections.unmodifiableList(Arrays.asList(
            ImageFormat.JPEG,
            ImageFormat.PNG,
            ImageFormat.TGA,
            ImageFormat.PSD,
            ImageFormat.GIF,
            ImageFormat.HDR,
            ImageFormat.PIC,
            ImageFormat.PNM
    ));

    @Override
    public List<ImageFormat> getSupportedExtensions() {
        return SUPPORTED;
    }

    @Override
    public ImageContent load(File file) {
        IntBuffer width = createIntBuffer(1),
                height = createIntBuffer(1),
                comp = createIntBuffer(1);
        ByteBuffer data = STBImage.stbi_load(file.getPath(), width, height, comp, STBImage.STBI_rgb_alpha);
        return new ImageContent(
                width.get(),
                height.get(),
                data
        );
    }
}

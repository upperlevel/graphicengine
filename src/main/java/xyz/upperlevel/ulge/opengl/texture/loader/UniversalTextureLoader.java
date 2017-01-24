package xyz.upperlevel.ulge.opengl.texture.loader;

import org.lwjgl.stb.STBImage;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.lwjgl.BufferUtils.createIntBuffer;

public class UniversalTextureLoader implements TextureLoader {

    public static final UniversalTextureLoader INSTANCE = new UniversalTextureLoader();

    public static final List<TextureFormat> SUPPORTED = Collections.unmodifiableList(Arrays.asList(
            TextureFormat.JPEG,
            TextureFormat.PNG,
            TextureFormat.TGA,
            TextureFormat.PSD,
            TextureFormat.GIF,
            TextureFormat.HDR,
            TextureFormat.PIC,
            TextureFormat.PNM
    ));

    @Override
    public List<TextureFormat> getSupportedExtensions() {
        return SUPPORTED;
    }

    @Override
    public TextureContent load(File file) {
        IntBuffer width = createIntBuffer(1),
                height = createIntBuffer(1),
                comp = createIntBuffer(1);
        ByteBuffer data = STBImage.stbi_load(file.getPath(), width, height, comp, STBImage.STBI_rgb_alpha);
        return new TextureContent(
                width.get(),
                height.get(),
                data
        );
    }
}

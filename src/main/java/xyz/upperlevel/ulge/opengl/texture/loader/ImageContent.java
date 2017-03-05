package xyz.upperlevel.ulge.opengl.texture.loader;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.lwjgl.BufferUtils;
import xyz.upperlevel.ulge.opengl.buffer.BufferUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.util.Hashtable;
import java.util.Objects;

public class ImageContent {

    private static final ColorModel OPENGL_ALPHA_MODEL = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB),
            new int[]{8, 8, 8, 8},
            true,
            false,
            ComponentColorModel.TRANSLUCENT,
            DataBuffer.TYPE_BYTE
    );

    @Getter
    private int width, height;

    @Getter
    @Setter
    @NonNull
    private ByteBuffer data;

    public ImageContent(int width, int height, ByteBuffer data) {
        setWidth(width);
        setHeight(height);
        setData(data);
    }

    public ImageContent(BufferedImage image) {
        this(
                image.getWidth(),
                image.getHeight(),
                imageToByteBuffer(image)
        );
    }

    public ImageContent setWidth(int width) {
        if (width < 0)
            throw new IllegalArgumentException("Width cannot be negative.");
        this.width = width;
        return this;
    }

    public ImageContent setHeight(int height) {
        if (height < 0)
            throw new IllegalArgumentException("Height cannot be negative.");
        this.height = height;
        return this;
    }

    public static ByteBuffer imageToByteBuffer(BufferedImage image) {
        final int BYTES_PER_PIXELS = 4;

        int w = image.getWidth();
        int h = image.getHeight();

        int[] pixels = new int[w * h];
        image.getRGB(0, 0, w, h, pixels, 0, w);
        ByteBuffer buffer = BufferUtils.createByteBuffer(w * h * BYTES_PER_PIXELS);
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int pixel = pixels[y * image.getWidth() + x];
                buffer.put((byte) ((pixel >> 16) & 0xFF));    // red component
                buffer.put((byte) ((pixel >> 8)  & 0xFF));    // green component
                buffer.put((byte) (pixel         & 0xFF));    // blue component
                buffer.put((byte) ((pixel >> 24) & 0xFF));    // alpha component
            }
        }
        buffer.flip();
        return buffer;
    }

    public static ImageContent fromResource(String path, Class<?> clazz) {
        return UniversalImageLoader.INSTANCE.load(new File(clazz.getClassLoader().getResource(path).getFile()));
    }
}

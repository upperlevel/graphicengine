package xyz.upperlevel.ulge.opengl.texture.loader;

import lombok.Getter;
import org.lwjgl.BufferUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
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
    private ByteBuffer data;

    public ImageContent(int width, int height, ByteBuffer data) {
        setWidth(width);
        setHeight(height);
        setData(data);
    }

    public ImageContent(BufferedImage image, boolean flip) {
        this(
                image.getWidth(),
                image.getHeight(),
                imageToByteBuffer(image, flip)
        );
    }

    public void setWidth(int width) {
        if (width < 0)
            throw new IllegalArgumentException("Width cannot be negative.");
        this.width = width;
    }

    public void setHeight(int height) {
        if (height < 0)
            throw new IllegalArgumentException("Height cannot be negative.");
        this.height = height;
    }

    public void setData(ByteBuffer data) {
        Objects.requireNonNull(data, "Data cannot be null.");
        this.data = data;
    }

    public static ByteBuffer imageToByteBuffer(BufferedImage image, boolean flip) {
        BufferedImage texImage = createNormalizedImage(image, flip);

        // build a byte buffer from the temporary image
        // that be used by OpenGL to produce a texture.
        /*
        byte[] data = ((DataBufferByte) texImage.getRaster().getDataBuffer()).getData();

        ByteBuffer imageBuffer = ByteBuffer.allocateDirect(data.length);
        imageBuffer.order(ByteOrder.nativeOrder());
        imageBuffer.put(data, 0, data.length);
        imageBuffer.flip();
        */

        // todo LOADS ONLY RGBA IMAGES!
        final int BYTES_PER_PIXELS = 4;

        int w = texImage.getWidth();
        int h = texImage.getHeight();

        int[] pixels = new int[w * h];
        texImage.getRGB(0, 0, w, h, pixels, 0, w);
        ByteBuffer buffer = BufferUtils.createByteBuffer(w * h * BYTES_PER_PIXELS);
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int pixel = pixels[y * texImage.getWidth() + x];
                buffer.put((byte) ((pixel >> 16) & 0xFF));    // red component
                buffer.put((byte) ((pixel >> 8) & 0xFF));     // green component
                buffer.put((byte) (pixel & 0xFF));            // blue component
                buffer.put((byte) ((pixel >> 24) & 0xFF));    // alpha component
            }
        }
        buffer.flip();
        return buffer;
    }

    public static BufferedImage createNormalizedImage(BufferedImage image, boolean flip) {
        int texWidth = 2;
        int texHeight = 2;

        // find the closest power of 2 for the rw and rh
        // of the produced texture

        int width = image.getWidth();
        int height = image.getHeight();

        while (texWidth < width)
            texWidth *= 2;

        while (texHeight < height)
            texHeight *= 2;

        //System.out.println("W: " + width + "->" + texWidth + ", H:" + height + "->" + texHeight);

        WritableRaster raster = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE, texWidth, texHeight, 4, null);
        BufferedImage texImage = new BufferedImage(OPENGL_ALPHA_MODEL, raster, false, new Hashtable());

        // copy the source image into the produced image
        Graphics2D g = (Graphics2D) texImage.getGraphics();

        // only need to blank the image for mac compatibility if we're using alpha
        g.setColor(new Color(0f, 0f, 0f, 0f));
        g.fillRect(0, 0, texWidth, texHeight);

        if (flip) {
            g.scale(1, -1);
            g.drawImage(image, 0, -height, null);
        } else {
            g.drawImage(image, 0, 0, null);
        }
        g.dispose();
        return texImage;
    }

    public static ImageContent fromStream(InputStream in) {
        try {
            return new ImageContent(
                    ImageIO.read(in),
                    false
            );
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static ImageContent fromResource(String path, Class clazz) {
        return fromStream(clazz.getClassLoader().getResourceAsStream(path));
    }
}

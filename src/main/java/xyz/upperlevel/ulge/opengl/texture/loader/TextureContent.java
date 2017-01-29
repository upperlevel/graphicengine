package xyz.upperlevel.ulge.opengl.texture.loader;

import lombok.Getter;

import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Hashtable;
import java.util.Objects;

public class TextureContent {

    private static final ColorModel openglAlphaModel = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB),
            new int[] {8,8,8,8},
            true,
            false,
            ComponentColorModel.TRANSLUCENT,
            DataBuffer.TYPE_BYTE
    );

    @Getter private int width, height;
    @Getter private ByteBuffer data;

    public TextureContent(int width, int height, ByteBuffer data) {
        setWidth(width);
        setHeight(height);
        setData(data);
    }

    public TextureContent(BufferedImage image, boolean flip) {
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
        byte[] data = ((DataBufferByte) texImage.getRaster().getDataBuffer()).getData();

        ByteBuffer imageBuffer = ByteBuffer.allocateDirect(data.length);
        imageBuffer.order(ByteOrder.nativeOrder());
        imageBuffer.put(data, 0, data.length);
        imageBuffer.flip();

        return imageBuffer;
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

        System.out.println("W: " + width + "->" + texWidth + ", H:" + height + "->" + texHeight);


        WritableRaster raster = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE, texWidth, texHeight, 4, null);
        BufferedImage texImage = new BufferedImage(openglAlphaModel, raster, false, new Hashtable());

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
}

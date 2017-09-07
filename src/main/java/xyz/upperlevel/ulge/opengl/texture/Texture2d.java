package xyz.upperlevel.ulge.opengl.texture;

import lombok.Getter;
import org.lwjgl.BufferUtils;
import xyz.upperlevel.ulge.opengl.DataType;
import xyz.upperlevel.ulge.opengl.texture.loader.ImageContent;

import java.nio.ByteBuffer;
import java.util.Objects;

import static org.lwjgl.opengl.GL11.*;

public class Texture2d {

    public static final Texture2d NULL;

    public static Texture2d bound = null;

    static {
        NULL = new Texture2d();
        NULL.loadImage(
                0,
                TextureFormat.RGBA,
                DataType.UNSIGNED_BYTE,
                new ImageContent(
                        1,
                        1,
                        (ByteBuffer) BufferUtils.createByteBuffer(4)
                                .put(new byte[]{
                                        (byte) 255,
                                        (byte) 255,
                                        (byte) 255,
                                        (byte) 255
                                })
                                .flip()
                )
        );
        TextureParameters.getDefault().setup();
    }

    @Getter
    public final int id;

    @Getter
    private ImageContent image = null;

    public Texture2d() {
        id = glGenTextures();
    }

    public Texture2d(int id) {
        this.id = id;
    }

    public Texture2d bind() {
        if (bound == null || bound.id != id) {
            glBindTexture(GL_TEXTURE_2D, id);
            bound = this;
        }
        return this;
    }

    public Texture2d forceBind() {
        glBindTexture(GL_TEXTURE_2D, id);
        bound = this;
        return this;
    }

    public Texture2d unbind() {
        if (bound != null) {
            glBindTexture(GL_TEXTURE_2D, 0);
            bound = null;
        }
        return this;
    }

    public Texture2d forceUnbind() {
        glBindTexture(GL_TEXTURE_2D, 0);
        bound = null;
        return this;
    }

    public Texture2d loadImage(int mipmapLevel, int formatType, int dataType, int width, int height, ByteBuffer image) {
        glTexImage2D(GL_TEXTURE_2D, mipmapLevel, formatType, width, height, 0, formatType, dataType, image);
        return this;
    }

    public Texture2d loadImage(int mipmapLevel, int formatType, int dataType, ImageContent image) {
        Objects.requireNonNull(image, "image");
        bind();
        glTexImage2D(GL_TEXTURE_2D,
                mipmapLevel,
                formatType,
                image.getWidth(),
                image.getHeight(),
                0,
                formatType,
                dataType,
                image.getData());
        this.image = image;
        return this;
    }

    public Texture2d loadImage(int mipmapLevel, TextureFormat formatType, DataType dataType, ImageContent image) {
        Objects.requireNonNull(formatType, "formatType");
        Objects.requireNonNull(dataType, "dataType");
        loadImage(mipmapLevel, formatType.getId(), dataType.getId(), image);
        return this;
    }

    public Texture2d loadImage(TextureFormat formatType, ImageContent image) {
        loadImage(0, formatType, DataType.UNSIGNED_BYTE, image);
        return this;
    }

    public void setup(TextureParameters parameters) {
        parameters.getParameters().forEach((type, val) -> glTexParameteri(GL_TEXTURE_2D, type.getId(), val.getId()));
    }

    public Texture2d destroy() {
        glDeleteTextures(id);
        return this;
    }

    public static Texture2d wrap(int id) {
        return new Texture2d(id);
    }
}
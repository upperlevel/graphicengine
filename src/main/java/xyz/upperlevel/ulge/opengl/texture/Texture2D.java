package xyz.upperlevel.ulge.opengl.texture;

import lombok.Getter;
import org.lwjgl.BufferUtils;
import xyz.upperlevel.ulge.opengl.DataType;
import xyz.upperlevel.ulge.opengl.texture.loader.ImageContent;

import java.nio.ByteBuffer;
import java.util.Objects;

import static org.lwjgl.opengl.GL11.*;

public class Texture2D {

    public static final Texture2D NULL;

    public static Texture2D bound = null;

    static {
        NULL = new Texture2D();
        NULL.loadImage(
                0,
                TextureFormat.RGBA,
                1,
                1,
                DataType.UNSIGNED_BYTE,
                (ByteBuffer) BufferUtils.createByteBuffer(4)
                        .put(new byte[]{
                                (byte) 255,
                                (byte) 255,
                                (byte) 255,
                                (byte) 255
                        })
                        .flip()
        );
        TextureParameters.getDefault().setup();
    }

    @Getter
    public final int id;

    @Getter
    private ImageContent imageContent = null;

    public Texture2D() {
        id = glGenTextures();
    }

    public Texture2D(int id) {
        this.id = id;
    }

    public Texture2D bind() {
        if (bound == null || bound.id != id) {
            glBindTexture(GL_TEXTURE_2D, id);
            bound = this;
        }
        return this;
    }

    public Texture2D forceBind() {
        glBindTexture(GL_TEXTURE_2D, id);
        bound = this;
        return this;
    }

    public Texture2D unbind() {
        if (bound != null) {
            glBindTexture(GL_TEXTURE_2D, 0);
            bound = null;
        }
        return this;
    }

    public Texture2D forceUnbind() {
        glBindTexture(GL_TEXTURE_2D, 0);
        bound = null;
        return this;
    }

    public Texture2D loadImage(int mipmapLevel, TextureFormat formatType, ImageContent content) {
        return loadImage(mipmapLevel, formatType, content.getWidth(), content.getHeight(), DataType.BYTE, content.getData());
    }

    public Texture2D loadImage(int mipmapLevel, int formatType, int width, int height, int dataType, ByteBuffer contentData) {
        bind();
        glTexImage2D(GL_TEXTURE_2D, mipmapLevel, formatType, width, height, 0, formatType, dataType, contentData);
        return this;
    }

    public Texture2D loadImage(int mipmapLevel, TextureFormat formatType, int width, int height, DataType dataType, ByteBuffer contentData) {
        Objects.requireNonNull(formatType, "formatType");
        Objects.requireNonNull(contentData, "contentData");
        loadImage(mipmapLevel, formatType.getId(), width, height, dataType.getId(), contentData);
        return this;
    }

    public Texture2D destroy() {
        glDeleteTextures(id);
        return this;
    }

    public static Texture2D wrap(int id) {
        return new Texture2D(id);
    }
}
package xyz.upperlevel.graphicengine.api.opengl.texture;

import lombok.Getter;
import org.lwjgl.BufferUtils;
import xyz.upperlevel.graphicengine.api.opengl.texture.loader.TextureContent;

import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.Optional;

import static org.lwjgl.opengl.GL11.*;

public class Texture {
    public static Texture NULL;

    static {
        NULL = new Texture();
        NULL.setContent(new TextureContent(
                1,
                1,
                (ByteBuffer) BufferUtils.createByteBuffer(4)
                .put(new byte[] {
                        (byte) 255,
                        (byte) 255,
                        (byte) 255,
                        (byte) 255
                }).flip()));
    }

    @Getter public final int id;

    private TextureContent content = null;

    public Texture() {
        id = glGenTextures();
    }

    public Optional<TextureContent> getContent() {
        return Optional.ofNullable(content);
    }

    public Texture setContent(TextureContent content) {
        setContent(content, TextureParameters.DEFAULTS);
        return this;
    }

    public Texture setContent(TextureContent content, TextureParameters parameters) {
        Objects.requireNonNull(content, "Content cannot be null.");
        Objects.requireNonNull(content, "Parameters cannot be null.");
        bind();
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, content.getWidth(), content.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, content.getData());
        parameters.setup();
        this.content = content;
        return this;
    }

    public Texture bind() {
        glBindTexture(GL_TEXTURE_2D, id);
        return this;
    }

    public Texture unbind() {
        glBindTexture(GL_TEXTURE_2D, 0);
        return this;
    }

    public Texture destroy() {
        glDeleteTextures(id);
        return this;
    }
}

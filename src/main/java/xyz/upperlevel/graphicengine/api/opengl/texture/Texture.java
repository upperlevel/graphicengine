package xyz.upperlevel.graphicengine.api.opengl.texture;

import lombok.Getter;
import xyz.upperlevel.graphicengine.api.opengl.texture.loader.TextureContent;

import java.util.Objects;
import java.util.Optional;

import static org.lwjgl.opengl.GL11.*;

public class Texture {

    @Getter public final int id;

    private TextureContent content = null;

    public Texture() {
        id = glGenTextures();
    }

    public Optional<TextureContent> getContent() {
        return Optional.ofNullable(content);
    }

    public void setContent(TextureContent content) {
        setContent(content, TextureParameters.DEFAULTS);
    }

    public void setContent(TextureContent content, TextureParameters parameters) {
        Objects.requireNonNull(content, "Content cannot be null.");
        Objects.requireNonNull(content, "Parameters cannot be null.");
        bind();
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, content.getWidth(), content.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, content.getData());
        parameters.setup();
        unbind();
        this.content = content;
    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, id);
    }

    public void unbind() {
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public void destroy() {
        glDeleteTextures(id);
    }
}

package xyz.upperlevel.ulge.opengl.buffer;

import lombok.Getter;
import xyz.upperlevel.ulge.opengl.texture.Texture2D;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL30.*;

public class Fbo {

    public static Fbo bound;

    @Getter
    public final int id;

    public static final int MAX_COLOR_ATTACHMENTS = (GL_COLOR_ATTACHMENT31 - GL_COLOR_ATTACHMENT0);

    public Fbo() {
        id = glGenFramebuffers();
    }

    public Fbo(int id) {
        this.id = id;
    }

    public Fbo bind() {
        if (bound == null || id != bound.id) {
            glBindFramebuffer(GL_FRAMEBUFFER, id);
            bound = this;
        }
        return this;
    }

    public Fbo forceBind() {
        glBindFramebuffer(GL_FRAMEBUFFER, id);
        bound = this;
        return this;
    }

    public Fbo bindDefault() {
        glBindFramebuffer(GL_FRAMEBUFFER, 0);
        return this;
    }

    public Fbo unbind() {
        if (bound != null) {
            bindDefault();
            bound = null;
        }
        return this;
    }

    public Fbo forceUnbind() {
        bindDefault();
        bound = null;
        return this;
    }

    public FboStatus getStatus() {
        FboStatus status = FboStatus.getStatus(glCheckFramebufferStatus(id));
        return status != null ? status : FboStatus.UNDEFINED;
    }

    public Fbo addTextureColorAttachment(int colorAttachmentIndex, int textureId, int mipmapLevel) {
        if (colorAttachmentIndex > MAX_COLOR_ATTACHMENTS)
            throw new IllegalArgumentException("Max color attachments count is: " + MAX_COLOR_ATTACHMENTS);
        glFramebufferTexture2D(
                GL_FRAMEBUFFER,
                GL_COLOR_ATTACHMENT0 + colorAttachmentIndex,
                GL_TEXTURE_2D,
                textureId,
                mipmapLevel
        );
        return this;
    }

    public Fbo addTextureColorAttachment(int colorAttachmentIndex, Texture2D texture, int mipmapLevel) {
        addTextureColorAttachment(colorAttachmentIndex, texture.getId(), mipmapLevel);
        return this;
    }

    public Fbo addTextureDepthAttachment(int textureId, int mipmapLevel) {
        glFramebufferTexture2D(
                GL_FRAMEBUFFER,
                GL_DEPTH_ATTACHMENT,
                GL_TEXTURE_2D,
                textureId,
                mipmapLevel
        );
        return this;
    }

    public Fbo addTextureDepthAttachment(Texture2D texture, int mipmapLevel) {
        addTextureDepthAttachment(texture.getId(), mipmapLevel);
        return this;
    }

    public Fbo addTextureStencilAttachment(int textureId, int mipmapLevel) {
        glFramebufferTexture2D(
                GL_FRAMEBUFFER,
                GL_STENCIL_ATTACHMENT,
                GL_TEXTURE_2D,
                textureId,
                mipmapLevel
        );
        return this;
    }

    public Fbo addTextureStencilAttachment(Texture2D texture, int mipmapLevel) {
        addTextureStencilAttachment(texture.getId(), mipmapLevel);
        return this;
    }

    public Fbo addTextureDepthStencilAttachment(int textureId, int mipmapLevel) {
        glFramebufferTexture2D(
                GL_FRAMEBUFFER,
                GL_DEPTH_STENCIL_ATTACHMENT,
                GL_TEXTURE_2D,
                textureId,
                mipmapLevel
        );
        return this;
    }

    public Fbo addTextureDepthStencilAttachment(Texture2D texture, int mipmapLevel) {
        addTextureDepthStencilAttachment(texture.getId(), mipmapLevel);
        return this;
    }

    public Fbo destroy() {
        glDeleteFramebuffers(id);
        return this;
    }

    public static Fbo generate() {
        return new Fbo();
    }

    public static Fbo wrap(int id) {
        return new Fbo(id);
    }
}

package xyz.upperlevel.ulge.opengl.buffer;

import lombok.Getter;
import xyz.upperlevel.ulge.opengl.texture.Texture2D;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL30.*;

public class FBO {

    private static FBO bound;

    @Getter
    private int id;

    public static final int MAX_COLOR_ATTACHMENTS = (GL_COLOR_ATTACHMENT31 - GL_COLOR_ATTACHMENT0);

    public FBO() {
        id = glGenFramebuffers();
    }

    public FBO bind() {
        if (!equals(bound)) {
            glBindFramebuffer(GL_FRAMEBUFFER, id);
            bound = this;
        }
        return this;
    }

    public FBO bindDefault() {
        glBindFramebuffer(GL_FRAMEBUFFER, 0);
        return this;
    }

    public FBO unbind() {
        if (equals(bound)) {
            bindDefault();
            bound = null;
        }
        return this;
    }

    public FBOStatus getStatus() {
        FBOStatus status = FBOStatus.getStatus(glCheckFramebufferStatus(id));
        return status != null ? status : FBOStatus.UNDEFINED;
    }

    public FBO addTextureColorAttachment(int colorAttachmentIndex, int textureId, int mipmapLevel) {
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

    public FBO addTextureColorAttachment(int colorAttachmentIndex, Texture2D texture, int mipmapLevel) {
        addTextureColorAttachment(colorAttachmentIndex, texture.getId(), mipmapLevel);
        return this;
    }

    public FBO addTextureDepthAttachment(int textureId, int mipmapLevel) {
        glFramebufferTexture2D(
                GL_FRAMEBUFFER,
                GL_DEPTH_ATTACHMENT,
                GL_TEXTURE_2D,
                textureId,
                mipmapLevel
        );
        return this;
    }

    public FBO addTextureDepthAttachment(Texture2D texture, int mipmapLevel) {
        addTextureDepthAttachment(texture.getId(), mipmapLevel);
        return this;
    }

    public FBO addTextureStencilAttachment(int textureId, int mipmapLevel) {
        glFramebufferTexture2D(
                GL_FRAMEBUFFER,
                GL_STENCIL_ATTACHMENT,
                GL_TEXTURE_2D,
                textureId,
                mipmapLevel
        );
        return this;
    }

    public FBO addTextureStencilAttachment(Texture2D texture, int mipmapLevel) {
        addTextureStencilAttachment(texture.getId(), mipmapLevel);
        return this;
    }

    public FBO addTextureDepthStencilAttachment(int textureId, int mipmapLevel) {
        glFramebufferTexture2D(
                GL_FRAMEBUFFER,
                GL_DEPTH_STENCIL_ATTACHMENT,
                GL_TEXTURE_2D,
                textureId,
                mipmapLevel
        );
        return this;
    }

    public FBO addTextureDepthStencilAttachment(Texture2D texture, int mipmapLevel) {
        addTextureDepthStencilAttachment(texture.getId(), mipmapLevel);
        return this;
    }

    public FBO destroy() {
        glDeleteFramebuffers(id);
        return this;
    }

    public static FBO generate() {
        return new FBO();
    }

    public static FBO getBound() {
        return bound;
    }

    @Deprecated
    public static void setBound(FBO fbo) {
        bound = fbo;
    }
}

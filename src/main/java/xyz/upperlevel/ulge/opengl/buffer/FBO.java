package xyz.upperlevel.ulge.opengl.buffer;

import lombok.Getter;
import xyz.upperlevel.ulge.opengl.texture.Texture;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL30.*;

public class FBO {

    @Getter
    private int id;

    public static final int MAX_COLOR_ATTACHMENTS = (GL_COLOR_ATTACHMENT31 - GL_COLOR_ATTACHMENT0);

    public FBO() {
        id = glGenFramebuffers();
    }

    public void bind() {
        glBindFramebuffer(GL_FRAMEBUFFER, id);
    }

    public void bindDefault() {
        glBindFramebuffer(GL_FRAMEBUFFER, 0);
    }

    public void unbind() {
        bindDefault();
    }

    public FBOStatus getStatus() {
        FBOStatus status = FBOStatus.getStatus(glCheckFramebufferStatus(id));
        return status != null ? status : FBOStatus.UNDEFINED;
    }

    public void addTextureColorAttachment(int colorAttachmentIndex, int textureId, int mipmapLevel) {
        if (colorAttachmentIndex > MAX_COLOR_ATTACHMENTS)
            throw new IllegalArgumentException("Max color attachments count is: " + MAX_COLOR_ATTACHMENTS);
        glFramebufferTexture2D(
                GL_FRAMEBUFFER,
                GL_COLOR_ATTACHMENT0 + colorAttachmentIndex,
                GL_TEXTURE_2D,
                textureId,
                mipmapLevel
        );
    }

    public void addTextureColorAttachment(int colorAttachmentIndex, Texture texture, int mipmapLevel) {
        addTextureColorAttachment(colorAttachmentIndex, texture.getId(), mipmapLevel);
    }

    public void addTextureDepthAttachment(int textureId, int mipmapLevel) {
        glFramebufferTexture2D(
                GL_FRAMEBUFFER,
                GL_DEPTH_ATTACHMENT,
                GL_TEXTURE_2D,
                textureId,
                mipmapLevel
        );
    }

    public void addTextureDepthAttachment(Texture texture, int mipmapLevel) {
        addTextureDepthAttachment(texture.getId(), mipmapLevel);
    }

    public void addTextureStencilAttachment(int textureId, int mipmapLevel) {
        glFramebufferTexture2D(
                GL_FRAMEBUFFER,
                GL_STENCIL_ATTACHMENT,
                GL_TEXTURE_2D,
                textureId,
                mipmapLevel
        );
    }

    public void addTextureStencilAttachment(Texture texture, int mipmapLevel) {
        addTextureStencilAttachment(texture.getId(), mipmapLevel);
    }

    public void addTextureDepthStencilAttachment(int textureId, int mipmapLevel) {
        glFramebufferTexture2D(
                GL_FRAMEBUFFER,
                GL_DEPTH_STENCIL_ATTACHMENT,
                GL_TEXTURE_2D,
                textureId,
                mipmapLevel
        );
    }

    public void addTextureDepthStencilAttachment(Texture texture, int mipmapLevel) {
        addTextureDepthStencilAttachment(texture.getId(), mipmapLevel);
    }

    public void destroy() {
        glDeleteFramebuffers(id);
    }

    public static FBO generate() {
        return new FBO();
    }
}

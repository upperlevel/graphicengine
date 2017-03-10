package xyz.upperlevel.ulge.opengl.texture;

import lombok.Getter;
import lombok.NonNull;
import xyz.upperlevel.ulge.opengl.DataType;
import xyz.upperlevel.ulge.opengl.texture.loader.ImageContent;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.glTexSubImage3D;
import static org.lwjgl.opengl.GL30.GL_TEXTURE_2D_ARRAY;
import static org.lwjgl.opengl.GL42.glTexStorage3D;

public class Texture2dArray {

    @Getter
    private int id;

    @Getter
    private int width = 0, height = 0, depth = 0;

    @Getter
    private int format;

    public Texture2dArray() {
        id = glGenTextures();
    }

    public void allocate(int mipmaps, int format, int width, int height, int depth) {
        bind();
        glTexStorage3D(GL_TEXTURE_2D_ARRAY, mipmaps, format, width, height, depth);

        this.format = GL_RGBA;
        this.width  = width;
        this.height = height;
        this.depth  = depth;
    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D_ARRAY, id);
    }

    public void unbind() {
        glBindTexture(GL_TEXTURE_2D_ARRAY, 0);
    }

    public void load(
            int mipmap,
            int x,
            int y,
            int z,
            int format,
            int dataType,
            @NonNull ByteBuffer image) {
        bind();
        glTexSubImage3D(
                GL_TEXTURE_2D_ARRAY,
                mipmap,
                x,
                y,
                z,
                width,
                height,
                1,
                format,
                dataType,
                image
        );
    }

    public void load(
            int mipmap,
            int x,
            int y,
            int z,
            @NonNull DataType dataType,
            @NonNull ImageContent image) {
        load(
                mipmap,
                x,
                y,
                z,
                format,
                dataType.getId(),
                image.getData()
        );
    }

    public void load(
            int mipmap,
            int layer,
            @NonNull DataType dataType,
            @NonNull ImageContent image) {
        load(
                mipmap,
                0,
                0,
                layer,
                format,
                dataType.getId(),
                image.getData()
        );
    }

    public void load(
            int layer,
            @NonNull ImageContent image) {
        load(
                0,
                layer,
                DataType.UNSIGNED_BYTE,
                image
        );
    }

    public void destroy() {
        glDeleteTextures(id);
    }
}

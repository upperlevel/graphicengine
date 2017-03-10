package xyz.upperlevel.ulge.opengl.texture;

import lombok.Getter;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.GL_BGR;
import static org.lwjgl.opengl.GL12.GL_BGRA;
import static org.lwjgl.opengl.GL30.*;

public enum TextureFormat {

    RED             (GL_RED),
    RG              (GL_RG),
    RGB             (GL_RGB),
    BGR             (GL_BGR),
    RGBA            (GL_RGBA),
    BGRA            (GL_BGRA),
    RED_INTEGER     (GL_RED_INTEGER),
    RG_INTEGER      (GL_RG_INTEGER),
    RGB_INTEGER     (GL_RGB_INTEGER),
    BGR_INTEGER     (GL_BGR_INTEGER),
    RGBA_INTEGER    (GL_RGBA_INTEGER),
    BGRA_INTEGER    (GL_BGRA_INTEGER),
    STENCIL_INDEX   (GL_STENCIL_INDEX),
    DEPTH_COMPONENT (GL_DEPTH_COMPONENT),
    DEPTH_STENCIL   (GL_DEPTH_STENCIL);

    @Getter
    private int id;

    TextureFormat(int id) {
        this.id = id;
    }

    public static TextureFormat fromId(int id) {
        for (TextureFormat v : values())
            if (v.getId() == id)
                return v;
        return null;
    }
}

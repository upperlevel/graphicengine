package xyz.upperlevel.ulge.opengl;

import xyz.upperlevel.ulge.opengl.buffer.EBO;
import xyz.upperlevel.ulge.opengl.buffer.FBO;
import xyz.upperlevel.ulge.opengl.buffer.VAO;
import xyz.upperlevel.ulge.opengl.buffer.VBO;
import xyz.upperlevel.ulge.opengl.shader.Program;
import xyz.upperlevel.ulge.opengl.texture.Texture2D;

public final class BoundWatcher {

    private BoundWatcher() {
    }

    public static VAO getBoundVAO() {
        return VAO.getBound();
    }

    public static VBO getBoundVBO() {
        return VBO.getBound();
    }

    public static EBO getBoundEBO() {
        return EBO.getBound();
    }

    public static FBO getBoundFBO() {
        return FBO.getBound();
    }

    public static Program getBoundProgram() {
        return Program.getBound();
    }

    public static Texture2D getBoundTexture2D() {
        return Texture2D.getBound();
    }
}

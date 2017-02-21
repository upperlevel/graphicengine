package xyz.upperlevel.ulge.opengl;

import xyz.upperlevel.ulge.opengl.buffer.Ebo;
import xyz.upperlevel.ulge.opengl.buffer.Fbo;
import xyz.upperlevel.ulge.opengl.buffer.Vao;
import xyz.upperlevel.ulge.opengl.buffer.Vbo;
import xyz.upperlevel.ulge.opengl.shader.Program;
import xyz.upperlevel.ulge.opengl.texture.Texture2D;

public final class BoundWatcher {

    private BoundWatcher() {
    }

    public static Vao getBoundVAO() {
        return Vao.bound;
    }

    public static Vbo getBoundVBO() {
        return Vbo.bound;
    }

    public static Ebo getBoundEBO() {
        return Ebo.bound;
    }

    public static Fbo getBoundFBO() {
        return Fbo.bound;
    }

    public static Program getBoundProgram() {
        return Program.bound;
    }

    public static Texture2D getBoundTexture2D() {
        return Texture2D.bound;
    }
}

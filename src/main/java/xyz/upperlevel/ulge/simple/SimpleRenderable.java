package xyz.upperlevel.ulge.simple;

import xyz.upperlevel.ulge.opengl.shader.Program;
import xyz.upperlevel.ulge.opengl.shader.Uniform;
import xyz.upperlevel.ulge.opengl.texture.Texture2d;
import xyz.upperlevel.ulge.util.Color;

public abstract class SimpleRenderable implements Renderable {
    protected final Texture2d tex;
    protected final Color color;

    protected Uniform uColor;

    protected SimpleRenderable(Color color) {
        this.tex = Texture2d.NULL;
        this.color = color;
    }

    protected SimpleRenderable(Texture2d tex) {
        this.tex = tex;
        this.color = Color.WHITE;
    }

    @Override
    public void init(Program program) {
        uColor = program.getUniform("col");
    }

    @Override
    public void draw(Program program) {
        tex.bind();
        uColor.set(color);
    }
}

package xyz.upperlevel.ulge.simple;

import xyz.upperlevel.ulge.opengl.shader.Uniform;
import xyz.upperlevel.ulge.opengl.shader.Uniformer;
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
    public void init(Uniformer uniformer) {
        uColor = uniformer.get("col");
    }

    @Override
    public void draw(Uniformer uniformer) {
        tex.bind();
        uColor.set(color);
    }
}

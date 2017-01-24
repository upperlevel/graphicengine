package xyz.upperlevel.ulge.simple;

import xyz.upperlevel.ulge.opengl.shader.Uniformer;
import xyz.upperlevel.ulge.opengl.texture.Texture;
import xyz.upperlevel.ulge.util.Color;
import xyz.upperlevel.ulge.util.Colors;

public abstract class SimpleRenderable implements Renderable {
    protected final Texture tex;
    protected final Color color;

    protected SimpleRenderable(Color color) {
        this.tex = Texture.NULL;
        this.color = color;
    }

    protected SimpleRenderable(Texture tex) {
        this.tex = tex;
        this.color = Colors.WHITE;
    }

    @Override
    public void draw(Uniformer uniformer) {
        tex.bind();

        if(!uniformer.setUniform("col", color))
            throw new IllegalStateException("Cannot set color");
    }
}

package xyz.upperlevel.graphicengine.api.simple;

import xyz.upperlevel.graphicengine.api.opengl.shader.Uniformer;
import xyz.upperlevel.graphicengine.api.opengl.texture.Texture;

public abstract class SimpleRenderable implements Renderable {
    protected final Texture tex;
    protected final SimpleColor color;

    protected SimpleRenderable(SimpleColor color) {
        this.tex = Texture.NULL;
        this.color = color;
    }

    protected SimpleRenderable(Texture tex) {
        this.tex = tex;
        this.color = SimpleColor.WHITE;
    }

    @Override
    public void draw(Uniformer uniformer) {
        tex.bind();

        if(!uniformer.setUniform("col", color))
            throw new IllegalStateException("Cannot set color");
    }
}

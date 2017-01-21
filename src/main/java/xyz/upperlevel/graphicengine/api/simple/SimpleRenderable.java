package xyz.upperlevel.graphicengine.api.simple;

import xyz.upperlevel.graphicengine.api.opengl.shader.Uniformer;
import xyz.upperlevel.graphicengine.api.opengl.texture.Texture;
import xyz.upperlevel.graphicengine.api.util.Color;
import xyz.upperlevel.graphicengine.api.util.Colors;

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

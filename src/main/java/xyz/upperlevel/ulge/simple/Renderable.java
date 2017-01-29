package xyz.upperlevel.ulge.simple;

import xyz.upperlevel.ulge.opengl.shader.Uniformer;

public interface Renderable {

    public void init(Uniformer uniformer);

    public void draw(Uniformer uniformer);
}

package xyz.upperlevel.ulge.simple;

import xyz.upperlevel.ulge.opengl.shader.Uniformer;

public interface Renderable {

    void init(Uniformer uniformer);

    void draw(Uniformer uniformer);
}

package xyz.upperlevel.ulge.simple;

import xyz.upperlevel.ulge.opengl.shader.Program;

public interface Renderable {
    void init(Program program);

    void draw(Program program);
}

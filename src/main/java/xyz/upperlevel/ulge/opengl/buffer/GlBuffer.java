package xyz.upperlevel.ulge.opengl.buffer;

public interface GlBuffer {

    GlBuffer bind(int type);

    GlBuffer bind();

    GlBuffer unbind(int type);

    GlBuffer unbind();

    void destroy();
}

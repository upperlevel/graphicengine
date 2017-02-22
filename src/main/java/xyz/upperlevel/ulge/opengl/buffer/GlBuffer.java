package xyz.upperlevel.ulge.opengl.buffer;

public interface GlBuffer {

    GlBuffer bind(int type);

    GlBuffer unbind(int type);

    void destroy();
}

package xyz.upperlevel.ulge.opengl.buffer;

public interface GlBuffer {

    GlBuffer bind(int type);

    GlBuffer unbind(int type);

    GlBuffer bindCopyRead();

    GlBuffer bindCopyWrite();

    void destroy();
}

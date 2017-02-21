package xyz.upperlevel.ulge.opengl.buffer;

public final class BufferGenerator {

    private BufferGenerator() {
    }

    public static Vao generateVAO() {
        return Vao.generate();
    }

    public static Vbo generateVBO() {
        return Vbo.generate();
    }

    public static Ebo generateEBO() {
        return Ebo.generate();
    }
}
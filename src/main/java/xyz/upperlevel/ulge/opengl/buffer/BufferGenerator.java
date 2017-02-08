package xyz.upperlevel.ulge.opengl.buffer;

public final class BufferGenerator {

    private BufferGenerator() {
    }

    public static VAO generateVAO() {
        return VAO.generate();
    }

    public static VBO generateVBO() {
        return VBO.generate();
    }

    public static EBO generateEBO() {
        return EBO.generate();
    }
}
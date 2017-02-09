package xyz.upperlevel.ulge.opengl.buffer;

import lombok.Getter;
import org.lwjgl.opengl.GL11;

import java.nio.*;

import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL15.*;

public class VBO {

    private static VBO bound;

    @Getter
    private int id;

    public VBO() {
        id = glGenBuffers();
    }

    public VBO bind() {
        if (!equals(bound)) {
            glBindBuffer(GL_ARRAY_BUFFER, id);
            bound = this;
        }
        return this;
    }

    public VBO unbind() {
        if (equals(bound)) {
            glBindBuffer(GL_ARRAY_BUFFER, 0);
            bound = null;
        }
        return this;
    }

    // loadData(float)

    public VBO loadData(float[] vertices, VBODataUsage usage) {
        loadData(vertices, usage.getId());
        return this;
    }

    public VBO loadData(float[] vertices, int usage) {
        loadData(BufferUtil.createBuffer(vertices), usage);
        return this;
    }

    public VBO loadData(FloatBuffer buffer, VBODataUsage usage) {
        loadData(buffer, usage.getId());
        return this;
    }

    public VBO loadData(FloatBuffer buffer, int usage) {
        bind();
        glBufferData(GL_ARRAY_BUFFER, buffer, usage);
        return this;
    }

    // loadData(double)

    public VBO loadData(double[] vertices, VBODataUsage usage) {
        loadData(vertices, usage.getId());
        return this;
    }

    public VBO loadData(double[] vertices, int usage) {
        loadData(BufferUtil.createBuffer(vertices), usage);
        return this;
    }

    public VBO loadData(DoubleBuffer buffer, VBODataUsage usage) {
        loadData(buffer, usage.getId());
        return this;
    }

    public VBO loadData(DoubleBuffer buffer, int usage) {
        bind();
        glBufferData(GL_ARRAY_BUFFER, buffer, usage);
        return this;
    }

    // updateData(float)

    public VBO updateData(int offset, float[] vertices) {
        updateData(offset, BufferUtil.createBuffer(vertices));
        return this;
    }

    public VBO updateData(int offset, FloatBuffer buffer) {
        bind();
        glBufferSubData(GL_ARRAY_BUFFER, offset, buffer);
        return this;
    }

    // updateData(double)

    public VBO updateData(int offset, double[] vertices) {
        updateData(offset, BufferUtil.createBuffer(vertices));
        return this;
    }

    public VBO updateData(int offset, DoubleBuffer buffer) {
        bind();
        glBufferSubData(GL_ARRAY_BUFFER, offset, buffer);
        return this;
    }

    public VBO draw(int drawMode, int startOffset, int verticesCount) {
        glDrawArrays(drawMode, startOffset, verticesCount);
        return this;
    }

    public VBO draw(DrawMode drawMode, int startOffset, int verticesCount) {
        draw(drawMode.getId(), startOffset, verticesCount);
        return this;
    }

    public static VBO generate() {
        return new VBO();
    }

    public static VBO getBound() {
        return bound;
    }

    @Deprecated
    public static void setBound(VBO vbo) {
        bound = vbo;
    }
}
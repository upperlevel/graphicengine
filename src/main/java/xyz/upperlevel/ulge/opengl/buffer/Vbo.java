package xyz.upperlevel.ulge.opengl.buffer;

import lombok.Getter;
import org.lwjgl.opengl.GL15;

import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL15.*;

public class Vbo {

    public static Vbo bound;

    @Getter
    private int id;

    public Vbo() {
        id = glGenBuffers();
    }

    public Vbo(int id) {
        this.id = id;
    }

    public Vbo bind() {
        if (bound == null || bound.id != id) {
            glBindBuffer(GL_ARRAY_BUFFER, id);
            bound = this;
        }
        return this;
    }

    public Vbo forceBind() {
        glBindBuffer(GL_ARRAY_BUFFER, id);
        bound = this;
        return this;
    }

    public Vbo unbind() {
        if (bound != null) {
            glBindBuffer(GL_ARRAY_BUFFER, 0);
            bound = null;
        }
        return this;
    }

    public Vbo forceUnbind() {
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        bound = null;
        return this;
    }

    // loadData(float)

    public Vbo loadData(float[] vertices, VboDataUsage usage) {
        loadData(vertices, usage.getId());
        return this;
    }

    public Vbo loadData(float[] vertices, int usage) {
        loadData(BufferUtil.createBuffer(vertices), usage);
        return this;
    }

    public Vbo loadData(FloatBuffer buffer, VboDataUsage usage) {
        loadData(buffer, usage.getId());
        return this;
    }

    public Vbo loadData(FloatBuffer buffer, int usage) {
        bind();
        glBufferData(GL_ARRAY_BUFFER, buffer, usage);
        return this;
    }

    // loadData(double)

    public Vbo loadData(double[] vertices, VboDataUsage usage) {
        loadData(vertices, usage.getId());
        return this;
    }

    public Vbo loadData(double[] vertices, int usage) {
        loadData(BufferUtil.createBuffer(vertices), usage);
        return this;
    }

    public Vbo loadData(DoubleBuffer buffer, VboDataUsage usage) {
        loadData(buffer, usage.getId());
        return this;
    }

    public Vbo loadData(DoubleBuffer buffer, int usage) {
        bind();
        glBufferData(GL_ARRAY_BUFFER, buffer, usage);
        return this;
    }

    // updateData(float)

    public Vbo updateData(int offset, float[] vertices) {
        updateData(offset, BufferUtil.createBuffer(vertices));
        return this;
    }

    public Vbo updateData(int offset, FloatBuffer buffer) {
        bind();
        glBufferSubData(GL_ARRAY_BUFFER, offset, buffer);
        return this;
    }

    // updateData(double)

    public Vbo updateData(int offset, double[] vertices) {
        updateData(offset, BufferUtil.createBuffer(vertices));
        return this;
    }

    public Vbo updateData(int offset, DoubleBuffer buffer) {
        bind();
        glBufferSubData(GL_ARRAY_BUFFER, offset, buffer);
        return this;
    }

    public Vbo draw(int drawMode, int startOffset, int verticesCount) {
        glDrawArrays(drawMode, startOffset, verticesCount);
        return this;
    }

    public Vbo draw(DrawMode drawMode, int startOffset, int verticesCount) {
        draw(drawMode.getId(), startOffset, verticesCount);
        return this;
    }

    public void destroy() {
        GL15.glDeleteBuffers(id);
    }

    public static Vbo generate() {
        return new Vbo();
    }

    public static Vbo wrap(int id) {
        return new Vbo(id);
    }
}
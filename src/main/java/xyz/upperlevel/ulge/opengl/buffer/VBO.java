package xyz.upperlevel.ulge.opengl.buffer;

import lombok.Getter;
import org.lwjgl.opengl.GL11;

import java.nio.*;

import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL15.*;

public class VBO {

    @Getter
    private int id;

    public VBO() {
        id = glGenBuffers();
    }

    public void bind() {
        glBindBuffer(GL_ARRAY_BUFFER, id);
    }

    public void unbind() {
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    // loadData(float)

    public void loadData(float[] vertices, VBODataUsage usage) {
        loadData(vertices, usage.getId());
    }

    public void loadData(float[] vertices, int usage) {
        loadData(BufferUtil.createBuffer(vertices), usage);
    }

    public void loadData(FloatBuffer buffer, VBODataUsage usage) {
        loadData(buffer, usage.getId());
    }

    public void loadData(FloatBuffer buffer, int usage) {
        bind();
        glBufferData(GL_ARRAY_BUFFER, buffer, usage);
    }

    // loadData(double)

    public void loadData(double[] vertices, VBODataUsage usage) {
        loadData(vertices, usage.getId());
    }

    public void loadData(double[] vertices, int usage) {
        loadData(BufferUtil.createBuffer(vertices), usage);
    }

    public void loadData(DoubleBuffer buffer, VBODataUsage usage) {
        loadData(buffer, usage.getId());
    }

    public void loadData(DoubleBuffer buffer, int usage) {
        bind();
        glBufferData(GL_ARRAY_BUFFER, buffer, usage);
    }

    // updateData(float)

    public void updateData(int offset, float[] vertices) {
        updateData(offset, BufferUtil.createBuffer(vertices));
    }

    public void updateData(int offset, FloatBuffer buffer) {
        bind();
        glBufferSubData(GL_ARRAY_BUFFER, offset, buffer);
    }

    // updateData(double)

    public void updateData(int offset, double[] vertices) {
        updateData(offset, BufferUtil.createBuffer(vertices));
    }

    public void updateData(int offset, DoubleBuffer buffer) {
        bind();
        glBufferSubData(GL_ARRAY_BUFFER, offset, buffer);
    }

    public void draw(int drawMode, int startOffset, int verticesCount) {
        glDrawArrays(drawMode, startOffset, verticesCount);
    }

    public void draw(DrawMode drawMode, int startOffset, int verticesCount) {
        draw(drawMode.getId(), startOffset, verticesCount);
    }

    public static VBO generate() {
        return new VBO();
    }
}
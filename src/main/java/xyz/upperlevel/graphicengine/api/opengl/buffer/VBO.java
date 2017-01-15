package xyz.upperlevel.graphicengine.api.opengl.buffer;

import lombok.Getter;

import java.nio.*;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class VBO {

    @Getter
    public final int id;

    /**
     * Generates a new VBO buffer.
     */
    public VBO() {
        id = glGenBuffers();
    }

    /**
     * Binds this VBO buffer.
     */
    public void bind() {
        glBindBuffer(GL_ARRAY_BUFFER, id);
    }

    /**
     * Unbinds the using VBO buffer. It does not checks if the
     * current one is this VBO buffer.
     */
    public void unbind() {
        glBindVertexArray(0);
    }

    /**
     * Loads vertices on this EBO.
     *
     * @param usage The expected usage of the vertices loaded.
     */
    public void loadData(float[] vertices, VBOUsage usage) {
        loadData(vertices, usage.id);
    }

    /**
     * Loads vertices on this EBO.
     *
     * @param usage The expected usage of the vertices loaded.
     */
    public void loadData(float[] vertices, int usage) {
        loadData(BufferUtil.createBuffer(vertices), usage);
    }

    /**
     * Loads vertices on this EBO.
     *
     * @param usage The expected usage of the vertices loaded.
     */
    public void loadData(FloatBuffer buffer, VBOUsage usage) {
        loadData(buffer, usage.id);
    }

    /**
     * Loads vertices on this EBO.
     *
     * @param usage The expected usage of the vertices loaded.
     */
    public void loadData(FloatBuffer buffer, int usage) {
        bind();
        glBufferData(GL_ARRAY_BUFFER, buffer, usage);
    }

    /**
     * Updates vertices at a precise offset.
     *
     * @param offset The offset where to update the vertices.
     */
    public void updateData(int offset, float[] vertices) {
        updateData(offset, BufferUtil.createBuffer(vertices));
    }

    /**
     * Updates vertices at a precise offset.
     *
     * @param offset The offset where to update the vertices.
     */
    public void updateData(int offset, FloatBuffer buffer) {
        bind();
        glBufferSubData(GL_ARRAY_BUFFER, offset, buffer);
    }

    /**
     * Loads vertices on this EBO.
     *
     * @param usage The expected usage of the vertices loaded.
     */
    public void loadData(double[] vertices, VBOUsage usage) {
        loadData(vertices, usage.id);
    }

    /**
     * Loads vertices on this EBO.
     *
     * @param usage The expected usage of the vertices loaded.
     */
    public void loadData(double[] vertices, int usage) {
        loadData(BufferUtil.createBuffer(vertices), usage);
    }

    /**
     * Loads vertices on this EBO.
     *
     * @param usage The expected usage of the vertices loaded.
     */
    public void loadData(DoubleBuffer buffer, VBOUsage usage) {
        loadData(buffer, usage.id);
    }

    /**
     * Loads vertices on this EBO.
     *
     * @param usage The expected usage of the vertices loaded.
     */
    public void loadData(DoubleBuffer buffer, int usage) {
        bind();
        glBufferData(GL_ARRAY_BUFFER, buffer, usage);
    }

    /**
     * Updates vertices at a precise offset.
     *
     * @param offset The offset where to update the vertices.
     */
    public void updateData(int offset, double[] vertices) {
        updateData(offset, BufferUtil.createBuffer(vertices));
    }

    /**
     * Updates vertices at a precise offset.
     *
     * @param offset The offset where to update the vertices.
     */
    public void updateData(int offset, DoubleBuffer buffer) {
        bind();
        glBufferSubData(GL_ARRAY_BUFFER, offset, buffer);
    }

    /**
     * Destroys this VBO buffer.
     */
    public void destroy() {
        glDeleteBuffers(id);
    }

    /**
     * Generates a new VBO buffer.
     */
    public static VBO generate() {
        return new VBO();
    }
}
package xyz.upperlevel.ulge.opengl.buffer;

import lombok.Getter;

import java.nio.*;

import static org.lwjgl.opengl.GL15.*;

public class EBO {

    @Getter
    public final int id;

    /**
     * Generates a new EBO buffer.
     */
    public EBO() {
        id = glGenBuffers();
    }

    /**
     * Binds this EBO buffer.
     */
    public void bind() {
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, id);
    }

    /**
     * Unbinds the using EBO buffer. It does not checks if the
     * current one is this EBO buffer.
     */
    public void unbind() {
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    /**
     * Loads indices on this EBO.
     *
     * @param usage The expected usage of the indices loaded.
     */
    public void loadData(byte[] indices, EBOUsage usage) {
        loadData(indices, usage.id);
    }

    /**
     * Loads indices on this EBO.
     *
     * @param usage The expected usage of the indices loaded.
     */
    public void loadData(byte[] indices, int usage) {
        loadData(BufferUtil.createBuffer(indices), usage);
    }

    /**
     * Loads indices on this EBO.
     *
     * @param usage The expected usage of the indices loaded.
     */
    public void loadData(ByteBuffer buffer, EBOUsage usage) {
        loadData(buffer, usage.id);
    }

    /**
     * Loads indices on this EBO.
     *
     * @param usage The expected usage of the indices loaded.
     */
    public void loadData(ByteBuffer buffer, int usage) {
        bind();
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffer, usage);
    }

    /**
     * Updates indices at a precise offset.
     *
     * @param offset The offset where to update the indices.
     */
    public void updateData(int offset, byte[] indices) {
        updateData(offset, BufferUtil.createBuffer(indices));
    }

    /**
     * Updates indices at a precise offset.
     *
     * @param offset The offset where to update the indices.
     */
    public void updateData(int offset, ByteBuffer buffer) {
        bind();
        glBufferSubData(GL_ELEMENT_ARRAY_BUFFER, offset, buffer);
    }

    /**
     * Loads indices on this EBO.
     *
     * @param usage The expected usage of the indices loaded.
     */
    public void loadData(short[] indices, EBOUsage usage) {
        loadData(indices, usage.id);
    }

    /**
     * Loads indices on this EBO.
     *
     * @param usage The expected usage of the indices loaded.
     */
    public void loadData(short[] indices, int usage) {
        loadData(BufferUtil.createBuffer(indices), usage);
    }

    /**
     * Loads indices on this EBO.
     *
     * @param usage The expected usage of the indices loaded.
     */
    public void loadData(ShortBuffer buffer, EBOUsage usage) {
        loadData(buffer, usage.id);
    }

    /**
     * Loads indices on this EBO.
     *
     * @param usage The expected usage of the indices loaded.
     */
    public void loadData(ShortBuffer buffer, int usage) {
        bind();
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffer, usage);
    }

    /**
     * Updates indices at a precise offset.
     *
     * @param offset The offset where to update the indices.
     */
    public void updateData(int offset, short[] indices) {
        updateData(offset, BufferUtil.createBuffer(indices));
    }

    /**
     * Updates indices at a precise offset.
     *
     * @param offset The offset where to update the indices.
     */
    public void updateData(int offset, ShortBuffer buffer) {
        bind();
        glBufferSubData(GL_ELEMENT_ARRAY_BUFFER, offset, buffer);
    }

    /**
     * Loads indices on this EBO.
     *
     * @param usage The expected usage of the indices loaded.
     */
    public void loadData(int[] indices, EBOUsage usage) {
        loadData(indices, usage.id);
    }

    /**
     * Loads indices on this EBO.
     *
     * @param usage The expected usage of the indices loaded.
     */
    public void loadData(int[] indices, int usage) {
        loadData(BufferUtil.createBuffer(indices), usage);
    }

    /**
     * Loads indices on this EBO.
     *
     * @param usage The expected usage of the indices loaded.
     */
    public void loadData(IntBuffer buffer, EBOUsage usage) {
        loadData(buffer, usage.id);
    }

    /**
     * Loads indices on this EBO.
     *
     * @param usage The expected usage of the indices loaded.
     */
    public void loadData(IntBuffer buffer, int usage) {
        bind();
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffer, usage);
    }

    /**
     * Updates indices at a precise offset.
     *
     * @param offset The offset where to update the indices.
     */
    public void updateData(int offset, int[] indices) {
        updateData(offset, BufferUtil.createBuffer(indices));
    }

    /**
     * Updates indices at a precise offset.
     *
     * @param offset The offset where to update the indices.
     */
    public void updateData(int offset, IntBuffer buffer) {
        bind();
        glBufferSubData(GL_ELEMENT_ARRAY_BUFFER, offset, buffer);
    }

    /**
     * Generates a new EBO buffer.
     */
    public static EBO generate() {
        return new EBO();
    }
}

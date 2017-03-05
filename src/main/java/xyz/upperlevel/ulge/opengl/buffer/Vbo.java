package xyz.upperlevel.ulge.opengl.buffer;

import lombok.Getter;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL31.GL_COPY_READ_BUFFER;
import static org.lwjgl.opengl.GL31.GL_COPY_WRITE_BUFFER;

public class Vbo implements GlBuffer {

    public static Vbo bound;

    @Getter
    private int id;

    public Vbo() {
        id = glGenBuffers();
    }

    public Vbo(int id) {
        this.id = id;
    }

    @Override
    public Vbo bind(int type) {
        glBindBuffer(type, id);
        return this;
    }

    private void recordBind() {
        bind(GL_ARRAY_BUFFER);
        bound = this;
    }

    public Vbo bind() {
        if (bound == null || bound.id == id)
            recordBind();
        return this;
    }

    public Vbo forceBind() {
        recordBind();
        return this;
    }

    @Override
    public Vbo unbind(int type) {
        glBindBuffer(type, 0);
        return this;
    }

    @Override
    public GlBuffer bindCopyRead() {
        bind(GL_COPY_READ_BUFFER);
        return this;
    }

    @Override
    public GlBuffer unbindCopyRead() {
        unbind(GL_COPY_READ_BUFFER);
        return this;
    }

    @Override
    public GlBuffer bindCopyWrite() {
        bind(GL_COPY_WRITE_BUFFER);
        return this;
    }

    @Override
    public GlBuffer unbindCopyWrite() {
        unbind(GL_COPY_WRITE_BUFFER);
        return this;
    }

    private void recordUnbind() {
        unbind(GL_ARRAY_BUFFER);
        bound = null;
    }

    public Vbo unbind() {
        if (bound != null)
            recordUnbind();
        return this;
    }

    public Vbo forceUnbind() {
        recordUnbind();
        return this;
    }

    @Override
    public void destroy() {
        glDeleteBuffers(id);
    }

    // loadData(byte)
    public Vbo loadData(byte[] vertices, VboDataUsage usage) {
        loadData(vertices, usage.getId());
        return this;
    }

    public Vbo loadData(byte[] vertices, int usage) {
        loadData(BufferUtil.createBuffer(vertices), usage);
        return this;
    }

    public Vbo loadData(ByteBuffer buffer, VboDataUsage usage) {
        loadData(buffer, usage.getId());
        return this;
    }

    public Vbo loadData(ByteBuffer buffer, int usage) {
        bind();
        glBufferData(GL_ARRAY_BUFFER, buffer, usage);
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

    // updateData(byte)

    public Vbo updateData(int offset, byte[] vertices) {
        updateData(offset, BufferUtil.createBuffer(vertices));
        return this;
    }

    public Vbo updateData(int offset, ByteBuffer buffer) {
        bind();
        glBufferSubData(GL_ARRAY_BUFFER, offset, buffer);
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
        bind();
        glDrawArrays(drawMode, startOffset, verticesCount);
        return this;
    }

    public Vbo draw(DrawMode drawMode, int startOffset, int verticesCount) {
        draw(drawMode.getId(), startOffset, verticesCount);
        return this;
    }

    public static Vbo generate() {
        return new Vbo();
    }

    public static Vbo wrap(int id) {
        return new Vbo(id);
    }
}
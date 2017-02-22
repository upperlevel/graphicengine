package xyz.upperlevel.ulge.opengl.buffer;

import lombok.Getter;
import xyz.upperlevel.ulge.opengl.DataType;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL15.*;

public class Ebo implements GlBuffer {

    public static Ebo bound;

    @Getter
    public final int id;

    public Ebo() {
        id = glGenBuffers();
    }

    public Ebo(int id) {
        this.id = id;
    }

    private void recordBind() {
        bind(GL_ELEMENT_ARRAY_BUFFER);
        bound = this;
    }

    @Override
    public Ebo bind(int type) {
        glBindBuffer(type, id);
        return this;
    }

    public Ebo bind() {
        if (bound == null || bound.id != id)
            recordBind();
        return this;
    }

    public Ebo forceBind() {
        recordBind();
        return this;
    }

    private void recordUnbind() {
        unbind(GL_ELEMENT_ARRAY_BUFFER);
        bound = null;
    }

    @Override
    public Ebo unbind(int type) {
        glBindBuffer(type, 0);
        return this;
    }

    public Ebo unbind() {
        if (bound != null && bound.id == id)
            recordUnbind();
        return this;
    }

    public Ebo forceUnbind() {
        recordUnbind();
        return this;
    }

    @Override
    public void destroy() {
        glDeleteBuffers(id);
    }

    // loadData(byte)

    public Ebo loadData(byte[] elements, EboDataUsage dataUsage) {
        loadData(elements, dataUsage.getId());
        return this;
    }

    public Ebo loadData(byte[] elements, int dataUsage) {
        loadData(BufferUtil.createBuffer(elements), dataUsage);
        return this;
    }

    public Ebo loadData(ByteBuffer buffer, EboDataUsage dataUsage) {
        loadData(buffer, dataUsage.getId());
        return this;
    }

    public Ebo loadData(ByteBuffer buffer, int dataUsage) {
        bind();
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffer, dataUsage);
        return this;
    }

    // loadData(short)

    public Ebo loadData(short[] elements, EboDataUsage dataUsage) {
        loadData(elements, dataUsage.getId());
        return this;
    }

    public Ebo loadData(short[] elements, int dataUsage) {
        loadData(BufferUtil.createBuffer(elements), dataUsage);
        return this;
    }

    public Ebo loadData(ShortBuffer buffer, EboDataUsage dataUsage) {
        loadData(buffer, dataUsage.getId());
        return this;
    }

    public Ebo loadData(ShortBuffer buffer, int dataUsage) {
        bind();
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffer, dataUsage);
        return this;
    }

    // loadData(int)

    public Ebo loadData(int[] elements, EboDataUsage dataUsage) {
        loadData(elements, dataUsage.getId());
        return this;
    }

    public Ebo loadData(int[] elements, int dataUsage) {
        loadData(BufferUtil.createBuffer(elements), dataUsage);
        return this;
    }

    public Ebo loadData(IntBuffer buffer, EboDataUsage dataUsage) {
        loadData(buffer, dataUsage.getId());
        return this;
    }

    public Ebo loadData(IntBuffer buffer, int dataUsage) {
        bind();
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffer, dataUsage);
        return this;
    }

    // updateData(byte)

    public Ebo updateData(int offset, byte[] elements) {
        updateData(offset, BufferUtil.createBuffer(elements));
        return this;
    }

    public Ebo updateData(int offset, ByteBuffer buffer) {
        bind();
        glBufferSubData(GL_ELEMENT_ARRAY_BUFFER, offset, buffer);
        return this;
    }

    // updateData(short)

    public Ebo updateData(int offset, short[] elements) {
        updateData(offset, BufferUtil.createBuffer(elements));
        return this;
    }

    public Ebo updateData(int offset, ShortBuffer buffer) {
        bind();
        glBufferSubData(GL_ELEMENT_ARRAY_BUFFER, offset, buffer);
        return this;
    }

    // updateData(int)

    public Ebo updateData(int offset, int[] elements) {
        updateData(offset, BufferUtil.createBuffer(elements));
        return this;
    }

    public Ebo updateData(int offset, IntBuffer buffer) {
        bind();
        glBufferSubData(GL_ELEMENT_ARRAY_BUFFER, offset, buffer);
        return this;
    }

    public Ebo draw(int drawMode, int dataType, long elementsOffset, int elementsCount) {
        glDrawElements(drawMode, elementsCount, dataType, elementsOffset);
        return this;
    }

    public Ebo draw(DrawMode drawMode, DataType dataType, long elementsOffset, int elementsCount) {
        draw(drawMode.getId(), dataType.getId(), elementsOffset, elementsCount);
        return this;
    }

    public static Ebo generate() {
        return new Ebo();
    }

    public static Ebo wrap(int id) {
        return new Ebo(id);
    }
}
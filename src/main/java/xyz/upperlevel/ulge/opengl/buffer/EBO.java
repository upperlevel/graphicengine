package xyz.upperlevel.ulge.opengl.buffer;

import lombok.Getter;
import xyz.upperlevel.ulge.opengl.DataType;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL15.*;

public class EBO {

    public static EBO bound;

    @Getter
    public final int id;

    public EBO() {
        id = glGenBuffers();
    }

    public EBO(int id) {
        this.id = id;
    }

    public EBO bind() {
        if (bound == null || bound.id != id) {
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, id);
            bound = this;
        }
        return this;
    }

    public EBO forceBind() {
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, id);
        bound = this;
        return this;
    }

    public EBO unbind() {
        if (bound != null) {
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
            bound = null;
        }
        return this;
    }

    public EBO forceUnbind() {
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        bound = null;
        return this;
    }

    // loadData(byte)

    public EBO loadData(byte[] elements, EBODataUsage dataUsage) {
        loadData(elements, dataUsage.getId());
        return this;
    }

    public EBO loadData(byte[] elements, int dataUsage) {
        loadData(BufferUtil.createBuffer(elements), dataUsage);
        return this;
    }

    public EBO loadData(ByteBuffer buffer, EBODataUsage dataUsage) {
        loadData(buffer, dataUsage.getId());
        return this;
    }

    public EBO loadData(ByteBuffer buffer, int dataUsage) {
        bind();
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffer, dataUsage);
        return this;
    }

    // loadData(short)

    public EBO loadData(short[] elements, EBODataUsage dataUsage) {
        loadData(elements, dataUsage.getId());
        return this;
    }

    public EBO loadData(short[] elements, int dataUsage) {
        loadData(BufferUtil.createBuffer(elements), dataUsage);
        return this;
    }

    public EBO loadData(ShortBuffer buffer, EBODataUsage dataUsage) {
        loadData(buffer, dataUsage.getId());
        return this;
    }

    public EBO loadData(ShortBuffer buffer, int dataUsage) {
        bind();
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffer, dataUsage);
        return this;
    }

    // loadData(int)

    public EBO loadData(int[] elements, EBODataUsage dataUsage) {
        loadData(elements, dataUsage.getId());
        return this;
    }

    public EBO loadData(int[] elements, int dataUsage) {
        loadData(BufferUtil.createBuffer(elements), dataUsage);
        return this;
    }

    public EBO loadData(IntBuffer buffer, EBODataUsage dataUsage) {
        loadData(buffer, dataUsage.getId());
        return this;
    }

    public EBO loadData(IntBuffer buffer, int dataUsage) {
        bind();
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffer, dataUsage);
        return this;
    }

    // updateData(byte)

    public EBO updateData(int offset, byte[] elements) {
        updateData(offset, BufferUtil.createBuffer(elements));
        return this;
    }

    public EBO updateData(int offset, ByteBuffer buffer) {
        bind();
        glBufferSubData(GL_ELEMENT_ARRAY_BUFFER, offset, buffer);
        return this;
    }

    // updateData(short)

    public EBO updateData(int offset, short[] elements) {
        updateData(offset, BufferUtil.createBuffer(elements));
        return this;
    }

    public EBO updateData(int offset, ShortBuffer buffer) {
        bind();
        glBufferSubData(GL_ELEMENT_ARRAY_BUFFER, offset, buffer);
        return this;
    }

    // updateData(int)

    public EBO updateData(int offset, int[] elements) {
        updateData(offset, BufferUtil.createBuffer(elements));
        return this;
    }

    public EBO updateData(int offset, IntBuffer buffer) {
        bind();
        glBufferSubData(GL_ELEMENT_ARRAY_BUFFER, offset, buffer);
        return this;
    }

    public EBO draw(int drawMode, int dataType, long elementsOffset, int elementsCount) {
        glDrawElements(drawMode, elementsCount, dataType, elementsOffset);
        return this;
    }

    public EBO draw(DrawMode drawMode, DataType dataType, long elementsOffset, int elementsCount) {
        draw(drawMode.getId(), dataType.getId(), elementsOffset, elementsCount);
        return this;
    }

    public static EBO generate() {
        return new EBO();
    }

    public static EBO wrap(int id) {
        return new EBO(id);
    }
}
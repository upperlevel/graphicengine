package xyz.upperlevel.ulge.opengl.buffer;

import lombok.Getter;
import xyz.upperlevel.ulge.opengl.DataType;

import java.nio.*;

import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL15.*;

public class EBO {

    @Getter
    private int id;

    public EBO() {
        id = glGenBuffers();
    }

    public void bind() {
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, id);
    }

    public void unbind() {
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    // loadData(byte)

    public void loadData(byte[] elements, EBODataUsage dataUsage) {
        loadData(elements, dataUsage.getId());
    }

    public void loadData(byte[] elements, int dataUsage) {
        loadData(BufferUtil.createBuffer(elements), dataUsage);
    }

    public void loadData(ByteBuffer buffer, EBODataUsage dataUsage) {
        loadData(buffer, dataUsage.getId());
    }

    public void loadData(ByteBuffer buffer, int dataUsage) {
        bind();
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffer, dataUsage);
    }

    // loadData(short)

    public void loadData(short[] elements, EBODataUsage dataUsage) {
        loadData(elements, dataUsage.getId());
    }

    public void loadData(short[] elements, int dataUsage) {
        loadData(BufferUtil.createBuffer(elements), dataUsage);
    }

    public void loadData(ShortBuffer buffer, EBODataUsage dataUsage) {
        loadData(buffer, dataUsage.getId());
    }

    public void loadData(ShortBuffer buffer, int dataUsage) {
        bind();
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffer, dataUsage);
    }

    // loadData(int)

    public void loadData(int[] elements, EBODataUsage dataUsage) {
        loadData(elements, dataUsage.getId());
    }

    public void loadData(int[] elements, int dataUsage) {
        loadData(BufferUtil.createBuffer(elements), dataUsage);
    }

    public void loadData(IntBuffer buffer, EBODataUsage dataUsage) {
        loadData(buffer, dataUsage.getId());
    }

    public void loadData(IntBuffer buffer, int dataUsage) {
        bind();
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffer, dataUsage);
    }

    // updateData(byte)

    public void updateData(int offset, byte[] elements) {
        updateData(offset, BufferUtil.createBuffer(elements));
    }

    public void updateData(int offset, ByteBuffer buffer) {
        bind();
        glBufferSubData(GL_ELEMENT_ARRAY_BUFFER, offset, buffer);
    }

    // updateData(short)

    public void updateData(int offset, short[] elements) {
        updateData(offset, BufferUtil.createBuffer(elements));
    }

    public void updateData(int offset, ShortBuffer buffer) {
        bind();
        glBufferSubData(GL_ELEMENT_ARRAY_BUFFER, offset, buffer);
    }

    // updateData(int)

    public void updateData(int offset, int[] elements) {
        updateData(offset, BufferUtil.createBuffer(elements));
    }

    public void updateData(int offset, IntBuffer buffer) {
        bind();
        glBufferSubData(GL_ELEMENT_ARRAY_BUFFER, offset, buffer);
    }

    public void draw(int drawMode, int dataType, long elementsOffset, int elementsCount) {
        glDrawElements(drawMode, elementsCount, dataType, elementsOffset);
    }

    public void draw(DrawMode drawMode, DataType dataType, long elementsOffset, int elementsCount) {
        draw(drawMode.getId(), dataType.getId(), elementsOffset, elementsCount);
    }

    public static EBO generate() {
        return new EBO();
    }
}
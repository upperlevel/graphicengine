package xyz.upperlevel.ulge.opengl.buffer;

import lombok.RequiredArgsConstructor;
import xyz.upperlevel.ulge.opengl.DataType;

import java.util.LinkedList;
import java.util.List;

import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;

public class VertexLinker {

    @RequiredArgsConstructor
    private static class Attrib {

        public final int index, count, type, bytes;

        @Override
        public String toString() {
            return "{i:" + index + ", c:" + count + ", b:" + bytes + "}";
        }
    }

    private final List<Attrib> vertex = new LinkedList<>();
    private DataType defDataType = DataType.FLOAT;

    public VertexLinker() {
    }

    public VertexLinker(DataType dataType) {
        defDataType = dataType;
    }

    public int getByteSize() {
        int sz = 0;
        for (Attrib attr : vertex)
            sz += attr.bytes;
        return sz;
    }

    public void setup() {
        int ptr = 0;
        for (Attrib attr : vertex) {
            glEnableVertexAttribArray(attr.index);
            glVertexAttribPointer(
                    attr.index,
                    attr.count,
                    attr.type,
                    false,
                    getByteSize(),
                    ptr);
            ptr += attr.bytes;
        }
    }

    public VertexLinker attrib(int index, int count, int dataTypeId, int bytes) {
        if(index < 0)
            throw new IllegalArgumentException("negative index: " + index);
        if(count < 0)
            throw new IllegalArgumentException("negative count: " + count);
        if(bytes < 0)
            throw new IllegalArgumentException("negative bytes: " + bytes);
        Attrib attr = new Attrib(index, count, dataTypeId, bytes);
        vertex.add(attr);
        return this;
    }

    public VertexLinker attrib(int index, int count, DataType type) {
        attrib(index, count, type.id, count * type.bytes);
        return this;
    }

    public VertexLinker attrib(int index, int count) {
        attrib(index, count, defDataType.id, count * defDataType.bytes);
        return this;
    }
}
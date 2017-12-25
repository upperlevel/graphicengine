package xyz.upperlevel.ulge.opengl.buffer;

import xyz.upperlevel.ulge.opengl.DataType;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;

public class VertexLinker {
    private final List<Attrib> attributes = new ArrayList<>();
    private int byteCount;

    /**
     * Adds an attribute to the vertex.
     *
     * @param index  the program attribute location
     * @param count  the count of data token by this attribute
     * @param type   the type of the attribute
     *               //* @param normalized
     * @param stride the distance of the attribute data from the beginning of the vertex data (in bytes)
     */
    public void attrib(int index, int count, DataType type, boolean normalized, int stride) {
        if (index < 0) {
            throw new IllegalArgumentException("Negative index: " + index);
        }
        if (count < 0) {
            throw new IllegalArgumentException("Negative count: " + count);
        }
        attributes.add(new Attrib(index, count, type, normalized, stride));
        int end = stride + count * type.getByteCount();
        if (end > byteCount) {
            byteCount = end;
        }
    }


    /**
     * Sends the built vertex to the GPU.
     */
    public void setup() {
        for (Attrib attr : attributes) {
            glEnableVertexAttribArray(attr.index);
            glVertexAttribPointer(
                    attr.index,
                    attr.count,
                    attr.type.getId(),
                    attr.normalized,
                    byteCount,
                    attr.stride
            );
        }
    }

    private static class Attrib {
        private int index, count;
        private DataType type;
        private boolean normalized;
        private int stride;

        public Attrib(int index, int count, DataType type, boolean normalized, int stride) {
            this.index = index;
            this.count = count;
            this.type = type;
            this.normalized = normalized;
            this.stride = stride;
        }
    }
}
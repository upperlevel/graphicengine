package xyz.upperlevel.graphicengine.api.opengl.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

import static org.lwjgl.opengl.GL11.GL_DOUBLE;
import static org.lwjgl.opengl.GL20.*;

public class VertexDefiner {

    private final List<Attrib> vertex = new LinkedList<>();

    public VertexDefiner(Builder builder) {
        vertex.addAll(builder.vertex);
    }

    public int dataSize() {
        int size = 0;
        for (Attrib attrib : vertex)
            size += attrib.count;
        return size;
    }

    public void setup() {
        vertex.forEach(attrib -> {
            glEnableVertexAttribArray(attrib.index);
            glVertexAttribPointer(
                    attrib.index,
                    attrib.count,
                    GL_DOUBLE,
                    false,
                    dataSize() * Double.BYTES,
                    attrib.stride * Double.BYTES);
        });
    }

    @AllArgsConstructor
    private static class Attrib {
        public final int index, count, stride;
    }

    @NoArgsConstructor
    public static class Builder {

        @Getter public final List<Attrib> vertex = new LinkedList<>();

        /**
         * @param index  defines glsl attribute index.
         * @param count  defines how many data for this attrib.
         * @param pointer defines how many attributes should be jumped before reading this attrib.
         **/
        public Builder attrib(int index, int count, int pointer) {
            vertex.add(new Attrib(index, count, pointer));
            return this;
        }

        public VertexDefiner build() {
            return new VertexDefiner(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}

package xyz.upperlevel.ulge.opengl.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import xyz.upperlevel.ulge.opengl.NumberType;

import java.util.LinkedList;
import java.util.List;

import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;

public class VertexDefiner {

    private final Attrib[] vertex;
    private final int size;

    public VertexDefiner(Builder builder) {
        vertex = builder.vertex.toArray(new Attrib[builder.vertex.size()]);
        {//Size
            int sz = 0;
            for(Attrib attrib : vertex)
                sz += attrib.bytes;
            size = sz;
        }
    }

    /**
     *
     * @return The data size expressed on bytes
     */
    public int dataSize() {
        return size;
    }

    public void setup() {
        int pointer = 0;
        for (Attrib attrib : vertex) {
            glEnableVertexAttribArray(attrib.index);
            glVertexAttribPointer(
                    attrib.index,
                    attrib.count,
                    attrib.type,
                    false,
                    size,
                    pointer);
            pointer += attrib.bytes;
        }
    }

    @AllArgsConstructor
    private static class Attrib {
        public final int index, count;
        public final int type, bytes;

        @Override
        public String toString() {
            return "{i:" + index + ", c:" + count + ", b:" + bytes + "}";
        }
    }

    public static class Builder {
        public static final NumberType DEF_TYPE = NumberType.DOUBLE;

        @Getter private final NumberType defType;

        @Getter public final List<Attrib> vertex = new LinkedList<>();

        public Builder(NumberType type) {
            defType = type;
        }

        public Builder() {
            this(DEF_TYPE);
        }

        /**
         * @param index  defines glsl attribute index.
         * @param count  defines how many data for this attrib.
         * @param openglTypeId the type id as OpenGL constant (like org.lwjgl.opengl.GL11.GL_FLOAT)
         **/
        public Builder attrib(int index, int count, int openglTypeId, int bytes) {
            Attrib attrib = new Attrib(index, count, openglTypeId, bytes);
            System.out.println(attrib);
            vertex.add(attrib);
            return this;
        }

        /**
         * @param index  defines glsl attribute index.
         * @param count  defines how many data for this attrib.
         **/
        public Builder attrib(int index, int count) {
            attrib(index, count, defType.id, count*defType.bytes);
            return this;
        }

        /**
         * @param index  defines glsl attribute index.
         * @param count  defines how many data for this attrib.
         * @param type defines what is the type of the attribute
         **/
        public Builder attrib(int index, int count, NumberType type) {
            attrib(index, count, type.id, count*type.bytes);
            return this;
        }

        public VertexDefiner build() {
            return new VertexDefiner(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Create a new builder that has the NumberType passed as argument as the default one
     * @param type the NumberType used as default
     * @return a new builder with type as the default type
     */
    public static Builder builder(NumberType type) {
        return new Builder(type);
    }
}

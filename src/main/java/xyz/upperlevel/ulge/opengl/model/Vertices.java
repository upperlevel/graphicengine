package xyz.upperlevel.ulge.opengl.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lwjgl.BufferUtils;
import xyz.upperlevel.ulge.opengl.NumberType;
import xyz.upperlevel.ulge.util.math.DoubleVec;
import xyz.upperlevel.ulge.util.math.Vec;

import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class Vertices implements Vec {

    @Getter private int vertexSize = -1;
    @Getter public final List<Vertex> vertices = new LinkedList<>();

    public Vertices add(Vertex vertex) {
        if (vertexSize < 0)
            vertexSize = vertex.size();
        else {
            int vSize = vertex.size();
            if (vertexSize != vSize)
                throw new IllegalArgumentException("All vertices must have the size set " +
                        "[needed: " + vertexSize + ", found: " + vSize + "]");
        }
        vertices.add(vertex);
        return this;
    }

    public Vertices add(float... values) {
        Vertex vtx = new Vertex();
        for (float val : values)
            vtx.add(val);
        add(vtx);
        return this;
    }

    public Vertices add(DoubleVec... values) {
        Vertex vtx = new Vertex();
        for (DoubleVec val : values)
            vtx.add(val);
        add(vtx);
        return this;
    }

    @Override
    public int size() {
        return vertices.size();
    }

    @Override
    public int byteSize() {
        return size() * vertexSize * DoubleVec.SINGLE_DATA_SIZE;
    }

    @Override
    public ByteBuffer buffer() {
        ByteBuffer buf = BufferUtils.createByteBuffer(byteSize());
        getVertices().forEach(vertex -> buf.put(vertex.buffer()));
        buf.flip();
        return buf;
    }

    public VertexDefiner.Builder definer() {
        return new VertexDefiner.Builder();
    }

    public VertexDefiner.Builder definer(NumberType def) {
        return new VertexDefiner.Builder(def);
    }
}

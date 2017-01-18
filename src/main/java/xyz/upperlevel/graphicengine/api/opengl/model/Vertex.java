package xyz.upperlevel.graphicengine.api.opengl.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lwjgl.BufferUtils;
import xyz.upperlevel.graphicengine.api.util.math.DoubleVec;
import xyz.upperlevel.graphicengine.api.util.math.Vec;
import xyz.upperlevel.graphicengine.api.util.math.Vec1;

import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;

import static xyz.upperlevel.graphicengine.api.util.math.DoubleVec.SINGLE_DATA_SIZE;

@NoArgsConstructor
public class Vertex implements Vec {

    @Getter public final List<DoubleVec> vecs = new LinkedList<>();

    public Vertex add(double attribute) {
        vecs.add(new Vec1(attribute));
        return this;
    }

    public Vertex add(DoubleVec attribute) {
        vecs.add(attribute);
        return this;
    }

    @Override
    public int size() {
        int size = 0;
        for (DoubleVec vec : vecs)
            size += vec.size();
        return size;
    }

    @Override
    public int byteSize() {
       return size() * SINGLE_DATA_SIZE;
    }

    @Override
    public ByteBuffer buffer() {
        ByteBuffer buf = BufferUtils.createByteBuffer(byteSize());
        vecs.forEach(vec -> buf.put(vec.buffer()));
        buf.flip();
        return buf;
    }
}

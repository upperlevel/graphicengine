package xyz.upperlevel.ulge.util.math;

import org.lwjgl.BufferUtils;

import java.nio.ByteBuffer;

public abstract class DoubleVec<T> implements Vec, VecOperable<T> {

    public static final int SINGLE_DATA_SIZE = Double.BYTES;

    @Override
    public int byteSize() {
        return size() * Double.BYTES;
    }

    @Override
    public ByteBuffer buffer() {
        ByteBuffer buf = BufferUtils.createByteBuffer(byteSize());
        fillBuf(buf);
        buf.flip();
        return buf;
    }

    protected abstract void fillBuf(ByteBuffer buf);
}
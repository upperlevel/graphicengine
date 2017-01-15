package xyz.upperlevel.graphicengine.api.opengl.buffer;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.lwjgl.BufferUtils;

import java.nio.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BufferUtil {

    public static ByteBuffer createBuffer(byte[] data) {
        return (ByteBuffer) BufferUtils.createByteBuffer(data.length)
                .put(data)
                .flip();
    }

    public static ShortBuffer createBuffer(short[] data) {
        return (ShortBuffer) BufferUtils.createShortBuffer(data.length)
                .put(data)
                .flip();
    }

    public static FloatBuffer createBuffer(float[] data) {
        return (FloatBuffer) BufferUtils.createFloatBuffer(data.length)
                .put(data)
                .flip();
    }

    public static IntBuffer createBuffer(int[] data) {
        return (IntBuffer) BufferUtils.createIntBuffer(data.length)
                .put(data)
                .flip();
    }

    public static DoubleBuffer createBuffer(double[] data) {
        return (DoubleBuffer) BufferUtils.createDoubleBuffer(data.length)
                .put(data)
                .flip();
    }
}
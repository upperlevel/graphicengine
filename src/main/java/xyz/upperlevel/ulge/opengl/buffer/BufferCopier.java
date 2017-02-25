package xyz.upperlevel.ulge.opengl.buffer;

import org.lwjgl.opengl.GL31;

import static org.lwjgl.opengl.GL31.GL_COPY_READ_BUFFER;
import static org.lwjgl.opengl.GL31.GL_COPY_WRITE_BUFFER;

public final class BufferCopier {

    private BufferCopier() {
    }

    public static void copy(int readTarget, int writeTarget, long fromOffset, long toOffset, long length) {
        GL31.glCopyBufferSubData(readTarget, writeTarget, fromOffset, toOffset, length);
    }

    public static <B extends GlBuffer> void copy(B fromBuffer, B toBuffer, long fromOffset, long toOffset, long length) {
        fromBuffer.bindCopyRead();
        toBuffer.bindCopyWrite();

        copy(GL_COPY_READ_BUFFER, GL_COPY_WRITE_BUFFER, fromOffset, toOffset, length);
    }
}

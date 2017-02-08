package xyz.upperlevel.ulge.opengl.buffer;

import lombok.Getter;
import org.lwjgl.opengl.GL15;

import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.glGenBuffers;

public abstract class GLBuffer {

    @Getter
    private int id;

    public GLBuffer() {
        id = glGenBuffers();
    }

    public void strictBind(int bindMode) {
        glBindBuffer(bindMode, id);
    }

    public void destroy() {
        glDeleteBuffers(id);
    }
}
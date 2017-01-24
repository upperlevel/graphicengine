package xyz.upperlevel.ulge.opengl.buffer;

import lombok.Getter;

import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;

public class VAO {

    @Getter
    public final int id;

    /**
     * Generates a new VAO buffer.
     */
    public VAO() {
        id = glGenBuffers();
    }

    /**
     * Binds this VAO buffer.
     */
    public void bind() {
        glBindVertexArray(id);
    }

    /**
     * Unbinds the using VAO buffer. It does not checks if the
     * current one is this VAO buffer.
     */
    public void unbind() {
        glBindVertexArray(0);
    }

    /**
     * Destroys this VAO buffer.
     */
    public void destroy() {
        glDeleteVertexArrays(id);
    }

    /**
     * Generates a new VAO buffer.
     */
    public static VAO generate() {
        return new VAO();
    }
}

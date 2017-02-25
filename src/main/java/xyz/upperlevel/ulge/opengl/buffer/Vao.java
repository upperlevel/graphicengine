package xyz.upperlevel.ulge.opengl.buffer;

import lombok.Getter;

import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;

public class Vao {

    public static Vao bound;

    @Getter
    public final int id;

    public Vao() {
        id = glGenBuffers();
    }

    public Vao(int id) {
        this.id = id;
    }

    public Vao bind() {
        if (bound == null || bound.id != id) {
            glBindVertexArray(id);
            bound = this;
        }
        return this;
    }

    public Vao forceBind() {
        glBindVertexArray(id);
        bound = this;
        return this;
    }

    public Vao unbind() {
        if (bound != null) {
            glBindVertexArray(0);
            bound = null;
        }
        return this;
    }

    public Vao forceUnbind() {
        glBindVertexArray(0);
        bound = null;
        return this;
    }

    public Vao destroy() {
        glDeleteVertexArrays(id);
        return this;
    }

    public Vao draw(int drawMode, int startOffset, int verticesCount) {
        glDrawArrays(drawMode, startOffset, verticesCount);
        return this;
    }

    public Vao draw(DrawMode drawMode, int startOffset, int verticesCount) {
        draw(drawMode.getId(), startOffset, verticesCount);
        return this;
    }

    public static Vao generate() {
        return new Vao();
    }

    public static Vao wrap(int id) {
        return new Vao(id);
    }
}

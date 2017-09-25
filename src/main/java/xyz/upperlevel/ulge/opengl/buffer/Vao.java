package xyz.upperlevel.ulge.opengl.buffer;

import lombok.Getter;

import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL30.*;

public class Vao {
    public static int instances = 0;
    public static Vao bound;

    @Getter
    public final int id;

    public Vao() {
        id = glGenVertexArrays();
        instances++;
    }

    public Vao(int id) {
        this.id = id;
        instances++;
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
        instances--;
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

package xyz.upperlevel.ulge.opengl.buffer;

import lombok.Getter;

import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;

public class VAO {

    public static VAO bound;

    @Getter
    public final int id;

    public VAO() {
        id = glGenBuffers();
    }

    public VAO(int id) {
        this.id = id;
    }

    public VAO bind() {
        if (bound == null || bound.id != id) {
            glBindVertexArray(id);
            bound = this;
        }
        return this;
    }

    public VAO forceBind() {
        glBindVertexArray(id);
        bound = this;
        return this;
    }

    public VAO unbind() {
        if (bound != null) {
            glBindVertexArray(0);
            bound = null;
        }
        return this;
    }

    public VAO forceUnbind() {
        glBindVertexArray(0);
        bound = null;
        return this;
    }

    public VAO destroy() {
        glDeleteVertexArrays(id);
        return this;
    }

    public VAO draw(int drawMode, int startOffset, int verticesCount) {
        glDrawArrays(drawMode, startOffset, verticesCount);
        return this;
    }

    public VAO draw(DrawMode drawMode, int startOffset, int verticesCount) {
        draw(drawMode.getId(), startOffset, verticesCount);
        return this;
    }

    public static VAO generate() {
        return new VAO();
    }

    public static VAO wrap(int id) {
        return new VAO(id);
    }
}

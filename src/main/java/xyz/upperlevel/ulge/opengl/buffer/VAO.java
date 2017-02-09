package xyz.upperlevel.ulge.opengl.buffer;

import lombok.Getter;

import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;

public class VAO {

    private static VAO bound;

    @Getter
    private int id;

    public VAO() {
        id = glGenBuffers();
    }

    public VAO bind() {
        if (!equals(bound)) {
            glBindVertexArray(id);
            bound = this;
        }
        return this;
    }

    public VAO unbind() {
        if (equals(bound)) {
            glBindVertexArray(0);
            bound = null;
        }
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

    public static VAO getBound() {
        return bound;
    }

    @Deprecated
    public static void setBound(VAO vao) {
        bound = vao;
    }
}

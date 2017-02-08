package xyz.upperlevel.ulge.opengl.buffer;

import lombok.Getter;

import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;

public class VAO {

    @Getter
    private int id;

    public VAO() {
        id = glGenBuffers();
    }

    public void bind() {
        glBindVertexArray(id);
    }

    public void unbind() {
        glBindVertexArray(0);
    }

    public void destroy() {
        glDeleteVertexArrays(id);
    }

    public void draw(int drawMode, int startOffset, int verticesCount) {
        glDrawArrays(drawMode, startOffset, verticesCount);
    }

    public void draw(DrawMode drawMode, int startOffset, int verticesCount) {
        draw(drawMode.getId(), startOffset, verticesCount);
    }

    public static VAO generate() {
        return new VAO();
    }
}

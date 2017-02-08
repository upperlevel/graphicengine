package xyz.upperlevel.ulge.opengl.buffer;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;

public enum DrawMode {

    POINTS(GL_POINTS),

    LINES(GL_LINES),

    TRIANGLES(GL_TRIANGLES),

    @Deprecated
    QUADS(GL_QUADS);

    @Getter
    private int id;

    DrawMode(int id) {
        this.id = id;
    }
}
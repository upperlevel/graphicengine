package xyz.upperlevel.ulge.opengl.buffer;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.lwjgl.opengl.GL11;

@RequiredArgsConstructor
public enum DrawMode {

    POINTS(GL11.GL_POINTS),

    LINES(GL11.GL_LINES),

    TRIANGLES(GL11.GL_TRIANGLES),

    @Deprecated
    QUADS(GL11.GL_QUADS);

    @Getter
    public final int id;
}
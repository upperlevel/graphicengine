package xyz.upperlevel.ulge.opengl.buffer;

import lombok.Getter;

import static org.lwjgl.opengl.GL15.GL_DYNAMIC_DRAW;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;

public enum VboDataUsage {

    STATIC_DRAW (GL_STATIC_DRAW),
    DYNAMIC_DRAW(GL_DYNAMIC_DRAW);

    @Getter
    private int id;

    VboDataUsage(int id) {
        this.id = id;
    }
}
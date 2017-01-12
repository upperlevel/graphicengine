package xyz.upperlevel.graphicengine.api.opengl.buffer;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static org.lwjgl.opengl.GL15.GL_DYNAMIC_DRAW;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;

@RequiredArgsConstructor
public enum EBOUsage {

    STATIC_DRAW(GL_STATIC_DRAW),
    DYNAMIC_DRAW(GL_DYNAMIC_DRAW);

    @Getter
    public final int id;
}

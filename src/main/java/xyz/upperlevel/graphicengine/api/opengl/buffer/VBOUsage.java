package xyz.upperlevel.graphicengine.api.opengl.buffer;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.lwjgl.opengl.GL15;

@RequiredArgsConstructor
public enum VBOUsage {

    STATIC_DRAW(GL15.GL_STATIC_DRAW),
    DYNAMIC_DRAW(GL15.GL_DYNAMIC_DRAW);

    @Getter
    public final int id;
}
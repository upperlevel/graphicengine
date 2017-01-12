package xyz.upperlevel.graphicengine.api.opengl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.lwjgl.opengl.GL11;

@RequiredArgsConstructor
public enum NumberType {

    BYTE(GL11.GL_BYTE),
    UNSIGNED_BYTE(GL11.GL_UNSIGNED_BYTE),

    SHORT(GL11.GL_SHORT),
    UNSIGNED_SHORT(GL11.GL_UNSIGNED_SHORT),

    INT(GL11.GL_INT),
    UNSIGNED_INT(GL11.GL_UNSIGNED_INT),

    FLOAT(GL11.GL_FLOAT),
    DOUBLE(GL11.GL_DOUBLE);

    @Getter
    public final int id;
}
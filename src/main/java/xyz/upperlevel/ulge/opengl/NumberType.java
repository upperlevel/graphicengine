package xyz.upperlevel.ulge.opengl;

import lombok.RequiredArgsConstructor;
import org.lwjgl.opengl.GL11;

@RequiredArgsConstructor
public enum NumberType {

    BYTE(GL11.GL_BYTE, Byte.BYTES),
    UNSIGNED_BYTE(GL11.GL_UNSIGNED_BYTE, Byte.BYTES),

    SHORT(GL11.GL_SHORT, Short.BYTES),
    UNSIGNED_SHORT(GL11.GL_UNSIGNED_SHORT, Short.BYTES),

    INT(GL11.GL_INT, Integer.BYTES),
    UNSIGNED_INT(GL11.GL_UNSIGNED_INT, Integer.BYTES),

    FLOAT(GL11.GL_FLOAT, Float.BYTES),
    DOUBLE(GL11.GL_DOUBLE, Double.BYTES);

    public final int id;

    public final int bytes;
}
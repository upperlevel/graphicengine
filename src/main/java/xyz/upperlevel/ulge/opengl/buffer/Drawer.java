package xyz.upperlevel.ulge.opengl.buffer;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.lwjgl.opengl.GL11;
import xyz.upperlevel.ulge.opengl.DataType;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Drawer {

    public static void drawArrays(DrawMode drawMode, int startOffset, int verticesCount) {
        drawArrays(drawMode.id, startOffset, verticesCount);
    }

    public static void drawArrays(int drawMode, int startOffset, int verticesCount) {
        GL11.glDrawArrays(drawMode, startOffset, verticesCount);
    }

    public static void drawElements(DrawMode drawMode, DataType dataType, int startOffset, int elementsCount) {
        drawElements(drawMode.id, dataType, startOffset, elementsCount);
    }

    public static void drawElements(int drawMode, DataType dataType, int startOffset, int elementsCount) {
        GL11.glDrawElements(drawMode, elementsCount, dataType.id, startOffset);
    }
}

package xyz.upperlevel.graphicengine.api.opengl.buffer;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.lwjgl.opengl.GL11;
import xyz.upperlevel.graphicengine.api.opengl.NumberType;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Drawer {

    public static void drawArrays(DrawerMode drawMode, int startOffset, int verticesCount) {
        drawArrays(drawMode.id, startOffset, verticesCount);
    }

    public static void drawArrays(int drawMode, int startOffset, int verticesCount) {
        GL11.glDrawArrays(drawMode, startOffset, verticesCount);
    }

    public static void drawElements(DrawerMode drawMode, NumberType numberType, int startOffset, int elementsCount) {
        drawElements(drawMode.id, numberType, startOffset, elementsCount);
    }

    public static void drawElements(int drawMode, NumberType numberType, int startOffset, int elementsCount) {
        GL11.glDrawElements(drawMode, elementsCount, numberType.id, startOffset);
    }
}

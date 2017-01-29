package xyz.upperlevel.ulge.text;

import org.joml.Matrix4f;
import org.joml.Vector2f;

public interface TextRenderer {
    public void init();

    public default Vector2f getSize(String str) {
        return getSize(str.toCharArray());
    }

    public Vector2f getSize(char[] str);

    public void drawText2D(SuperText text, Vector2f screenCoords, float distance, float size);

    public void drawText(SuperText text, Matrix4f position);

    public void destroy();
}

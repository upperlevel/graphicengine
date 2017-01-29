package xyz.upperlevel.ulge.text;

import lombok.AllArgsConstructor;
import org.joml.Matrix4f;
import org.joml.Vector2f;

import java.nio.CharBuffer;

public abstract class TextRenderer {
    public abstract void init();

    public abstract Vector2f getSize(SuperText text, float size);

    public abstract Vector2f getSize(CharSequence str, float size);

    public Vector2f getSize(char[] str, float size) {
        return getSize(CharBuffer.wrap(str), size);
    }

    public void drawText2D(SuperText text, Vector2f screenCoords, TextOrigin pos, float distance, float scale) {
        pos.translate(screenCoords, scale, pos.needsSize ? getSize(text, scale) : null);
        drawText2D0(text, screenCoords, distance, scale);
    }


    protected abstract void drawText2D0(SuperText text, Vector2f screenCoords, float distance, float scale);

    public abstract void drawText(SuperText text, Matrix4f position);

    public abstract void destroy();

    @AllArgsConstructor
    public enum TextOrigin {
        CENTER_LEFT(true) {
            @Override
            public void translate(Vector2f o, float scale, Vector2f s) {
                o.y += s.y/2f - scale;
            }
        }, CENTER(true) {
            @Override
            public void translate(Vector2f o, float scale, Vector2f s) {
                o.x -= s.x / 2f;
                o.y += s.y / 2f - scale;
            }
        }, CENTER_RIGHT(true) {
            @Override
            public void translate(Vector2f o, float scale, Vector2f s) {
                o.x -= s.x;
                o.y += s.y / 2f - scale;
            }
        },

        BOTTOM_LEFT(true) {
            @Override
            public void translate(Vector2f o, float scale, Vector2f s) {
                o.y += s.y - scale;
            }
        }, BOTTOM_CENTER(true) {
            @Override
            public void translate(Vector2f o, float scale, Vector2f s) {
                o.y += s.y - scale;
                o.x -= s.x / 2f;
            }
        }, BOTTOM_RIGHT(true) {
            @Override
            public void translate(Vector2f o, float scale, Vector2f s) {
                o.x -= s.x;
                o.y += s.y - scale;
            }
        },

        UPPER_LEFT(false) {
            @Override
            public void translate(Vector2f o, float scale, Vector2f s) {
                o.y -= scale;
            }
        }, UPPER_CENTER(true) {
            @Override
            public void translate(Vector2f o, float scale, Vector2f s) {
                o.x -= s.x / 2f;
                o.y -= scale;
            }
        }, UPPER_RIGHT(true) {
            @Override
            public void translate(Vector2f o, float scale, Vector2f s) {
                o.x -= s.x;
                o.y -= scale;
            }
        };

        public final boolean needsSize;

        public abstract void translate(Vector2f o, float scale, Vector2f s);
    }
}

package xyz.upperlevel.ulge.gui;

import xyz.upperlevel.ulge.opengl.texture.Texture2D;
import xyz.upperlevel.ulge.util.Color;

public interface GuiBackground {
    void apply(GuiRenderer renderer);

    public static GuiBackground color(Color color) {
        return (r) -> {
            r.setTexture(Texture2D.NULL);
            r.setColor(color);
        };
    }

    public static GuiBackground texture(Texture2D texture) {
        return (r) -> {
            r.setTexture(texture);
            r.setColor(Color.WHITE);
        };
    }

    public static GuiBackground transparent() {
        return TRANSPARENT;
    }

    public static final GuiBackground TRANSPARENT = (r) -> {
        r.setTexture(Texture2D.NULL);
        r.setColor(Color.TRANSPARENT);
    };
}

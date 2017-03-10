package xyz.upperlevel.ulge.gui;

import xyz.upperlevel.ulge.opengl.texture.Texture2d;
import xyz.upperlevel.ulge.util.Color;

public interface GuiBackground {
    void apply(GuiRenderer renderer);

    static GuiBackground color(Color color) {
        return (r) -> {
            r.setTexture(Texture2d.NULL);
            r.setColor(color);
        };
    }

    static GuiBackground texture(Texture2d texture) {
        return (r) -> {
            r.setTexture(texture);
            r.setColor(Color.WHITE);
        };
    }

    static GuiBackground transparent() {
        return TRANSPARENT;
    }

    GuiBackground TRANSPARENT = (r) -> {
        r.setTexture(Texture2d.NULL);
        r.setColor(Color.TRANSPARENT);
    };
}

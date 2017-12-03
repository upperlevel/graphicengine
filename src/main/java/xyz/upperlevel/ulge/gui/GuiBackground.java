package xyz.upperlevel.ulge.gui;

import xyz.upperlevel.ulge.opengl.texture.Texture2d;
import xyz.upperlevel.ulge.util.Color;
import xyz.upperlevel.ulge.window.Window;

public interface GuiBackground {
    void render(Window window, GuiBounds bounds);

    GuiBackground TRANSPARENT = (w, b) -> {};

    static GuiBackground color(Color color) {
        return (window, bounds) -> {
            GuiRenderer r = GuiRenderer.get();
            r.setColor(color);
            r.setTexture(Texture2d.NULL);
            r.render(window, bounds);
        };
    }

    static GuiBackground texture(Texture2d texture) {
        return (window, bounds) -> {
            GuiRenderer r = GuiRenderer.get();
            r.setColor(Color.TRANSPARENT);
            r.setTexture(texture);
            r.render(window, bounds);
        };
    }

    static GuiBackground of(Texture2d texture, Color color) {
        return (window, bounds) -> {
            GuiRenderer r = GuiRenderer.get();
            r.setColor(color);
            r.setTexture(texture);
            r.render(window, bounds);
        };
    }
}

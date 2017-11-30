package xyz.upperlevel.ulge.gui.impl;

import lombok.Getter;
import xyz.upperlevel.ulge.gui.BaseGui;
import xyz.upperlevel.ulge.gui.GuiBounds;
import xyz.upperlevel.ulge.gui.GuiRenderer;
import xyz.upperlevel.ulge.opengl.texture.Texture2d;
import xyz.upperlevel.ulge.util.Color;

/**
 * A simple pane used as background, supporting simple color and texture
 */
public class GuiPane extends BaseGui {

    @Getter
    private Color color = Color.WHITE;

    @Getter
    private Texture2d texture = Texture2d.NULL;

    /**
     * Sets the background color
     * @param color the new background color
     */
    public void setColor(Color color) {
        this.color = color == null ? Color.TRANSPARENT : color;
    }

    /**
     * Sets the background texture
     * @param texture the new background texture
     */
    public void setTexture(Texture2d texture) {
        this.texture = texture == null ? Texture2d.NULL : texture;
    }

    @Override
    public void render(GuiBounds upperBounds) {
        GuiBounds bounds = upperBounds.insideRelative(getBounds());
        GuiRenderer renderer = GuiRenderer.get();
        renderer.setColor(color);
        renderer.setTexture(texture);
        renderer.render(bounds);
    }
}

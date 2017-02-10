package xyz.upperlevel.ulge.gui.impl;

import org.joml.Vector2f;
import xyz.upperlevel.ulge.gui.BaseGui;
import xyz.upperlevel.ulge.gui.Bounds;
import xyz.upperlevel.ulge.gui.GuiRenderer;
import xyz.upperlevel.ulge.util.Color;

public class Button extends BaseGui {
    private final Color color, hoverColor, clickColor;

    private boolean hover, click;

    public Button(Bounds bounds, Color color, Color hoverColor, Color clickColor) {
        super(bounds);
        this.color = color;
        this.hoverColor = hoverColor;
        this.clickColor = clickColor;
    }

    @Override
    public boolean hover(Vector2f pos) {
        if(super.hover(pos)) {
            hover = pos != null;
            return true;
        } else return false;
    }

    @Override
    public boolean clickBegin(Vector2f pos) {
        if(super.clickBegin(pos)) {
            click = true;
            return true;
        } else return false;
    }

    @Override
    public boolean clickEnd(Vector2f pos) {
        if(super.clickEnd(pos)) {
            click = false;
            return true;
        } else return false;
    }

    @Override
    public void draw(GuiRenderer r) {
        super.draw(r);
        r.setColor(click ? clickColor : hover ? hoverColor : color);
    }
}

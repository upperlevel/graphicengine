package xyz.upperlevel.ulge.gui.impl;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.joml.Vector2f;
import xyz.upperlevel.ulge.gui.BaseGui;
import xyz.upperlevel.ulge.gui.Bounds;
import xyz.upperlevel.ulge.gui.GuiRenderer;
import xyz.upperlevel.ulge.util.Color;
import xyz.upperlevel.ulge.util.Colors;

public class Button extends BaseGui {

    @Getter
    @Setter
    @NonNull
    private Color
            color      = Colors.WHITE,
            hoverColor = Colors.WHITE,
            clickColor = Colors.WHITE;

    private boolean hover, click;

    public Button(Bounds bounds, Color color, Color hoverColor, Color clickColor) {
        super(bounds);
        this.color      = color;
        this.hoverColor = hoverColor;
        this.clickColor = clickColor;
    }

    @Override
    public boolean onHover(Vector2f pos) {
        if (super.onHover(pos)) {
            System.out.println("---> " + getBounds().isInside(pos));
            hover = getBounds().isInside(pos) && pos != null;
            return true;
        } else return false;
    }

    @Override
    public boolean onClickBegin(Vector2f pos) {
        if (super.onClickBegin(pos)) {
            click = true;
            return true;
        } else return false;
    }

    @Override
    public boolean onClickEnd(Vector2f pos) {
        if (super.onClickEnd(pos)) {
            click = false;
            return true;
        } else return false;
    }

    @Override
    protected void onPreDraw(GuiRenderer renderer) {
        renderer.setColor(click ? clickColor : (hover ? hoverColor : color));
    }
}

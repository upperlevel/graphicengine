package xyz.upperlevel.ulge.gui.impl;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.joml.Vector2f;
import xyz.upperlevel.ulge.gui.BaseGui;
import xyz.upperlevel.ulge.gui.Bounds;
import xyz.upperlevel.ulge.gui.GuiRenderer;
import xyz.upperlevel.ulge.opengl.texture.Texture2D;
import xyz.upperlevel.ulge.util.Color;
import xyz.upperlevel.ulge.util.Colors;

public class Button extends BaseGui {

    @Getter
    @Setter
    @NonNull
    private Color
            defColor   = Colors.WHITE,
            enterColor = Colors.AQUA,
            hoverColor = Colors.BLUE,
            clickColor = Colors.BLACK;

    private boolean hover, click, mouse;

    public Button(Bounds bounds, Color color, Color hoverColor, Color clickColor) {
        super(bounds);
        this.defColor   = color;
        this.hoverColor = hoverColor;
        this.clickColor = clickColor;
    }

    @Override
    public boolean onMouseMove(Vector2f lastPos, Vector2f pos) {
        if (super.onMouseMove(lastPos, pos)) {
            hover = true;
            return true;
        } else return false;
    }

    @Override
    public boolean onMouseEnter(Vector2f pos) {
        if(super.onMouseEnter(pos)) {
            mouse = true;
            return true;
        } else return false;
    }

    @Override
    public boolean onMouseExit(Vector2f lastPos) {
        if(super.onMouseExit(lastPos)) {
            mouse = false;
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
    public void render(GuiRenderer renderer) {
        renderer.setTexture(Texture2D.NULL);

        Color color;

        if(!mouse)
            color = this.defColor;
        else if(click)
            color = this.clickColor;
        else if(hover)
            color = this.hoverColor;
        else
            color = enterColor;

        renderer.setColor(color);
        renderer.fill();
    }
}

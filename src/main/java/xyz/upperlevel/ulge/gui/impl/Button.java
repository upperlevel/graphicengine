package xyz.upperlevel.ulge.gui.impl;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.joml.Vector2f;
import xyz.upperlevel.ulge.gui.BaseGui;
import xyz.upperlevel.ulge.gui.Bounds;
import xyz.upperlevel.ulge.gui.GuiBackground;
import xyz.upperlevel.ulge.gui.GuiRenderer;
import xyz.upperlevel.ulge.util.Color;

import static xyz.upperlevel.ulge.gui.GuiBackground.color;

public class Button extends BaseGui {

    @Getter
    @Setter
    @NonNull
    private GuiBackground
            defColor   = color(Color.WHITE),
            enterColor = color(Color.AQUA),
            hoverColor = color(Color.BLUE),
            clickColor = color(Color.BLACK);

    private boolean hover, click, mouse;

    public Button(Bounds bounds, GuiBackground color, GuiBackground hoverColor, GuiBackground clickColor) {
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
        GuiBackground bkg;

        if(!mouse)
            bkg = this.defColor;
        else if(click)
            bkg = this.clickColor;
        else if(hover)
            bkg = this.hoverColor;
        else
            bkg = enterColor;

        bkg.apply(renderer);
        renderer.fill();
    }
}

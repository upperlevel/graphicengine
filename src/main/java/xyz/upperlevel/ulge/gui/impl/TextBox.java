package xyz.upperlevel.ulge.gui.impl;

import lombok.Getter;
import lombok.Setter;
import org.joml.Matrix4f;
import xyz.upperlevel.ulge.gui.Gui;
import xyz.upperlevel.ulge.gui.GuiRenderer;
import xyz.upperlevel.ulge.text.SuperText;

public class TextBox extends Gui {

    @Getter
    @Setter
    private SuperText text = new SuperText();

    public TextBox() {
    }

    @Override
    public void render(Matrix4f transformation, GuiRenderer renderer) {
        super.render(transformation, renderer);

    }
}
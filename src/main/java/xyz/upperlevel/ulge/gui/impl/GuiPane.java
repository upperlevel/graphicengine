package xyz.upperlevel.ulge.gui.impl;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.joml.Matrix4f;
import xyz.upperlevel.ulge.gui.Gui;
import xyz.upperlevel.ulge.gui.GuiRenderer;
import xyz.upperlevel.ulge.opengl.texture.Texture2d;
import xyz.upperlevel.ulge.util.Color;

public class GuiPane extends Gui {

    @Getter
    private Color color = Color.WHITE;

    @Getter
    private Texture2d texture = Texture2d.NULL;

    public GuiPane() {
    }

    @Builder
    public GuiPane(Color color, Texture2d texture) {
        this.color = color;
        this.texture = texture;
    }

    public void setColor(Color color) {
        this.color = color == null ? Color.WHITE : color;
    }

    public void setTexture(Texture2d texture) {
        this.texture = texture == null ? Texture2d.NULL : texture;
    }

    @Override
    public void render(Matrix4f transformation, GuiRenderer renderer) {
        super.render(transformation, renderer);

        renderer.setColor(color);
        renderer.setTexture(texture);

        renderer.render();
    }
}

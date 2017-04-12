package xyz.upperlevel.ulge.gui.impl;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.joml.Matrix4f;
import xyz.upperlevel.ulge.gui.GuiRenderer;

@Accessors(chain = true, fluent = true)
public class GuiButton extends GuiPane {

    @Getter
    @Setter
    private Runnable onClick = null;

    public GuiButton() {
    }
    
    @Override
    public boolean onClickEnd(double x, double y) {
        if (onClick != null) {
            onClick.run();
            return true;
        }
        return false;
    }

    @Override
    public void render(Matrix4f transformation, GuiRenderer renderer) {
        super.render(transformation, renderer);

        // todo render text
    }


}

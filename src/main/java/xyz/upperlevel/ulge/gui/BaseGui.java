package xyz.upperlevel.ulge.gui;

import lombok.Getter;
import lombok.Setter;
import xyz.upperlevel.event.EventManager;
import xyz.upperlevel.ulge.window.event.button.MouseButton;

public class BaseGui implements Gui {
    @Getter
    private boolean clicked = false;

    @Getter
    private boolean hovered = false;

    @Getter
    @Setter
    private EventManager eventManager = new EventManager();

    @Getter
    private double x = 0, y = 0, w = 1, h = 1;

    // Lazy initialization
    private GuiBounds bounds = GuiBounds.FULL;

    @Override
    public GuiBounds getBounds() {
        if (bounds == null) {
            bounds = new GuiBounds(x, y, x + w, y + h);
        }
        return bounds;
    }

    @Override
    public void setBounds(GuiBounds bounds) {
        this.bounds = bounds;
        x = bounds.minX;
        y = bounds.minY;
        w = bounds.maxX - x;
        h = bounds.maxY - y;
    }

    @Override
    public void setX(double x) {
        this.x = x;
        bounds = null;
    }

    @Override
    public void setY(double y) {
        this.y = y;
        bounds = null;
    }

    @Override
    public void setW(double w) {
        this.w = w;
        bounds = null;
    }

    @Override
    public void setH(double h) {
        this.h = h;
        bounds = null;
    }

    @Override
    public void onCursorEnter(double x, double y) {
        Gui.super.onCursorEnter(x, y);
        hovered = true;
    }

    @Override
    public void onCursorExit(double x, double y) {
        Gui.super.onCursorExit(x, y);
        hovered = false;
        clicked = false;
    }

    @Override
    public void onClickBegin(double x, double y, MouseButton button) {
        Gui.super.onClickBegin(x, y, button);
        if (button == MouseButton.LEFT) {
            clicked = true;
        }
    }

    @Override
    public void onClickEnd(double x, double y, MouseButton button) {
        Gui.super.onClickEnd(x, y, button);
        if (button == MouseButton.LEFT) {
            clicked = false;
        }
    }

    @Override
    public void render(GuiBounds bounds) {
    }
}

package xyz.upperlevel.ulge.gui;

import lombok.Getter;
import xyz.upperlevel.ulge.gui.impl.GuiContainer;

public class GuiViewer {

    private GuiContainer container = new GuiContainer();

    public GuiViewer() {
        container.setX(0);
        container.setY(0);
        container.setWidth(1);
        container.setHeight(1);
    }

    public void open(Gui gui) {
        container.add(gui);
    }

    public void close(Gui gui) {
        container.remove(gui);
    }

    public void clickBegin(double x, double y) {
        container.onClickBegin(x, y);
    }

    public void clickEnd(double x, double y) {
        container.onClickEnd(x, y);
    }

    public void move(double startX, double startY, double endX, double endY) {
        // cursor move handles cursor enter, cursor exit and cursor drag events
        container.onCursorMove(startX, startY, endX, endY);
    }
}
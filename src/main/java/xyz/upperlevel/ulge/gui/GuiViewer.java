package xyz.upperlevel.ulge.gui;

import xyz.upperlevel.ulge.gui.impl.GuiContainer;

import java.util.List;

public class GuiViewer {

    protected final GuiContainer container = new GuiContainer();

    public GuiViewer() {
        container.setPosition(0, 0);
        container.setSize(1, 1);
        container.onOpen();
    }

    public void open(Gui gui) {
        gui.onOpen();
        container.add(gui);
    }

    public void close(Gui gui) {
        gui.onClose();
        if (!container.remove(gui)) {
            throw new IllegalStateException("Closing a Gui without opening it");
        }
    }

    public List<Gui> getGuis() {
        return container.getAll();
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
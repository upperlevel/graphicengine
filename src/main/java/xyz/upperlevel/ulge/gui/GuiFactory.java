package xyz.upperlevel.ulge.gui;

import xyz.upperlevel.ulge.gui.impl.GuiButton;
import xyz.upperlevel.ulge.gui.impl.GuiContainer;
import xyz.upperlevel.ulge.gui.impl.GuiPane;

public final class GuiFactory {

    private GuiFactory() {
    }

    public static GuiViewer viewer() {
        return new GuiViewer();
    }

    public static GuiButton button() {
        return new GuiButton();
    }

    public static GuiPane pane() {
        return new GuiPane();
    }

    public static GuiContainer container() {
        return new GuiContainer();
    }
}

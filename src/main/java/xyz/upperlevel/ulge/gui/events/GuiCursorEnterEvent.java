package xyz.upperlevel.ulge.gui.events;

import lombok.Getter;
import lombok.NonNull;
import xyz.upperlevel.ulge.gui.Gui;

public class GuiCursorEnterEvent extends GuiEvent {

    @Getter
    private double x, y;

    public GuiCursorEnterEvent(@NonNull Gui gui, double x, double y) {
        super(gui);
        this.x = x;
        this.y = y;
    }
}

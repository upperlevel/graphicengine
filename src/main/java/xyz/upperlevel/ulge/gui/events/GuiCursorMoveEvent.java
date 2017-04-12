package xyz.upperlevel.ulge.gui.events;

import lombok.Getter;
import lombok.NonNull;
import xyz.upperlevel.ulge.gui.Gui;

public class GuiCursorMoveEvent extends GuiEvent {

    @Getter
    private double startX, startY, endX, endY;

    public GuiCursorMoveEvent(@NonNull Gui gui, double startX, double startY, double endX, double endY) {
        super(gui);
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }
}
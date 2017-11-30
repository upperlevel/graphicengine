package xyz.upperlevel.ulge.gui.events;

import lombok.Getter;
import lombok.NonNull;
import xyz.upperlevel.ulge.gui.Gui;
import xyz.upperlevel.ulge.window.event.button.MouseButton;

public class GuiClickEndEvent extends GuiEvent {

    @Getter
    private final double x, y;
    @Getter
    private final MouseButton button;

    public GuiClickEndEvent(@NonNull Gui gui, double x, double y, MouseButton button) {
        super(gui);
        this.x = x;
        this.y = y;
        this.button = button;
    }
}

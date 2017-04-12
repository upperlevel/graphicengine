package xyz.upperlevel.ulge.gui.events;

import lombok.NonNull;
import xyz.upperlevel.ulge.gui.Gui;

public class GuiCloseEvent extends GuiEvent {

    public GuiCloseEvent(@NonNull Gui gui) {
        super(gui);
    }
}

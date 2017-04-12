package xyz.upperlevel.ulge.gui.events;

import lombok.NonNull;
import xyz.upperlevel.ulge.gui.Gui;

public class GuiOpenEvent extends GuiEvent {

    public GuiOpenEvent(@NonNull Gui gui) {
        super(gui);
    }
}

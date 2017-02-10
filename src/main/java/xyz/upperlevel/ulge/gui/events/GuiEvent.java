package xyz.upperlevel.ulge.gui.events;

import lombok.Getter;
import xyz.upperlevel.ulge.events.CancellableEvent;
import xyz.upperlevel.ulge.gui.Gui;

public class GuiEvent extends CancellableEvent{
    @Getter
    public final Gui gui;

    public GuiEvent(Gui gui) {
        this.gui = gui;
    }
}

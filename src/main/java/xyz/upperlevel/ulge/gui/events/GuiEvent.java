package xyz.upperlevel.ulge.gui.events;

import lombok.Getter;
import xyz.upperlevel.ulge.gui.Gui;
import xyz.upperlevel.utils.event.CancellableEvent;

public class GuiEvent extends CancellableEvent {
    @Getter
    public final Gui gui;

    public GuiEvent(Gui gui) {
        this.gui = gui;
    }
}

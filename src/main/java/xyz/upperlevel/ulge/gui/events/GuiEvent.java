package xyz.upperlevel.ulge.gui.events;

import lombok.Getter;
import lombok.NonNull;
import xyz.upperlevel.ulge.gui.Gui;
import xyz.upperlevel.utils.event.CancellableEvent;

public class GuiEvent extends CancellableEvent {

    @Getter
    private Gui gui;

    public GuiEvent(@NonNull Gui gui) {
        this.gui = gui;
    }
}

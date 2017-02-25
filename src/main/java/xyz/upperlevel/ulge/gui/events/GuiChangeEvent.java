package xyz.upperlevel.ulge.gui.events;

import lombok.Getter;
import lombok.NonNull;
import xyz.upperlevel.ulge.gui.Gui;

public class GuiChangeEvent extends GuiEvent {

    @Getter
    private final Gui substitute;

    public GuiChangeEvent(Gui old, @NonNull Gui substitute) {
        super(old);
        this.substitute = substitute;
    }
}

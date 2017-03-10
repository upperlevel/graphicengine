package xyz.upperlevel.ulge.gui;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import xyz.upperlevel.utils.event.EventManager;
import xyz.upperlevel.utils.event.impl.SimpleEventManager;

public abstract class BaseGui extends Gui {
    @Getter
    @Setter
    @NonNull
    private EventManager eventManager;

    public BaseGui(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    public BaseGui() {
        this(new SimpleEventManager());
    }

    @Override
    public void init(GuiRenderer renderer) {}
}

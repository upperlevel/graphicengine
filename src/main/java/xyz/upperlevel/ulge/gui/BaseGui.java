package xyz.upperlevel.ulge.gui;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import xyz.upperlevel.utils.event.EventManager;
import xyz.upperlevel.utils.event.impl.SimpleEventManager;

public abstract class BaseGui implements Gui {

    @Getter
    @Setter
    @NonNull
    private Bounds bounds;

    @Getter
    @Setter
    @NonNull
    private EventManager eventManager;

    public BaseGui(Bounds bounds, EventManager eventManager) {
        this.bounds = bounds;
        this.eventManager = eventManager;
    }

    public BaseGui(Bounds bounds) {
        this(bounds, new SimpleEventManager());
    }

    public BaseGui() {
        this(Bounds.FULL, new SimpleEventManager());
    }

    @Override
    public void init(GuiRenderer renderer) {
    }

    @Override
    public void draw(GuiRenderer renderer) {
        renderer.pushAndSetBounds(bounds);
        render(renderer);
        renderer.popBounds();
    }

    public abstract void render(GuiRenderer renderer);
}

package xyz.upperlevel.ulge.gui;

import xyz.upperlevel.ulge.events.EventManager;
import xyz.upperlevel.ulge.events.impl.SimpleEventManager;

public class BaseGui implements Gui {
    private Bounds bounds;
    private final EventManager eventManager;

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
    public void init(GuiRenderer r) {
    }

    @Override
    public void draw(GuiRenderer r) {
        r.setBounds(bounds);
    }

    @Override
    public EventManager getEventManager() {
        return eventManager;
    }

    @Override
    public Bounds getBounds() {
        return bounds;
    }

    @Override
    public void setBounds(Bounds bounds) {
        this.bounds = bounds;
    }
}

package xyz.upperlevel.ulge.gui.impl;

import lombok.Getter;
import xyz.upperlevel.ulge.gui.Bounds;
import xyz.upperlevel.ulge.gui.Gui;
import xyz.upperlevel.utils.event.EventManager;
import xyz.upperlevel.utils.event.impl.SimpleEventManager;

import java.util.Collections;
import java.util.Set;

public class SingletonContainer extends Container {

    @Getter
    private final Gui gui;

    private final Set<GuiData> guis;

    public SingletonContainer(Gui gui, Bounds bounds, EventManager manager) {
        super(bounds);
        this.gui = gui;
        this.guis = Collections.singleton(new GuiData(gui));
    }

    public SingletonContainer(Gui gui, Bounds bounds) {
        this(gui, bounds, new SimpleEventManager());
    }

    public SingletonContainer(Gui gui) {
        this(gui, Bounds.FULL, new SimpleEventManager());
    }

    @Override
    public Iterable<GuiData> guis() {
        return guis;
    }
}

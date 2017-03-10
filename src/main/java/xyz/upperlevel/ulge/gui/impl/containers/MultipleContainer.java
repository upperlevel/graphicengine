package xyz.upperlevel.ulge.gui.impl.containers;

import xyz.upperlevel.ulge.gui.Bounds;
import xyz.upperlevel.ulge.gui.Gui;
import xyz.upperlevel.utils.event.EventManager;
import xyz.upperlevel.utils.event.impl.SimpleEventManager;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MultipleContainer extends Container{
    private final Map<Gui, GuiData> guis;

    public MultipleContainer(Map<Bounds, Gui> guis, EventManager manager) {
        super(manager);
        this.guis = guis.entrySet()
                .stream()
                .collect(
                        Collectors.toMap(
                                Map.Entry::getValue,
                                e -> toData(e.getValue(), e.getKey())
                        )
                );
    }

    public MultipleContainer(Map<Bounds, Gui> guis) {
        this(guis, new SimpleEventManager());
    }

    public MultipleContainer(EventManager manager) {
        super(manager);
        this.guis = new HashMap<>();
    }

    public MultipleContainer() {
        this(new SimpleEventManager());
    }


    @Override
    public boolean add(Gui gui, Bounds bounds) {
        return guis.putIfAbsent(gui, toData(gui, bounds)) == null;
    }

    @Override
    public boolean remove(Gui gui) {
        return guis.remove(gui) != null;
    }

    protected GuiData toData(Gui gui, Bounds bounds) {
        return new GuiData(gui, bounds);
    }

    @Override
    public Iterable<GuiData> guis() {
        return guis.values();
    }
}

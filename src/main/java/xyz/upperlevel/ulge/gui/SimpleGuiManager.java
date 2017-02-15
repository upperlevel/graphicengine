package xyz.upperlevel.ulge.gui;

import xyz.upperlevel.ulge.util.UnmodifiableQueue;

import java.util.LinkedList;
import java.util.Queue;

public class SimpleGuiManager implements GuiManager {
    private final Queue<Gui> guis, immutableView;

    public static GuiRenderer defRenderer = DefaultGuiRenderer.$;

    public SimpleGuiManager(Queue<Gui> guis) {
        this.guis = guis;
        immutableView = new UnmodifiableQueue<>(guis);
    }

    public SimpleGuiManager() {
        this(new LinkedList<>());
    }

    @Override
    public boolean openGui(Gui gui) {
        if(!gui.onOpen())
            return false;

        Gui current = guis.peek();

        if(current != null) {
            if (!current.onChange(gui))
                return false;
        }
        guis.add(gui);
        return true;
    }

    @Override
    public boolean hasGui() {
        return !guis.isEmpty();
    }

    @Override
    public boolean closeGui() {
        Gui gui = guis.peek();
        if(gui == null)
            return true;

        if(!gui.onClose())
            return false;

        guis.clear();//TODO: all the other guis should have some kind of event to manage this
        return true;
    }

    @Override
    public boolean changeGui(Gui gui) {
        if(!gui.onOpen())
            return false;

        Gui current = guis.poll();

        if (current != null && !current.onChange(gui)) {
            //TODO: Who will notify the other gui?
            guis.add(current);
            return false;
        } else guis.add(gui);
        return true;
    }

    @Override
    public boolean backGui() {
        Gui gui = guis.poll();

        if(gui == null)
            return true;

        if(!gui.onClose()) {
            guis.add(gui);
            return false;
        } else {
            Gui curr = guis.peek();
            if(curr == null)
                return true;

            if(!curr.onOpen()) {
                //Fallback
                closeGui();//TODO: find some better way to manage this
                return false;
            }
        }
        return true;
    }

    @Override
    public Queue<Gui> getHistory() {
        return immutableView;
    }

    @Override
    public void render() {
        Gui current = guis.peek();
        current.draw(defRenderer);
        if(!defRenderer.isBoundsStackEmpty())
            throw new IllegalStateException("Bound stack not empty!");
    }
}

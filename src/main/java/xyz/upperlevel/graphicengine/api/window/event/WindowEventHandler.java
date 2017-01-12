package xyz.upperlevel.graphicengine.api.window.event;

import xyz.upperlevel.graphicengine.api.window.Window;

import java.util.List;

public interface WindowEventHandler<E extends WindowEvent> {

    void apply(Window window);

    List<E> getEvents();

    void register(E event);

    boolean unregister(E event);

    void clear();
}

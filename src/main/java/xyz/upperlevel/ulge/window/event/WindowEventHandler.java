package xyz.upperlevel.ulge.window.event;

import xyz.upperlevel.ulge.window.Window;

import java.util.List;

public interface WindowEventHandler<E extends WindowEvent> {

    void apply(Window window);

    List<E> getEvents();

    void register(E event);

    boolean unregister(E event);

    void clear();
}

package xyz.upperlevel.graphicengine.api.window.event;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractGLFWEventHandler<E extends WindowEvent> implements WindowEventHandler<E> {

    protected final List<E> events = Collections.synchronizedList(new LinkedList<>());

    @Override
    public List<E> getEvents() {
        return events;
    }

    @Override
    public void register(E event) {
        events.add(event);
    }

    @Override
    public boolean unregister(E event) {
        return events.remove(event);
    }

    @Override
    public void clear() {
        events.clear();
    }
}

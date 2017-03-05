package xyz.upperlevel.ulge.window.event;

import lombok.Getter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractGlfwEventHandler<E extends WindowEvent> implements WindowEventHandler<E> {

    @Getter
    protected List<E> events = Collections.synchronizedList(new LinkedList<>());

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

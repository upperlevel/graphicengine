package xyz.upperlevel.ulge.events;

import java.util.Collections;
import java.util.Iterator;

public interface EventManager {
    <E extends Event> void register(Class<E> clazz, Iterator<EventListener<E>> event);

    default <E extends Event> void register(Class<E> clazz, EventListener<E> event) {
        register(clazz, Collections.singleton(event).iterator());
    }

    boolean remove(EventListener event);

    boolean contains(EventListener event);

    void call(Event event);

    default boolean call(CancellableEvent event) {
        call((Event) event);
        return !event.isCancelled();
    }
}

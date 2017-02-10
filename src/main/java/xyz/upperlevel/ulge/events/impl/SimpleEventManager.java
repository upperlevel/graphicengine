package xyz.upperlevel.ulge.events.impl;

import xyz.upperlevel.ulge.events.CancellableEvent;
import xyz.upperlevel.ulge.events.Event;
import xyz.upperlevel.ulge.events.EventListener;
import xyz.upperlevel.ulge.events.EventManager;

import java.util.*;

public class SimpleEventManager implements EventManager {

    private final Map<Class<?>, Map<Byte, Set<EventListener>>> byListenerAndPriority = new HashMap<>();
    private final Map<Class<?>, EventListener[]> byEventBaked = new HashMap<>();

    @Override
    public <E extends Event> void register(Class<E> clazz, Iterator<EventListener<E>> listeners) {
        Map<Byte, Set<EventListener>> handlers = byListenerAndPriority.computeIfAbsent(clazz, k -> new HashMap<>());

        while (listeners.hasNext()) {
            EventListener<E> listener = listeners.next();
            Set<EventListener> l = handlers.computeIfAbsent(listener.priority, k -> new HashSet<>());
            l.add(listener);
        }
        bake(clazz);
    }

    @Override
    public boolean remove(EventListener event) {
        return byListenerAndPriority.values().stream()
                .map(m -> m.get(event.priority))
                .filter(Objects::nonNull)
                .anyMatch(l -> l.contains(event));
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean contains(EventListener event) {
        for(Map.Entry<Class<?>, Map<Byte, Set<EventListener>>> entry : byListenerAndPriority.entrySet()) {
            Set<EventListener> l = entry.getValue().get(event.priority);
            if(l != null)
                if(l.remove(event)) {
                    bake((Class)entry.getKey());
                    return true;
                }
        }
        return false;
    }

    @Override
    public void call(Event event) {
        call0(event.getClass(), event);
    }

    @SuppressWarnings("unchecked")
    protected void call0(Class<?> clazz, Event event) {//TODO: optimize the super events call in the bake method
        EventListener[] listeners = byEventBaked.get(clazz);
        if(listeners != null)
            for(EventListener listener : listeners)
                listener.call(event);

        Class<?> superClazz = clazz.getSuperclass();
        if(superClazz != Event.class && superClazz != CancellableEvent.class)
            call0(superClazz, event);
    }

    @SuppressWarnings("unchecked")
    public <E extends Event> void bake(Class<E> clazz) {
        Map<Byte, Set<EventListener<E>>> handlers = (Map) byListenerAndPriority.get(clazz);

        if (handlers != null) {
            EventListener<E>[] baked = new EventListener[handlers.values().stream().mapToInt(Set::size).sum()];
            int i = 0;

            for (Set<EventListener<E>> listeners : handlers.values())
                for (EventListener<E> listener : listeners)
                    baked[i++] = listener;
            byEventBaked.put(clazz, baked);
        }
    }
}

package xyz.upperlevel.ulge.events;

import lombok.AllArgsConstructor;

import java.util.function.Consumer;

@AllArgsConstructor
public abstract class EventListener<E extends Event> {
    public final byte priority;

    public abstract void call(E event);

    public static <E extends Event> EventListener<E> create(Consumer<E> consumer, byte priority) {
        return new SimpleEventListener<>(priority, consumer);
    }

    public static <E extends Event> EventListener<E> create(Consumer<E> consumer) {
        return new SimpleEventListener<>(EventPriority.NORMAL, consumer);
    }

    private static class SimpleEventListener<E extends Event> extends EventListener<E> {
        private final Consumer<E> consumer;

        public SimpleEventListener(byte priority, Consumer<E> consumer) {
            super(priority);
            this.consumer = consumer;
        }

        @Override
        public void call(E event) {
            consumer.accept(event);
        }

        @Override
        public int hashCode() {
            return consumer.hashCode() + priority;
        }

        @Override
        public boolean equals(Object e) {
            if (e == this) return true;
            return e instanceof SimpleEventListener && ((SimpleEventListener) e).consumer == this.consumer && ((SimpleEventListener) e).priority == this.priority;
        }
    }
}

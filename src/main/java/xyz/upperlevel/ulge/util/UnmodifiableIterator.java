package xyz.upperlevel.ulge.util;

import lombok.AllArgsConstructor;

import java.util.Iterator;
import java.util.function.Consumer;

@AllArgsConstructor
public class UnmodifiableIterator<E> implements Iterator<E> {
    private final Iterator<E> handle;

    @Override
    public boolean hasNext() {
        return handle.hasNext();
    }

    @Override
    public E next() {
        return handle.next();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void forEachRemaining(Consumer<? super E> action) {
        handle.forEachRemaining(action);
    }
}

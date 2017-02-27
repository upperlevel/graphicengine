package xyz.upperlevel.ulge.util;

import lombok.RequiredArgsConstructor;

import java.util.Iterator;
import java.util.function.Consumer;

@RequiredArgsConstructor
public class ArrayUnmodifiableIterator<E> implements Iterator<E> {
    private final E[] elems;
    private int index = 0;

    @Override
    public boolean hasNext() {
        return index < elems.length;
    }

    @Override
    public E next() {
        return elems[index++];
    }

    @Override
    public void forEachRemaining(Consumer<? super E> action) {
        for(; index < elems.length; index++)
            action.accept(elems[index]);
    }
}

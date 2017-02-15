package xyz.upperlevel.ulge.util;

import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

@AllArgsConstructor
public class UnmodifiableQueue<T> implements Queue<T> {

    private final Queue<T> handle;


    @Override
    public int size() {
        return handle.size();
    }

    @Override
    public boolean isEmpty() {
        return handle.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return handle.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return new UnmofiableIterator<>(handle.iterator());
    }

    @Override
    public Object[] toArray() {
        return handle.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return handle.toArray(a);
    }

    @Override
    public boolean add(T t) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return handle.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeIf(Predicate<? super T> filter) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean offer(T t) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public T poll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public T element() {
        return handle.element();
    }

    @Override
    public T peek() {
        return handle.peek();
    }

    public boolean equals(Object o) {
        return o == this ||
                        o != null
                        && o instanceof UnmodifiableQueue
                        && this.handle.equals(((UnmodifiableQueue) o).handle);
    }

    public int hashCode() {
        return 31 * handle.hashCode();
    }

    @Override
    public Spliterator<T> spliterator() {
        return handle.spliterator();
    }

    public Stream<T> stream() {
        return handle.stream();
    }

    @Override
    public Stream<T> parallelStream() {
        return handle.parallelStream();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        handle.forEach(action);
    }


    public static <E> UnmodifiableQueue<E> wrap(Queue<E> queue) {
        return new UnmodifiableQueue<>(queue);
    }
}

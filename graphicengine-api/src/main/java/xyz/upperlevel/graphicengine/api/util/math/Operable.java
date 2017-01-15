package xyz.upperlevel.graphicengine.api.util.math;

public interface Operable<T> {

    T set(T other);

    T add(T other);

    T sub(T other);

    T mul(T other);

    T div(T other);

    T zero();

    T normalize();

    T normalize(T dest);

    T copy();
}
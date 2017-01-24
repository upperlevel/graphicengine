package xyz.upperlevel.ulge.util.math;

public interface VecOperable<T> extends Operable<T> {

    double lengthSquared();

    default double length() {
        return Math.sqrt(lengthSquared());
    }
}
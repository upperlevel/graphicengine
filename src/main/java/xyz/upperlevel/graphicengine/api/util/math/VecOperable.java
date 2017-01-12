package xyz.upperlevel.graphicengine.api.util.math;

public interface VecOperable<T> extends Operable<T> {

    double lengthSquared();

    default double length() {
        return Math.sqrt(lengthSquared());
    }
}
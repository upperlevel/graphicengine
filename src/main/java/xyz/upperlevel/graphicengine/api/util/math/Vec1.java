package xyz.upperlevel.graphicengine.api.util.math;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.nio.ByteBuffer;

@NoArgsConstructor
@AllArgsConstructor
public class Vec1 extends DoubleVec<Vec1> {

    @Getter @Setter public double x;

    @Override
    public int size() {
        return 1;
    }

    @Override
    protected void fillBuf(ByteBuffer buf) {
        buf.putDouble(x);
    }

    @Override
    public Vec1 set(Vec1 other) {
        x = other.x;
        return this;
    }

    @Override
    public Vec1 add(Vec1 other) {
        x += other.x;
        return this;
    }

    @Override
    public Vec1 sub(Vec1 other) {
        x -= other.x;
        return this;
    }

    @Override
    public Vec1 mul(Vec1 other) {
        x *= other.x;
        return this;
    }

    @Override
    public Vec1 div(Vec1 other) {
        x /= other.x;
        return this;
    }

    @Override
    public Vec1 zero() {
        x = 0;
        return this;
    }

    @Override
    public Vec1 normalize() {
        x *= (1. / length());
        return this;
    }

    @Override
    public Vec1 normalize(Vec1 dest) {
        dest.x = x * (1. / length());
        return dest;
    }

    @Override
    public double lengthSquared() {
        return x * x;
    }

    @Override
    public double length() {
        return x;
    }

    @Override
    public Vec1 copy() {
        return new Vec1(x);
    }
}

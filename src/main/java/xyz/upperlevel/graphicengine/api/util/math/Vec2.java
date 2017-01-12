package xyz.upperlevel.graphicengine.api.util.math;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.nio.ByteBuffer;

@NoArgsConstructor
@AllArgsConstructor
public class Vec2 extends DoubleVec<Vec2> {

    @Getter @Setter public double x, y;

    @Override
    public int size() {
        return 2;
    }

    @Override
    public void fillBuf(ByteBuffer buf) {
        buf.putDouble(x);
        buf.putDouble(y);
    }

    @Override
    public Vec2 set(Vec2 other) {
        x = other.x;
        y = other.y;
        return this;
    }

    @Override
    public Vec2 add(Vec2 other) {
        x += other.x;
        y += other.y;
        return this;
    }

    @Override
    public Vec2 sub(Vec2 other) {
        x -= other.x;
        y -= other.y;
        return this;
    }

    @Override
    public Vec2 mul(Vec2 other) {
        x *= other.x;
        y *= other.y;
        return this;
    }

    @Override
    public Vec2 div(Vec2 other) {
        x /= other.x;
        y /= other.y;
        return this;
    }

    @Override
    public Vec2 zero() {
        x = 0;
        y = 0;
        return this;
    }

    @Override
    public Vec2 normalize() {
        double invLen = 1. / length();
        x *= invLen;
        y *= invLen;
        return this;
    }

    @Override
    public Vec2 normalize(Vec2 dest) {
        double invLen = 1. / length();
        dest.x = x * invLen;
        dest.y = y * invLen;
        return dest;
    }

    @Override
    public double lengthSquared() {
        return x * x + y * y;
    }

    @Override
    public Vec2 copy() {
        return new Vec2(x, y);
    }
}

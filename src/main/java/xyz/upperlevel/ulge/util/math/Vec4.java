package xyz.upperlevel.ulge.util.math;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.nio.ByteBuffer;

@NoArgsConstructor
@AllArgsConstructor
public class Vec4 extends DoubleVec<Vec4> {

    public static final Vec4 ZERO = new Vec4();

    @Getter @Setter public double x, y, z, w;

    @Override
    public int size() {
        return 4;
    }

    @Override
    protected void fillBuf(ByteBuffer buf) {
        buf.putDouble(x);
        buf.putDouble(y);
        buf.putDouble(z);
        buf.putDouble(w);
    }

    @Override
    public Vec4 set(Vec4 other) {
        x = other.x;
        y = other.y;
        z = other.z;
        w = other.w;
        return this;
    }

    @Override
    public Vec4 add(Vec4 other) {
        x += other.x;
        y += other.y;
        z += other.z;
        w += other.w;
        return this;
    }

    @Override
    public Vec4 sub(Vec4 other) {
        x -= other.x;
        y -= other.y;
        z -= other.z;
        w -= other.w;
        return this;
    }

    @Override
    public Vec4 mul(Vec4 other) {
        x *= other.x;
        y *= other.y;
        z *= other.z;
        w *= other.w;
        return this;
    }

    @Override
    public Vec4 div(Vec4 other) {
        x /= other.x;
        y /= other.y;
        z /= other.z;
        w /= other.w;
        return this;
    }

    @Override
    public Vec4 zero() {
        x = 0;
        y = 0;
        z = 0;
        w = 0;
        return this;
    }

    @Override
    public Vec4 normalize() {
        double invLen = 1. / length();
        x *= invLen;
        y *= invLen;
        z *= invLen;
        w *= invLen;
        return this;
    }

    @Override
    public double lengthSquared() {
        return x * x + y * y + z * z + w * w;
    }

    @Override
    public Vec4 copy() {
        return new Vec4(x, y, z, w);
    }
}

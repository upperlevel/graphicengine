package xyz.upperlevel.graphicengine.api.util.math;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.nio.ByteBuffer;

@NoArgsConstructor
@AllArgsConstructor
public class Vec3 extends DoubleVec<Vec3> {

    @Getter @Setter public double x, y, z;

    @Override
    public int size() {
        return 3;
    }

    @Override
    public void fillBuf(ByteBuffer buf) {
        buf.putDouble(x);
        buf.putDouble(y);
        buf.putDouble(z);
    }

    @Override
    public Vec3 set(Vec3 other) {
        x = other.x;
        y = other.y;
        z = other.z;
        return this;
    }

    @Override
    public Vec3 add(Vec3 other) {
        x += other.x;
        y += other.y;
        z += other.z;
        return this;
    }

    @Override
    public Vec3 sub(Vec3 other) {
        x -= other.x;
        y -= other.y;
        z -= other.z;
        return this;
    }

    @Override
    public Vec3 mul(Vec3 other) {
        x *= other.x;
        y *= other.y;
        z *= other.z;
        return this;
    }

    @Override
    public Vec3 div(Vec3 other) {
        x /= other.x;
        y /= other.y;
        z /= other.z;
        return this;
    }

    @Override
    public Vec3 zero() {
        x = 0;
        y = 0;
        z = 0;
        return this;
    }

    @Override
    public Vec3 normalize() {
        double invLen = 1. / length();
        x *= invLen;
        y *= invLen;
        z *= invLen;
        return this;
    }

    @Override
    public double lengthSquared() {
        return x * x + y * y + z * z;
    }

    @Override
    public Vec3 copy() {
        return new Vec3(x, y, z);
    }
}

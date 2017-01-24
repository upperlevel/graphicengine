package xyz.upperlevel.ulge.util.math;

import lombok.*;

@NoArgsConstructor
public class Rot implements Operable<Rot> {

    @Getter @Setter private double
            yaw,
            pitch,
            scroll;

    public Rot(double yaw, double pitch, double scroll) {
        setYaw(yaw);
        setPitch(pitch);
        setScroll(scroll);
    }

    @Override
    public Rot set(Rot other) {
        yaw = AngleUtil.normalizeRadAngle(other.yaw);
        pitch = AngleUtil.normalizeRadAngle(other.pitch);
        scroll = AngleUtil.normalizeRadAngle(other.scroll);
        return this;
    }

    @Override
    public Rot add(Rot other) {
        setYaw(yaw + other.yaw);
        setPitch(pitch + other.pitch);
        setScroll(scroll + other.scroll);
        return this;
    }

    @Override
    public Rot sub(Rot other) {
        setYaw(yaw - other.yaw);
        setPitch(pitch - other.pitch);
        setScroll(scroll - other.scroll);
        return this;
    }

    @Override
    public Rot mul(Rot other) {
        setYaw(yaw * other.yaw);
        setPitch(pitch * other.pitch);
        setScroll(scroll * other.scroll);
        return this;
    }

    @Override
    public Rot div(Rot other) {
        setYaw(yaw / other.yaw);
        setPitch(pitch / other.pitch);
        setScroll(scroll / other.scroll);
        return this;
    }

    @Override
    public Rot zero() {
        yaw = 0;
        pitch = 0;
        scroll = 0;
        return this;
    }

    @Override
    public Rot normalize() {
        yaw = AngleUtil.normalizeRadAngle(yaw);
        pitch = AngleUtil.normalizeRadAngle(pitch);
        scroll = AngleUtil.normalizeRadAngle(scroll);
        return this;
    }

    @Override
    public Rot copy() {
        return new Rot(yaw, pitch, scroll);
    }
}

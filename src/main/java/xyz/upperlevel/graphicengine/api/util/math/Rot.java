package xyz.upperlevel.graphicengine.api.util.math;

import lombok.*;

import static xyz.upperlevel.graphicengine.api.util.math.AngleUtil.normalizeRadAngle;

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
        yaw = normalizeRadAngle(other.yaw);
        pitch = normalizeRadAngle(other.pitch);
        scroll = normalizeRadAngle(other.scroll);
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
        yaw = normalizeRadAngle(yaw);
        pitch = normalizeRadAngle(pitch);
        scroll = normalizeRadAngle(scroll);
        return this;
    }

    @Override
    public Rot copy() {
        return new Rot(yaw, pitch, scroll);
    }
}

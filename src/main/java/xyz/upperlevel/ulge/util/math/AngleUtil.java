package xyz.upperlevel.ulge.util.math;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AngleUtil {

    public static final double ROUND_ANGLE_DEG = 360;
    public static final double ROUND_ANGLE_RAD = 2 * Math.PI;

    public static double normalizeDegAngle(double angle) {
        return ((angle % ROUND_ANGLE_DEG) + ROUND_ANGLE_DEG) % ROUND_ANGLE_DEG;
    }

    public static double normalizeRadAngle(double angle) {
        return Math.atan2(Math.sin(angle), Math.cos(angle));
    }
}

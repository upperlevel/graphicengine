package xyz.upperlevel.ulge.util.math;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public final class CameraUtil {

    private CameraUtil() {
    }

    public static Matrix4f getOrientation(float yaw, float pitch) {
        Matrix4f matrix = new Matrix4f();
        matrix.rotate(pitch, new Vector3f(1f, 0, 0));
        matrix.rotate(yaw, new Vector3f(0, 1f, 0));
        return matrix;
    }

    public static Vector3f getForward(float yaw, float pitch) {
        return getOrientation(yaw, pitch)
                .invert()
                .transformDirection(new Vector3f(0f, 0f, -1f));
    }

    public static Vector3f getRight(float yaw, float pitch) {
        return getOrientation(yaw, pitch)
                .invert()
                .transformDirection(new Vector3f(1f, 0f, 0f));
    }

    public static Vector3f getUp(float yaw, float pitch) {
        return getOrientation(yaw, pitch)
                .invert()
                .transformDirection(new Vector3f(0f, 1f, 0f));
    }

    public static Matrix4f getProjection(float fov, float aspectRatio, float nearPane, float farPane) {
        return new Matrix4f().perspective(
                fov,
                aspectRatio,
                nearPane,
                farPane);
    }

    public static Matrix4f getView(float yaw, float pitch, float x, float y, float z) {
        return getOrientation(yaw, pitch).mul(new Matrix4f().translate(-x, -y, -z));
    }

    public static Matrix4f getView(float yaw, float pitch, Vector3f position) {
        return getView(yaw, pitch, position.x, position.y, position.z);
    }

    public static Matrix4f getCamera(
            float fov, float aspectRatio, float nearPane, float farPane,
            float yaw, float pitch, float x, float y, float z) {
        return getCamera(
                getProjection(fov, aspectRatio, nearPane, farPane),
                getView(yaw, pitch, x, y, z)
        );
    }

    public static Matrix4f getCamera(Matrix4f projection, Matrix4f view) {
        return projection.mul(view);
    }
}

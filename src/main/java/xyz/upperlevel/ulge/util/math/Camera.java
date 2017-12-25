package xyz.upperlevel.ulge.util.math;

import lombok.Getter;
import lombok.Setter;
import org.joml.Matrix4f;
import org.joml.Vector3f;

@Getter
@Setter
public class Camera {
    private float x, y, z;
    private float yaw, pitch;
    private Matrix4f orientationMatrix;

    private float fov;
    private float aspectRatio;
    private float nearPlane, farPlane;

    private Matrix4f cameraMatrix;

    public Camera() {
        // Init the camera with default value, may be changed!
        x = 0f;
        y = 0f;
        z = 0f;
        yaw = 0f;
        pitch = 0f;

        fov = (float) Math.toRadians(90);
        aspectRatio = 2f / 1f;
        nearPlane = 0.1f;
        farPlane = 100f;

        updateCameraMatrix();
    }

    /**
     * Gets the forward direction vector based on current position and rotation.
     *
     * @return the forward direction
     */
    public Vector3f getForwardDirection() {
        return orientationMatrix.invert(new Matrix4f()).transformDirection(new Vector3f(0f, 0f, -1f));
    }

    /**
     * Gets the right direction vector based on current position and rotation.
     *
     * @return the right direction
     */
    public Vector3f getRightDirection() {
        return orientationMatrix.invert(new Matrix4f()).transformDirection(new Vector3f(1f, 0f, 0f));
    }

    /**
     * Gets the up direction vector based on current position and rotation.
     *
     * @return the up direction
     */
    public Vector3f getUpDirection() {
        return orientationMatrix.invert(new Matrix4f()).transformDirection(new Vector3f(0f, 1f, 0f));
    }

    /**
     * Called each time a camera attribute changes.
     */
    private void updateCameraMatrix() {
        orientationMatrix = new Matrix4f();
        orientationMatrix.rotate(pitch, 1f, 0f, 0f);
        orientationMatrix.rotate(yaw, 0f, 1f, 0f);

        Matrix4f vMat = orientationMatrix.mul(new Matrix4f().translate(-x, -y, -z));
        Matrix4f prMat = new Matrix4f().perspective(fov, aspectRatio, nearPlane, farPlane);

        cameraMatrix = prMat.mul(vMat);
    }

    /**
     * Sets the camera position.
     *
     * @param x the x-axis position
     * @param y the y-axis position
     * @param z the z-axis position
     */
    public void setPosition(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        updateCameraMatrix();
    }

    /**
     * Moves the camera of an offset position.
     *
     * @param offsetX the x-axis offset
     * @param offsetY the y-axis offset
     * @param offsetZ the z-axis offset
     */
    public void move(float offsetX, float offsetY, float offsetZ) {
        x += offsetX;
        y += offsetY;
        z += offsetZ;
        updateCameraMatrix();
    }

    /**
     * Moves the camera on a direction of an intensity.
     *
     * @param direction the direction
     * @param intensity the intensity
     */
    public void move(Vector3f direction, float intensity) {
        x += direction.x * intensity;
        y += direction.y * intensity;
        z += direction.z * intensity;
        updateCameraMatrix();
    }

    /**
     * Sets the camera rotation. Both yaw and pitch.
     *
     * @param yaw   the vertical rotation
     * @param pitch the horizontal rotation
     */
    public void setRotation(float yaw, float pitch) {
        this.yaw = yaw;
        this.pitch = pitch;
        updateCameraMatrix();
    }

    /**
     * Rotates the camera of offset rotations in radians.
     *
     * @param offsetYaw   the yaw offset rotation
     * @param offsetPitch the pitch offset rotation
     */
    public void rotate(float offsetYaw, float offsetPitch) {
        yaw += (float) AngleUtil.normalizeRadianAngle(offsetYaw);
        pitch += (float) AngleUtil.normalizeRadianAngle(offsetPitch);
        updateCameraMatrix();
    }

    /**
     * Sets the camera fov in radians.
     *
     * @param fov the fov
     */
    public void setFov(float fov) {
        this.fov = (float) AngleUtil.normalizeRadianAngle(fov);
        updateCameraMatrix();
    }

    /**
     * Sets the near and the far plane.
     * <br>
     * <b>Note: The near plane must be lower than the far plane.</b>
     *
     * @param nearPlane the near plane
     * @param farPlane  the far plane
     */
    public void setNearAndFarPlane(float nearPlane, float farPlane) {
        if (nearPlane > farPlane) {
            throw new IllegalArgumentException("Near plane must be lower than far plane");
        }
        this.nearPlane = nearPlane;
        this.farPlane = farPlane;
        updateCameraMatrix();
    }

    /**
     * Sets camera aspect ratio {@code (width / height)}.
     *
     * @param aspectRatio the aspect ratio
     */
    public void setAspectRatio(float aspectRatio) {
        if (aspectRatio < 0f) {
            throw new IllegalArgumentException("Aspect ratio cannot be negative");
        }
        this.aspectRatio = aspectRatio;
        updateCameraMatrix();
    }

    /**
     * Configures the camera to look at the given position.
     *
     * @param x the x
     * @param y the y
     * @param z the z
     */
    public void lookAt(float x, float y, float z) {
        if (this.x == x && this.y == y && this.z == z) {
            throw new IllegalArgumentException("Look-at position cannot be equal to camera position");
        }
        Vector3f dir = new Vector3f(x - this.x, y - this.y, z - this.z).normalize();
        setRotation((float) Math.toRadians(Math.asin(dir.y)), (float) Math.toRadians(Math.atan2(-dir.x, -dir.z)));
    }
}

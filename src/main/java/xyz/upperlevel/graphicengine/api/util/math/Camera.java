package xyz.upperlevel.graphicengine.api.util.math;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import static java.lang.Math.asin;
import static java.lang.Math.atan2;
import static java.lang.Math.toRadians;
import static xyz.upperlevel.graphicengine.api.util.math.AngleUtil.normalizeRadAngle;

public class Camera {

    @Getter @Setter @NonNull private Vector3f position = new Vector3f();
    @Getter @Setter @NonNull private Rot rotation = new Rot();
    @Getter private double fov = Math.PI / 4;

    @Getter private float
            nearPlane = .01f, farPlane = 100f,
            aspectRatio = 2f / 1f;

    public void setFov(double fov) {
        this.fov = normalizeRadAngle(fov);
    }

    public void setRotation(double yaw, double pitch, double scroll) {
        rotation.setYaw(yaw);
        rotation.setPitch(pitch);
        rotation.setScroll(scroll);
    }

    public void setNearAndFarPlane(float nearPl, float farPl) {
        if (nearPl < 0f)
            throw new IllegalArgumentException("Near plane cannot be negative.");
        if (farPl < nearPl)
            throw new IllegalArgumentException("Far plane cannot be lower than near plane.");
        nearPlane = nearPl;
        farPlane = farPl;
    }

    public void setAspectRatio(float aspectRatio) {
        if (aspectRatio < 0f)
            throw new IllegalArgumentException("Aspect ratio cannot be negative.");
        this.aspectRatio = aspectRatio;
    }

    public void lookAt(Vector3f lookAtPos) {
        if (!position.equals(lookAtPos))
            throw new IllegalArgumentException("Look at position cannot be equal to camera position.");
        Vector3f dir = new Vector3f();
        lookAtPos.sub(position, dir).normalize();
        rotation.setYaw(toRadians(asin(dir.y)));
        rotation.setPitch(toRadians(atan2(-dir.x, -dir.z)));
    }

    public Matrix4f getOrientation() {
        Matrix4f result = new Matrix4f();
        result.rotate((float) rotation.getPitch(), new Vector3f(1f, 0, 0));
        result.rotate((float) rotation.getYaw(), new Vector3f(0, 1f, 0));
        return result;
    }

    public Vector3f getForward() {
        return getOrientation().invert(new Matrix4f()).transformDirection(new Vector3f(0f, 0f, -1f));
    }

    public Vector3f getRight() {
        return getOrientation().invert(new Matrix4f()).transformDirection(new Vector3f(1, 0, 0));
    }

    public Vector3f getUp() {
        return getOrientation().invert(new Matrix4f()).transformDirection(new Vector3f(0, 1, 0));
    }

    public Matrix4f getProjection() {
        return new Matrix4f().perspective(
                (float) fov,
                aspectRatio,
                getNearPlane(),
                getFarPlane());
    }

    public Matrix4f getView() {
        return getOrientation().mul(new Matrix4f()
                .translate(-position.x, -position.y, -position.z));
    }

    public Matrix4f getMatrix() {
        return getProjection().mul(getView());
    }
}

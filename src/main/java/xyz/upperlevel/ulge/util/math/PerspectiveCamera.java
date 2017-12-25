package xyz.upperlevel.ulge.util.math;

import lombok.Getter;
import lombok.Setter;
import org.joml.Matrix4f;
import org.joml.Vector3f;

@Getter
public class PerspectiveCamera extends Camera {
    private float fov;
    private float aspectRatio;
    private float nearPlane, farPlane;

    private Matrix4f cameraMatrix;

    public PerspectiveCamera() {
        fov = (float) Math.toRadians(90);
        aspectRatio = 2f / 1f;
        nearPlane = 0.1f;
        farPlane = 100f;
        updateMatrices();
    }

    @Override
    protected void updateMatrices() {
        super.updateMatrices();
        Matrix4f prMat = new Matrix4f().perspective(fov, aspectRatio, nearPlane, farPlane);
        cameraMatrix = prMat.mul(getViewMatrix());
    }

    public void setFov(float fov) {
        this.fov = (float) AngleUtil.normalizeRadianAngle(Math.toRadians(fov));
        updateMatrices();
    }

    public void setAspectRatio(float aspectRatio) {
        this.aspectRatio = aspectRatio;
        updateMatrices();
    }

    public void setNearAndFarPlanes(float nearPlane, float farPlane) {
        if (nearPlane > farPlane) {
            throw new IllegalArgumentException("Near plane must be lower than far plane");
        }
        this.nearPlane = nearPlane;
        this.farPlane = farPlane;
        updateMatrices();
    }
}

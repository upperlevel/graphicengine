package xyz.upperlevel.ulge.util.math;

import lombok.Getter;
import org.joml.Matrix4f;

@Getter
public class OrthographicCamera extends Camera {
    private float minX, minY;
    private float maxX, maxY;
    private float nearPlane, farPlane;

    private Matrix4f cameraMatrix;

    public OrthographicCamera() {
        minX = -1;
        minY = -1;
        maxX = 1;
        maxY = 1;
        nearPlane = 0.1f;
        farPlane = 100f;
        updateMatrices();
    }

    @Override
    protected void updateMatrices() {
        super.updateMatrices();
        Matrix4f ortMat = new Matrix4f().setOrtho(minX, maxX, minY, maxY, nearPlane, farPlane);
        cameraMatrix = ortMat.mul(getViewMatrix());
    }

    public void setOrtho(float minX, float minY, float maxX, float maxY, float nearPlane, float farPlane) {
        if (nearPlane > farPlane) {
            throw new IllegalArgumentException("Near plane must be lower than far plane");
        }
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
        this.nearPlane = nearPlane;
        this.farPlane = farPlane;
        updateMatrices();
    }
}

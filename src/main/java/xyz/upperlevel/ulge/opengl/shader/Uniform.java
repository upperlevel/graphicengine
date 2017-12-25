package xyz.upperlevel.ulge.opengl.shader;

import lombok.Getter;
import org.joml.*;
import xyz.upperlevel.ulge.opengl.texture.Texture2d;
import xyz.upperlevel.ulge.util.Color;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL20.*;

@Getter
public class Uniform {
    private static final float[] matrixBuffer = new float[4 * 4];

    private final Program program;
    private final int location;

    public Uniform(Program program, int location) {
        this.program = program;
        this.location = location;
    }

    public void set(float f) {
        glUniform1f(location, f);
    }

    public void set(float v0, float v1) {
        glUniform2f(location, v0, v1);
    }

    public void set(float v0, float v1, float v2) {
        glUniform3f(location, v0, v1, v2);
    }

    public void set(float v0, float v1, float v2, float v3) {
        glUniform4f(location, v0, v1, v2, v3);
    }

    public void set(boolean b) {
        glUniform1i(location, b ? 1 : 0);
    }

    public void set(int i) {
        glUniform1i(location, i);
    }

    public void set(int v0, int v1) {
        glUniform2i(location, v0, v1);
    }

    public void set(int v0, int v1, int v2) {
        glUniform3i(location, v0, v1, v2);
    }

    public void set(int v0, int v1, int v2, int v3) {
        glUniform4i(location, v0, v1, v2, v3);
    }

    public void matrix4(float[] value) {
        glUniformMatrix4fv(location, false, value);
    }

    public void matrix4(FloatBuffer value) {
        glUniformMatrix4fv(location, false, value);
    }

    public void matrix3(float[] value) {
        glUniformMatrix3fv(location, false, value);
    }

    public void matrix3(FloatBuffer value) {
        glUniformMatrix3fv(location, false, value);
    }

    public void matrix2(float[] value) {
        glUniformMatrix2fv(location, false, value);
    }

    public void matrix2(FloatBuffer value) {
        glUniformMatrix2fv(location, false, value);
    }

    public void set(Matrix4f value) {
        glUniformMatrix4fv(location, false, value.get(matrixBuffer));
    }

    public void set(Matrix3f value) {
        glUniformMatrix3fv(location, false, value.get(matrixBuffer));
    }

    public void set(Color value) {
        glUniform4f(location, value.r, value.g, value.b, value.a);
    }

    public void set(Vector4f value) {
        glUniform4f(location, value.x, value.y, value.z, value.w);
    }

    public void set(Vector3f value) {
        glUniform3f(location, value.x, value.y, value.z);
    }

    public void set(Vector2f value) {
        glUniform2f(location, value.x, value.y);
    }

    public void set(Texture2d value) {
        glUniform1i(location, value.getId());
    }
}

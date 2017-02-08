package xyz.upperlevel.ulge.opengl.shader;

import lombok.AllArgsConstructor;
import org.joml.*;
import xyz.upperlevel.ulge.opengl.texture.Texture2D;
import xyz.upperlevel.ulge.util.Color;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL20.*;

@AllArgsConstructor
public class Uniform {
    public final Program program;
    public final int loc;

    public void set(float f) {
        glUniform1f(loc, f);
    }

    public void set(float v0, float v1) {
        glUniform2f(loc, v0, v1);
    }

    public void set(float v0, float v1, float v2) {
        glUniform3f(loc, v0, v1, v2);
    }

    public void set(float v0, float v1, float v2, float v3) {
        glUniform4f(loc, v0, v1, v2, v3);
    }

    public void set(boolean b) {
        glUniform1i(loc, b ? 1 : 0);
    }


    public void set(int i) {
        glUniform1i(loc, i);
    }

    public void set(int v0, int v1) {
        glUniform2i(loc, v0, v1);
    }

    public void set(int v0, int v1, int v2) {
        glUniform3i(loc, v0, v1, v2);
    }

    public void set(int v0, int v1, int v2, int v3) {
        glUniform4i(loc, v0, v1, v2, v3);
    }


    public void matrix4(float[] value) {
        glUniformMatrix4fv(loc, false, value);
    }

    public void matrix4(FloatBuffer value) {
        glUniformMatrix4fv(loc, false, value);
    }

    public void matrix3(float[] value) {
        glUniformMatrix3fv(loc, false, value);
    }

    public void matrix3(FloatBuffer value) {
        glUniformMatrix3fv(loc, false, value);
    }

    public void matrix2(float[] value) {
        glUniformMatrix2fv(loc, false, value);
    }

    public void matrix2(FloatBuffer value) {
        glUniformMatrix2fv(loc, false, value);
    }

    public void set(Matrix4f value) {
        glUniformMatrix4fv(loc, false, value.get(Uniformer.buffer));
    }

    public void set(Matrix3f value) {
        glUniformMatrix3fv(loc, false, value.get(Uniformer.buffer));
    }

    public void set(Color value) {
        glUniform4f(loc, value.r, value.g, value.b, value.a);
    }

    public void set(Vector4f value) {
        glUniform4f(loc, value.x, value.y, value.z, value.w);
    }

    public void set(Vector3f value) {
        glUniform3f(loc, value.x, value.y, value.z);
    }

    public void set(Vector2f value) {
        glUniform2f(loc, value.x, value.y);
    }

    public void set(Texture2D value) {
        glUniform1i(loc, value.id);
    }
}

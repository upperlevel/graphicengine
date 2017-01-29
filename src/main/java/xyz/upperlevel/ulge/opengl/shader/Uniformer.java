package xyz.upperlevel.ulge.opengl.shader;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import xyz.upperlevel.ulge.util.Color;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL20.*;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class Uniformer {
    public static final float[] buffer = new float[4*4];

    @Getter @NonNull public final Program program;

    public boolean hasAttrib(String uniform) {
        return getAttribLocation(uniform) >= 0;
    }

    public int getAttribLocation(String attrib) {
        return glGetAttribLocation(program.getId(), attrib);
    }

    public boolean hasUniform(String uniform) {
        return getUniLocation(uniform) >= 0;
    }

    public Uniform get(String uniform) {
        final int loc = getUniLocation(uniform);
        return loc == -1 ? null : new Uniform(program, getUniLocation(uniform));
    }

    private boolean testUniform(String uni, Runnable act) {
        if (hasUniform(uni)) {
            act.run();
            return true;
        }
        return false;
    }

    public int getUniLocation(String uniform) {
        return glGetUniformLocation(program.getId(), uniform);
    }

    public boolean setUniform(String uniform, float value) {
        return testUniform(uniform, () -> glUniform1f(getUniLocation(uniform), value));
    }

    public boolean setUniform(String uniform, int value) {
        return testUniform(uniform, () -> glUniform1i(getUniLocation(uniform), value));
    }

    public boolean setUniform(String uniform, boolean value) {
        return testUniform(uniform, () -> glUniform1i(getUniLocation(uniform), value ? 1 : 0));
    }

    public boolean setUniform(String uniform, float v1, float v2) {
        return testUniform(uniform, () -> glUniform2f(getUniLocation(uniform), v1, v2));
    }

    public boolean setUniform(String uniform, int v1, int v2) {
        return testUniform(uniform, () -> glUniform2i(getUniLocation(uniform), v1, v2));
    }

    public boolean setUniform(String uniform, float v1, float v2, float v3) {
        return testUniform(uniform, () -> glUniform3f(getUniLocation(uniform), v1, v2, v3));
    }

    public boolean setUniform(String uniform, int v1, int v2, int v3) {
        return testUniform(uniform, () -> glUniform3i(getUniLocation(uniform), v1, v2, v3));
    }

    public boolean setUniform(String uniform, float v1, float v2, float v3, float v4) {
        return testUniform(uniform, () -> glUniform4f(getUniLocation(uniform), v1, v2, v3, v4));
    }

    public boolean setUniform(String uniform, int v1, int v2, int v3, int v4) {
        return testUniform(uniform, () -> glUniform4i(getUniLocation(uniform), v1, v2, v3, v4));
    }


    public boolean setUniformMatrix4(String uniform, FloatBuffer buffer) {
        return testUniform(uniform, () -> glUniformMatrix4fv(getUniLocation(uniform), false, buffer));
    }

    public boolean setUniformMatrix4(String uniform, float[] buffer) {
        return testUniform(uniform, () -> glUniformMatrix4fv(getUniLocation(uniform), false, buffer));
    }

    public boolean setUniformMatrix3(String uniform, FloatBuffer buffer) {
        return testUniform(uniform, () -> glUniformMatrix3fv(getUniLocation(uniform), false, buffer));
    }

    public boolean setUniformMatrix3(String uniform, float[] buffer) {
        return testUniform(uniform, () -> glUniformMatrix3fv(getUniLocation(uniform), false, buffer));
    }

    public boolean setUniformMatrix2(String uniform, FloatBuffer buffer) {
        return testUniform(uniform, () -> glUniformMatrix2fv(getUniLocation(uniform), false, buffer));
    }

    public boolean setUniformMatrix2(String uniform, float[] buffer) {
        return testUniform(uniform, () -> glUniformMatrix2fv(getUniLocation(uniform), false, buffer));
    }

    public boolean setUniform(String uniform, Color color) {
        return setUniform(uniform, color.r, color.g, color.b, color.a);
    }
}
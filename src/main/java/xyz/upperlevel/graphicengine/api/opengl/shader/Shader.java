package xyz.upperlevel.graphicengine.api.opengl.shader;

import lombok.Getter;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;

public class Shader {

    @Getter public final int id;

    public Shader(ShaderType type) {
        this(type.id);
    }

    public Shader(int type) {
        id = glCreateShader(type);
    }

    public void linkSource(String source) {
        glShaderSource(id, source);
    }

    private CompileStatus getCompileStatus() {
        if (glGetShaderi(id, GL_COMPILE_STATUS) == GL_FALSE)
            return new CompileStatus(glGetShaderInfoLog(id));
        return CompileStatus.OK;
    }

    public CompileStatus compileSource() {
        glCompileShader(id);
        return getCompileStatus();
    }

    public void destroy() {
        glDeleteShader(id);
    }
}
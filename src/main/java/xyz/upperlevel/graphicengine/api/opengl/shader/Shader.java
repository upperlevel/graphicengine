package xyz.upperlevel.graphicengine.api.opengl.shader;

import lombok.Getter;
import xyz.upperlevel.graphicengine.api.opengl.shader.loader.SimpleShaderSourceLoader;

import java.io.File;
import java.io.InputStream;
import java.util.Scanner;

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

    public Shader linkSource(String source) {
        glShaderSource(id, source);
        return this;
    }

    public Shader linkResource(String path) {
        InputStream stream = Program.class.getClassLoader().getResourceAsStream(path);
        if(stream == null)
            throw new IllegalArgumentException("Cannot find " + path);
        try (Scanner s = new Scanner(stream)) {
            s.useDelimiter("\\A");
            linkSource(s.hasNext() ? s.next() : "");
        }
        return this;
    }

    public Shader linkSource(File file) {
        linkSource(new SimpleShaderSourceLoader().load(file));
        return this;
    }

    public CompileStatus getCompileStatus() {
        if (glGetShaderi(id, GL_COMPILE_STATUS) == GL_FALSE)
            return new CompileStatus(glGetShaderInfoLog(id));
        return CompileStatus.OK;
    }

    public CompileStatus compileSource() {
        glCompileShader(id);
        return getCompileStatus();
    }

    public Shader destroy() {
        glDeleteShader(id);
        return this;
    }
}
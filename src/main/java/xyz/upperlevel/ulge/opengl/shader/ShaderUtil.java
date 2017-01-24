package xyz.upperlevel.ulge.opengl.shader;

import java.io.*;

public final class ShaderUtil {

    private ShaderUtil() {
    }

    public static Shader load(ShaderType shaderType, InputStream stream) {
        Shader shader = new Shader(shaderType);
        String source;
        try {
            source = SimpleShaderSourceLoader.$().load(stream);
        } catch (IOException e) {
            throw new IllegalStateException("Error during input stream loading", e);
        }
        shader.linkSource(source);
        CompileStatus compileStatus = shader.compileSource();
        if (!compileStatus.isOk())
            throw new IllegalStateException("Failed to compile loaded source: " + compileStatus.getLog());
        return shader;
    }

    public static Shader load(ShaderType shaderType, File file) {
        try {
            return load(shaderType, new FileInputStream(file));
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File not found", e);
        }
    }
}

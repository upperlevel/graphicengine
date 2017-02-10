package xyz.upperlevel.ulge.opengl.shader;

public class ShaderCompileException extends RuntimeException {
    public ShaderCompileException(ShaderType type, String log) {
        super("Error compiling " + type.name() + " Shader|\n" + log);
    }
}

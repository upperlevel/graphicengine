package xyz.upperlevel.graphicengine.api.simple;

import xyz.upperlevel.graphicengine.api.opengl.shader.CompileStatus;
import xyz.upperlevel.graphicengine.api.opengl.shader.Program;
import xyz.upperlevel.graphicengine.api.opengl.shader.Shader;
import xyz.upperlevel.graphicengine.api.opengl.shader.ShaderType;

public class SimpleGraphicEngine {
    private final Program program = createProgram();

    protected Program createProgram() {
        return new Program()
                .attach(createFragmentShader())
                .attach(createVertexShader())
                .link();
    }

    protected Shader createFragmentShader() {
        Shader shader =  new Shader(ShaderType.FRAGMENT).linkResource("simple/FragmentShader.glsl");
        CompileStatus status = shader.compileSource();
        if(!status.isOk())
            throw new IllegalStateException("Cannot compile default fragment shader: " + status.getLog());
        return shader;
    }

    protected Shader createVertexShader() {
        Shader shader =  new Shader(ShaderType.FRAGMENT).linkResource("simple/VertexShader.glsl");
        CompileStatus status = shader.compileSource();
        if(!status.isOk())
            throw new IllegalStateException("Cannot compile default vertex shader: " + status.getLog());
        return shader;
    }
}

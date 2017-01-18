package xyz.upperlevel.graphicengine.api.simple;

import org.lwjgl.opengl.GL11;
import xyz.upperlevel.graphicengine.api.opengl.shader.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SimpleGraphicEngine {
    private final Program program = createProgram();


    private Set<Renderable> objects = new HashSet<>();

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
            throw new IllegalStateException("Cannot compile default vertex shader:\n" + status.getLog());
        return shader;
    }

    public void register(Renderable renderable) {
        objects.add(renderable);
    }

    public void register(Renderable... renderables) {
        objects.addAll(Arrays.asList(renderables));
    }

    public void register(List<Renderable> renderables) {
        objects.addAll(renderables);
    }

    public void remove(Renderable renderable) {
        objects.remove(renderable);
    }

    public void remove(List<Renderable> renderables) {
        objects.removeAll(renderables);
    }

    public void draw() {
        GL11.glClearColor(0f, 0f, 0f, 0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

        final Uniformer uniformer = program.bind();
        objects.forEach(r -> r.draw(uniformer));
    }
}

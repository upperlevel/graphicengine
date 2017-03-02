package xyz.upperlevel.ulge.simple;

import lombok.Getter;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import xyz.upperlevel.ulge.opengl.shader.*;

import java.util.*;

public class SimpleGraphicEngine {
    private final Program program = createProgram();

    private Set<Renderable> objects = new HashSet<>();

    private Queue<Renderable> toInit = new ArrayDeque<>();

    @Getter
    private float width, height;

    private Matrix4f projection = null;

    private Uniform iProj;

    private float[] buffer = new float[4*4];

    public SimpleGraphicEngine(float width, float height) {
        this.width = width;
        this.height = height;

        iProj = program.bind().get("projection");
    }

    protected Program createProgram() {
        return new Program()
                .attach(createFragmentShader())
                .attach(createVertexShader())
                .link();
    }

    protected Shader createFragmentShader() {
        Shader shader =  new Shader(ShaderType.FRAGMENT).linkResource("simple/fragment_shader.glsl");
        CompileStatus status = shader.compileSource();
        if(!status.isOk())
            throw new IllegalStateException("Cannot compile default fragment shader: " + status.getLog());
        return shader;
    }

    protected Shader createVertexShader() {
        Shader shader =  new Shader(ShaderType.VERTEX).linkResource("simple/vertex_shader.glsl");
        CompileStatus status = shader.compileSource();
        if(!status.isOk())
            throw new IllegalStateException("Cannot compile default vertex shader:\n" + status.getLog());
        return shader;
    }

    public void register(Renderable renderable) {
        objects.add(renderable);
        toInit.add(renderable);
    }

    public void register(Renderable... renderables) {
        register(Arrays.asList(renderables));
    }

    public void register(List<Renderable> renderables) {
        objects.addAll(renderables);
        toInit.addAll(renderables);
    }

    public void remove(Renderable renderable) {
        objects.remove(renderable);
    }

    public void remove(List<Renderable> renderables) {
        objects.removeAll(renderables);
    }

    public void setHeight(float height) {
        this.height = height;
        this.projection = null;
    }

    public void setWidth(float width) {
        this.width = width;
        this.projection = null;
    }

    public void setSize(float height, float width) {
        this.height = height;
        this.width = width;
        this.projection = null;
    }

    public void draw() {
        GL11.glClearColor(0f, 0f, 0f, 0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

        final Uniformer uniformer = program.bind();

        if(!toInit.isEmpty()) {
            System.out.println("INIT!");
            toInit.forEach(i -> i.init(uniformer));
            toInit.clear();
        }

        if(projection == null) {
            projection = new Matrix4f().ortho2D(0, width, 0, height);
            projection.get(buffer);
        }

        iProj.set(projection);

        objects.forEach(r -> r.draw(uniformer));
        program.unbind();
    }
}

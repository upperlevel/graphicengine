package xyz.upperlevel.ulge.gui;

import xyz.upperlevel.ulge.opengl.shader.*;
import xyz.upperlevel.ulge.opengl.texture.Texture2D;
import xyz.upperlevel.ulge.util.Color;

import java.util.LinkedList;
import java.util.Queue;

public class DefaultGuiRenderer extends GuiRenderer {
    public static final DefaultGuiRenderer $ = new DefaultGuiRenderer();

    private Uniform uBounds, uColor, uDepth;

    private Queue<Bounds> boundsStack = new LinkedList<>();

    private Bounds current = Bounds.FULL;

    public DefaultGuiRenderer() {
        super(createProgram());

        Uniformer u = getProgram().uniformer;
        uBounds = checkUniform(u.get("bounds"), "bounds");
        uColor  = checkUniform(u.get("col")   , "col");
        uDepth  = checkUniform(u.get("depth") , "depth");
    }

    public void setBounds(Bounds bounds) {
        uBounds.set(bounds.minX, bounds.minY, bounds.maxX - bounds.minX, bounds.maxY - bounds.minY);
    }

    @Override
    public void setColor(Color color) {
        uColor.set(color);
    }

    @Override
    public Bounds getAbsoluteBounds() {
        return current;
    }

    @Override
    public Bounds pushBounds(Bounds bounds) {
        if(boundsStack.isEmpty()) {
            current = bounds;
            boundsStack.add(bounds);
        } else {
            Bounds abs = bounds.shrink(current.shrink(bounds));
            boundsStack.add(abs);
            current = abs;
        }
        return current;
    }

    @Override
    public void popBounds() {
        if(boundsStack.poll() == null)
            throw new IllegalStateException("Trying to pop from an empty bounds stack!");
        else
            current = boundsStack.peek();
    }

    @Override
    public boolean isBoundsStackEmpty() {
        return boundsStack.isEmpty();
    }

    @Override
    public void setTexture(Texture2D texture) {
        texture.bind();
    }

    @Override
    public void setDepth(float depth) {
        uDepth.set(depth);
    }

    private static Program createProgram() {
        Program program = new Program();
        program.attach(Shader.create(ShaderType.VERTEX  , "gui/vertex_shader.glsl"  , DefaultGuiRenderer.class));
        program.attach(Shader.create(ShaderType.FRAGMENT, "gui/fragment_shader.glsl", DefaultGuiRenderer.class));
        program.link();
        return program;
    }

    private static Uniform checkUniform(Uniform u, String name) {
        if (u == null)
            throw new NullPointerException("Cannot find uniform \"" + name + "\" in the program!");
        return u;
    }

    public static DefaultGuiRenderer $() {
        return $;
    }
}

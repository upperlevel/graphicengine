package xyz.upperlevel.ulge.gui;

import lombok.Getter;
import lombok.NonNull;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import xyz.upperlevel.ulge.opengl.DataType;
import xyz.upperlevel.ulge.opengl.buffer.*;
import xyz.upperlevel.ulge.opengl.shader.*;
import xyz.upperlevel.ulge.opengl.texture.Texture2d;
import xyz.upperlevel.ulge.util.Color;

import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawElements;

public class GuiRenderer {

    public static final Vao guiMesh;

    static {
        guiMesh = new Vao();
        guiMesh.bind();
        {

            Ebo ebo = new Ebo();
            ebo.loadData(new int[]{
                    0, 1, 2,
                    1, 2, 3
            }, EboDataUsage.STATIC_DRAW);

            Vbo vbo = new Vbo();
            vbo.bind();
            vbo.loadData(new float[]{
                    //Coords (x, y, z)
                    0.0f, 0.0f,
                    0.0f, 1.0f,
                    1.0f, 0.0f,
                    1.0f, 1.0f,
            }, VboDataUsage.STATIC_DRAW);
            new VertexLinker(DataType.FLOAT)
                    .attrib(0, 2)
                    .setup();
            vbo.unbind();
        }
        guiMesh.unbind();
    }

    @Getter
    private Program program;

    private Uniform transformationUni, colorUni, depthUni;

    public GuiRenderer() {
        this(createProgram());
    }

    public GuiRenderer(@NonNull Program program) {
        this.program = program;

        Uniformer uniformer = program.uniformer;

        transformationUni = uniformer.get("transformation");
        colorUni          = uniformer.get("color");
        depthUni          = uniformer.get("depth");
    }

    private static Program createProgram() {
        Program program = new Program();
        program.attach(Shader.create(ShaderType.VERTEX, "gui/basicShader.vs", GuiRenderer.class));
        program.attach(Shader.create(ShaderType.FRAGMENT, "gui/basicShader.fs", GuiRenderer.class));
        return program;
    }

    public void setTransformation(@NonNull Matrix4f transformation) {
        if (transformationUni != null)
            transformationUni.set(transformation);
    }

    public void setColor(@NonNull Color color) {
        if (colorUni != null)
            colorUni.set(color);
    }

    public void setDepth(float depth) {
        if (depthUni != null)
            depthUni.set(depth);
    }

    public void setTexture(@NonNull Texture2d texture) {
        texture.bind();
    }

    public void render() {
        guiMesh.bind();
        glDrawElements(GL11.GL_TRIANGLES, 6, GL_UNSIGNED_INT, 0);
        guiMesh.unbind();
    }
}

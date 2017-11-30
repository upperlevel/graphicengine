package xyz.upperlevel.ulge.gui;

import lombok.Getter;
import lombok.NonNull;
import org.lwjgl.opengl.GL11;
import xyz.upperlevel.ulge.opengl.DataType;
import xyz.upperlevel.ulge.opengl.buffer.*;
import xyz.upperlevel.ulge.opengl.shader.*;
import xyz.upperlevel.ulge.opengl.texture.Texture2d;
import xyz.upperlevel.ulge.util.Color;

import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawElements;

/**
 * Class used to have some (really basic) Gui rendering
 */
public class GuiRenderer {
    private static GuiRenderer instance = new GuiRenderer();
    private static final Vao MESH;

    static {
        MESH = new Vao();
        MESH.bind();

        // Setup EBO
        Ebo ebo = new Ebo();
        ebo.loadData(new int[]{
                0, 1, 2,
                1, 3, 2
        }, EboDataUsage.STATIC_DRAW);

        // Setup Vbo
        Vbo vbo = new Vbo();
        vbo.loadData(new float[]{
                // Coords (x, y)
                0.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 0.0f,
                1.0f, 1.0f,
        }, VboDataUsage.STATIC_DRAW);

        // Link Vertexes
        new VertexLinker(DataType.FLOAT)
                .attrib(0, 2)
                .setup();
    }

    @Getter
    private Program program;

    private Uniform boundsUniform, colorUniform, depthUniform;

    public GuiRenderer() {
        this(createProgram());
    }

    public GuiRenderer(@NonNull Program program) {
        this.program = program;

        Uniformer uniformer = program.uniformer;

        boundsUniform = uniformer.get("bounds");
        if (boundsUniform == null) {
            throw new NullPointerException("Cannot find 'bounds' uniform location");
        }

        colorUniform = uniformer.get("color");
        if (colorUniform == null) {
            throw new NullPointerException("Cannot find 'color' uniform location");
        }

        depthUniform = uniformer.get("depth");
        if (depthUniform == null) {
            throw new NullPointerException("Cannot find 'depth' uniform location");
        }
    }

    /**
     * Changes the {@link Color} that will be used in the next {@link #render(GuiBounds)} call
     * @param color the color that will be used
     */
    public void setColor(@NonNull Color color) {
        program.bind();
        colorUniform.set(color);
    }

    /**
     * Changes the depth that the next {@link #render(GuiBounds)} will use (default: 0)
     * @param depth the depth that will be used
     */
    public void setDepth(float depth) {
        program.bind();
        depthUniform.set(depth);
    }

    /**
     * Changes the {@link Texture2d} that will be used in the next {@link #render(GuiBounds)} call
     * @param texture the texture that will be used
     */
    public void setTexture(@NonNull Texture2d texture) {
        program.bind();
        texture.bind();
    }

    /**
     * Renders the Gui in the bounds using the parameters set using {@link #setColor(Color)}, {@link #setDepth(float)} and {@link #setTexture(Texture2d)}
     * <br>Warning: the uniforms should be always set or another call might overwrite them
     * @param bounds the bounds that will be used to render
     */
    public void render(GuiBounds bounds) {
        program.bind();
        MESH.bind();
        // Two operations are being made:
        // - Converting the min-max system to the xywh system
        // - Inverting the y axis
        boundsUniform.set(
                (float) bounds.minX,
                1.0f - (float) bounds.minY,         // Invert y
                (float) (bounds.maxX - bounds.minX),    // Convert maxX to width
                (float) (bounds.minY - bounds.maxY)     // Convert maxY to height & Invert y: 1 - (max - min) = (min - max)
        );
        glDrawElements(GL11.GL_TRIANGLES, 6, GL_UNSIGNED_INT, 0);
    }

    /**
     * Returns the GuiRenderer instance used by all the guis
     * @return the common instance
     */
    public static GuiRenderer get() {
        return instance;
    }

    /**
     * Overwrites the GuiRenderer instance used by all the guis
     * @param renderer the new common GuiRenderer instance
     */
    public static void set(GuiRenderer renderer) {
        instance = renderer;
    }

    private static Program createProgram() {
        Program program = new Program();
        program.attach(Shader.create(ShaderType.VERTEX, "gui/basicShader.vs", GuiRenderer.class));
        program.attach(Shader.create(ShaderType.FRAGMENT, "gui/basicShader.fs", GuiRenderer.class));
        program.link();
        return program;
    }
}

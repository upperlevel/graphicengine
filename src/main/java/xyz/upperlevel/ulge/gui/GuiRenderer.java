package xyz.upperlevel.ulge.gui;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.joml.Vector2f;
import xyz.upperlevel.ulge.opengl.DataType;
import xyz.upperlevel.ulge.opengl.buffer.*;
import xyz.upperlevel.ulge.opengl.shader.Program;
import xyz.upperlevel.ulge.opengl.texture.Texture2D;
import xyz.upperlevel.ulge.util.Color;

import java.util.Objects;

import static org.lwjgl.opengl.GL11.*;

public abstract class GuiRenderer {

    public static final Vao fillVao;

    static {
        fillVao = new Vao();
        fillVao.bind();
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
        fillVao.unbind();
    }

    @Getter
    @Setter
    @NonNull
    private Program program;

    public GuiRenderer(Program program) {
        Objects.requireNonNull(program, "program");
        this.program = program;
    }
    public abstract void setColor(Color color);


    public abstract Bounds getAbsoluteBounds();

    public void resetBounds() {
        setBounds(getAbsoluteBounds());
    }

    public abstract void setBounds(Bounds bounds);

    public void pushAndSetBounds(Bounds bounds) {
        setBounds(pushBounds(bounds));
    }

    public abstract Bounds pushBounds(Bounds bounds);

    public abstract void popBounds();

    public abstract boolean isBoundsStackEmpty();

    /**
     * Equal to setBounds(getAbsoluteBounds());
     * Sends the bounds to their uniform
     */
    public abstract void flushBounds();


    public abstract void setTexture(Texture2D texture);

    public abstract void setDepth(float depth);

    public abstract float getDepth();

    public void setSize(Vector2f size) {
        setSize(size.x, size.y);
    }

    public abstract void setSize(float w, float h);

    public void fill(Vector2f size) {
        fill(size.x, size.y);
    }

    public void fill(float x, float y) {
        setSize(x, y);
        fill();
    }

    public void fill() {
        flushBounds();
        fillVao.bind();
        glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_INT, 0);
        fillVao.unbind();
    }
}

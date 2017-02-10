package xyz.upperlevel.ulge.gui;

import xyz.upperlevel.ulge.opengl.DataType;
import xyz.upperlevel.ulge.opengl.buffer.*;
import xyz.upperlevel.ulge.opengl.shader.Program;
import xyz.upperlevel.ulge.opengl.texture.Texture2D;
import xyz.upperlevel.ulge.util.Color;

import static org.lwjgl.opengl.GL11.*;

public abstract class GuiRenderer {

    public static final VAO fillVao;

    static {
        fillVao = new VAO();
        fillVao.bind();
        {

            EBO ebo = new EBO();
            ebo.loadData(new int[]{
                    0, 1, 2,
                    1, 2, 3
            }, EBODataUsage.STATIC_DRAW);

            VBO vbo = new VBO();
            vbo.bind();
            vbo.loadData(new float[]{
                    //Coords (x, y, z)
                    0.0f,  0.0f,
                    0.0f,  1.0f,
                    1.0f,  0.0f,
                    1.0f,  1.0f,
            }, VBODataUsage.STATIC_DRAW);
            new VertexLinker(DataType.FLOAT)
                    .attrib(0, 2)
                    .setup();
            vbo.unbind();
        }
        fillVao.unbind();
    }

    public final Program program;

    public GuiRenderer(Program program) {
        this.program = program;
    }

    public abstract void setBounds(Bounds bounds);

    public abstract void setColor(Color color);

    public abstract void setTexture(Texture2D tex);

    public abstract void setDepth(float depth);

    public void fill() {
        fillVao.bind();
        glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_INT, 0);
        fillVao.unbind();
    }
}

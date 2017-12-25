package xyz.upperlevel.ulge.simple.shapes;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import xyz.upperlevel.ulge.opengl.DataType;
import xyz.upperlevel.ulge.opengl.buffer.*;
import xyz.upperlevel.ulge.opengl.shader.Program;
import xyz.upperlevel.ulge.opengl.shader.Uniform;
import xyz.upperlevel.ulge.opengl.texture.Texture2d;
import xyz.upperlevel.ulge.simple.SimpleRenderable;
import xyz.upperlevel.ulge.util.Color;

import static org.lwjgl.opengl.GL11.*;

public class Square extends SimpleRenderable {

    public static final Vao vao;

    public static final float vertices[] = {
            //Coords (x, y, z)     //TexCoords
            0.0f, 0.0f, 0.0f, 1.0f,
            0.0f, 1.0f, 0.0f, 0.0f,
            1.0f, 0.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 0.0f,
    };

    public static final int indices[] = {
            0, 1, 2,
            1, 2, 3,
    };

    static {
        vao = new Vao();
        vao.bind();
        {

            Ebo ebo = new Ebo();
            ebo.loadData(indices, EboDataUsage.STATIC_DRAW);

            Vbo vbo = new Vbo();
            vbo.bind();
            vbo.loadData(vertices, VboDataUsage.STATIC_DRAW);

            VertexLinker v = new VertexLinker();
            v.attrib(0, 2, DataType.FLOAT, false, 0);
            v.attrib(1, 2, DataType.FLOAT, false, 2 * DataType.FLOAT.getByteCount());
            v.setup();

            vbo.unbind();
        }
        vao.unbind();
    }

    public Vector2f pos;
    public Vector2f size;

    public float rotation = 0f;

    protected Uniform uTransform;

    public Square(Vector2f pos, Vector2f size, Color color) {
        super(color);
        this.pos = pos;
        this.size = size;
    }

    public Square(Vector2f pos, Vector2f size, Texture2d texture) {
        super(texture);
        this.pos = pos;
        this.size = size;
    }

    public Matrix4f getTransform() {
        Matrix4f matrix = new Matrix4f();
        matrix.translate(pos.x, pos.y, 0);
        matrix.scale(size.x, size.y, 1);

        if (rotation != 0)
            matrix.rotate(rotation, 0f, 0f, 1f);
        return matrix;
    }

    @Override
    public void init(Program program) {
        super.init(program);
        uTransform = program.getUniform("transform");
    }


    @Override
    public void draw(Program program) {
        super.draw(program);

        uTransform.set(getTransform());

        vao.bind();
        glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_INT, 0);
        vao.unbind();
    }
}

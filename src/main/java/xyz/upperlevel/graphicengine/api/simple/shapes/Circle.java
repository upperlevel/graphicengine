package xyz.upperlevel.graphicengine.api.simple.shapes;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import xyz.upperlevel.graphicengine.api.opengl.buffer.BufferUtil;
import xyz.upperlevel.graphicengine.api.opengl.buffer.VAO;
import xyz.upperlevel.graphicengine.api.opengl.buffer.VBO;
import xyz.upperlevel.graphicengine.api.opengl.buffer.VBOUsage;
import xyz.upperlevel.graphicengine.api.opengl.model.VertexDefiner;
import xyz.upperlevel.graphicengine.api.opengl.shader.Uniformer;
import xyz.upperlevel.graphicengine.api.opengl.texture.Texture;
import xyz.upperlevel.graphicengine.api.simple.SimpleColor;
import xyz.upperlevel.graphicengine.api.simple.SimpleRenderable;

import static org.lwjgl.opengl.GL11.GL_TRIANGLE_FAN;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static xyz.upperlevel.graphicengine.api.opengl.NumberType.FLOAT;

public class Circle extends SimpleRenderable{
    public VAO vao;


    public Vector2f pos;
    public Vector2f size;

    public final int segments;

    public Circle(Vector2f pos, Vector2f size, SimpleColor color, int segments) {
        super(color);
        this.segments = segments;
        initVerts(segments);
        this.pos = pos;
        this.size = size;
    }

    public Circle(Vector2f pos, Vector2f size, Texture texture, int segments) {
        super(texture);
        this.segments = segments;
        initVerts(segments);
        this.pos = pos;
        this.size = size;
    }

    private void initVerts(int segments) {
        vao = new VAO();
        vao.bind();
        {
            VBO vbo = new VBO();
            vbo.bind();
            vbo.loadData(genVertices(segments), VBOUsage.STATIC_DRAW);
            VertexDefiner.builder(FLOAT)
                    .attrib(0, 2)
                    .attrib(1, 2)

                    .build().setup();
            vbo.unbind();
        }
        vao.unbind();
    }

    public Matrix4f getTransform() {
        Matrix4f matrix = new Matrix4f();
        matrix.translate(pos.x, pos.y, 0);
        matrix.scale(size.x, size.y, 1);
        return matrix;
    }


    @Override
    public void draw(Uniformer uniformer) {
        super.draw(uniformer);

        if(!uniformer.setUniformMatrix("transform", getTransform().get(BufferUtil.createBuffer(new float[4*4]))))
            throw new IllegalStateException();

        vao.bind();
        glDrawArrays(GL_TRIANGLE_FAN, 0, segments + 2);
        vao.unbind();
    }

    public static float[] genVertices(int segments) {
        double theta = 2 * Math.PI / (double) segments;
        double c = Math.cos(theta);//precalculate the sine and cosine
        double s = Math.sin(theta);
        double t;

        final float r = 0.5f;

        double x = r;//we start at angle = 0
        double y = 0;

        float[] vertices = new float[(2 + segments) * 4];

        //glBegin(GL_TRIANGLE_FAN);
        vertices[0] = r;
        vertices[1] = r;
        vertices[2] = r;
        vertices[3] = r;

        for(int i = 1; i <= segments; i++) {
            final int vi = i*4;
            vertices[vi    ] = (float) x + r;
            vertices[vi + 1] = (float) y + r;

            vertices[vi + 2] = ((float) x + r);
            vertices[vi + 3] = 1.0f - ((float) y + r);
            //glVertex2f(x + cx, y + cy);//output vertex

            //apply the rotation matrix
            t = x;
            x = c * x - s * y;
            y = s * t + c * y;
        }
        final int vi = (segments + 1)*4;
        vertices[vi  ] = 2*r;
        vertices[vi+1] = r;
        vertices[vi+2] = 2*r;
        vertices[vi+3] = 1.0f - r;

        //glEnd();
        return vertices;
    }
}

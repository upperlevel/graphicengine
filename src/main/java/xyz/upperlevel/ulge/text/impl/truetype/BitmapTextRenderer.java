package xyz.upperlevel.ulge.text.impl.truetype;

import lombok.Getter;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import xyz.upperlevel.ulge.opengl.DataType;
import xyz.upperlevel.ulge.opengl.buffer.*;
import xyz.upperlevel.ulge.opengl.shader.*;
import xyz.upperlevel.ulge.opengl.texture.Texture2D;
import xyz.upperlevel.ulge.opengl.texture.loader.ImageContent;
import xyz.upperlevel.ulge.text.SuperText;
import xyz.upperlevel.ulge.text.TextPiece;
import xyz.upperlevel.ulge.text.TextRenderer;

public class BitmapTextRenderer extends TextRenderer {

    private static final float[] VERTICES = {
            0.0f, 0.0f,
            0.0f, 1.0f,
            1.0f, 0.0f,
            1.0f, 1.0f
    };

    public static final int INDICES[] = {
            0, 1, 2,
            1, 2, 3,
    };

    public static final VAO vao;
    public static final EBO ebo;

    public static Program standardProgram;



    protected final Texture2D texture;
    protected final Program program;

    private final int width, height;

    @Getter
    protected CharDataManager chars;

    protected Uniform textureLoc, colorLoc, projectionLoc;
    protected Uniform charXLoc, charYLoc, charWidthLoc, charHeightLoc;

    static {
        vao = new VAO();
        vao.bind();
        {

            ebo = new EBO();
            ebo.loadData(INDICES, EBODataUsage.STATIC_DRAW);

            VBO vbo = new VBO();
            vbo.bind();
            vbo.loadData(VERTICES, VBODataUsage.STATIC_DRAW);
            new VertexLinker(DataType.FLOAT)
                    .attrib(0, 2)
                    .setup();
            vbo.unbind();
        }
        vao.unbind();

        Shader vertex, fragment;

        {
            vertex = new Shader(ShaderType.VERTEX)
                    .linkResource("text/bitmap/vertex_shader.glsl", BitmapTextRenderer.class);

            CompileStatus status = vertex.compileSource();

            if (!status.isOk())
                throw new IllegalStateException("Error compiling vertex shader:\n" + status.getLog());
        }

        {
            fragment = new Shader(ShaderType.FRAGMENT)
                    .linkResource("text/bitmap/fragment_shader.glsl", BitmapTextRenderer.class);

            CompileStatus status = fragment.compileSource();

            if (!status.isOk())
                throw new IllegalStateException("Error compiling fragment shader:\n" + status.getLog());
        }

        standardProgram = new Program()
                .attach(vertex)
                .attach(fragment)
                .link();
    }

    public BitmapTextRenderer(Texture2D texture, CharDataManager chars) {
        program = getProgram();
        this.texture = texture;
        this.chars = chars;

        ImageContent content = texture.getImageContent();

        width = content.getWidth();
        height = content.getHeight();

        chars.datas().forEachRemaining(c -> c.setup(width, height));
    }

    public Program getProgram() {
        return standardProgram;
    }

    @Override
    public void init() {
        Uniformer uniformer = program.bind();

        textureLoc = uniformer.get("tex");
        colorLoc = uniformer.get("col");
        projectionLoc = uniformer.get("projection");

        charXLoc = uniformer.get("ch.x");
        charYLoc = uniformer.get("ch.y");
        charWidthLoc = uniformer.get("ch.w");
        charHeightLoc = uniformer.get("ch.h");

        program.unbind();
    }

    @Override
    public Vector2f getSize(CharSequence str, float scale) {
        Vector2f size = new Vector2f(0.0f, 0.0f);
        float lineWidth = 0.0f;
        final int length = str.length();
        for(int i = 0; i < length; i++) {
            char c = str.charAt(i);
            if(c == '\n') {
                if(size.x < lineWidth)
                    size.x = lineWidth;

                size.y += scale;
                lineWidth = 0;
            } else {
                CharData ch = chars.get(c);
                if(ch == null)
                    continue;
                lineWidth += ch.ratio * scale;
            }
        }
        return size;
    }

    @Override
    public Vector2f getSize(SuperText text, float scale) {
        Vector2f size = new Vector2f(0.0f, scale);
        float lineWidth = 0.0f;
        for(TextPiece piece : text.asList()) {
            final int length = piece.text.length();
            for (int i = 0; i < length; i++) {
                char c = piece.text.charAt(i);
                if (c == '\n') {
                    if (size.x < lineWidth)
                        size.x = lineWidth;
                    size.y += scale;
                    lineWidth = 0;
                } else {
                    CharData ch = chars.get(c);
                    if (ch == null)
                        continue;
                    lineWidth += ch.ratio * scale;
                }
            }
        }

        return size;
    }

    public void drawText2D0(SuperText text, Vector2f pos, float distance, float scale) {
        program.bind();
        texture.bind();

        final float initX = pos.x;

        for(TextPiece piece : text.asList()) {
            if(piece.bold) throw new NotImplementedException();
            if(piece.italic) throw new NotImplementedException();
            if(piece.strikeThrough) throw new NotImplementedException();
            colorLoc.set(piece.color);


            final int length = piece.text.length();
            for(int i = 0; i < length; i++) {
                char c = piece.text.charAt(i);

                if(c == '\n') {
                    pos.y -= scale;
                    pos.x = initX;
                    continue;
                }


                CharData data = get(c);

                if(data == null)
                    continue;

                final float widthScale = scale * data.ratio;

                projectionLoc.set(new Matrix4f().translate(pos.x, pos.y, distance).scale(widthScale, scale, 1.0f));

                pos.x += widthScale;

                uniform2d(data);

                ebo.draw(DrawMode.TRIANGLES, DataType.UNSIGNED_INT, 0, 6);
            }
        }

        program.unbind();
    }

    protected void uniform2d(CharData data) {
        charXLoc.set(data.rx);
        charYLoc.set(data.ry);
        charWidthLoc.set(data.rw);
        charHeightLoc.set(data.rh);
    }

    public CharData get(char c) {
        return chars.get(c);
    }

    @Override
    public void drawText(SuperText text, Matrix4f position) {
        throw new NotImplementedException();
    }

    @Override
    public void destroy() {
        program.destroy();
    }
}

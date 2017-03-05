package xyz.upperlevel.ulge.text.impl.bitmap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import xyz.upperlevel.ulge.opengl.DataType;
import xyz.upperlevel.ulge.opengl.buffer.*;
import xyz.upperlevel.ulge.opengl.shader.*;
import xyz.upperlevel.ulge.opengl.texture.Texture2D;
import xyz.upperlevel.ulge.opengl.texture.loader.ImageContent;
import xyz.upperlevel.ulge.text.CompiledText;
import xyz.upperlevel.ulge.text.SuperText;
import xyz.upperlevel.ulge.text.TextPiece;
import xyz.upperlevel.ulge.text.TextRenderer;
import xyz.upperlevel.ulge.util.Color;

import java.util.ArrayList;
import java.util.List;

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

    public static final Vao vao;
    public static final Ebo ebo;

    public static Program standardProgram;


    @Getter
    protected final Texture2D texture;
    protected final Program program;

    private final int width, height;

    @Getter
    protected CharDataManager chars;

    protected Uniform textureLoc, colorLoc, projectionLoc;
    protected Uniform charXLoc, charYLoc, charWidthLoc, charHeightLoc;

    static {
        vao = new Vao();
        vao.bind();
        {

            ebo = new Ebo();
            ebo.loadData(INDICES, EboDataUsage.STATIC_DRAW);

            Vbo vbo = new Vbo();
            vbo.bind();
            vbo.loadData(VERTICES, VboDataUsage.STATIC_DRAW);
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

        ImageContent content = texture.getImage();

        width = content.getWidth();
        height = content.getHeight();

        chars.datas().forEachRemaining(c -> c.setup(width, height));
    }

    public Program getProgram() {
        return standardProgram;
    }

    @Override
    public void init() {
        if(textureLoc == null) {//Just a test to remove redundancy
            Uniformer uniformer = program.push();

            textureLoc = uniformer.get("tex");
            colorLoc = uniformer.get("col");
            projectionLoc = uniformer.get("projection");

            charXLoc = uniformer.get("ch.x");
            charYLoc = uniformer.get("ch.y");
            charWidthLoc = uniformer.get("ch.w");
            charHeightLoc = uniformer.get("ch.h");

            program.pop();
        }
    }

    @Override
    public Vector2f getSize(CharSequence str, float scale, float maxW) {
        Vector2f size = new Vector2f(0.0f, 0.0f);
        float lineWidth = 0.0f;
        final int length = str.length();
        maxW *= 2f;
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
                final float w = ch.ratio * scale;

                if(lineWidth +  w > maxW) {
                    if(size.x < lineWidth)
                        size.x = lineWidth;

                    size.y += scale;
                    lineWidth = 0;
                }
                lineWidth += w;
            }
        }
        if(size.x < lineWidth)
            size.x = lineWidth;
        return size;
    }

    @Override
    public Vector2f getSize(SuperText text, float scale, float maxW) {
        Vector2f size = new Vector2f(0.0f, scale);
        float lineWidth = 0.0f;
        maxW *= 2f;
        for(TextPiece piece : text) {
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
                    final float w = ch.ratio * scale;

                    if(lineWidth +  w > maxW) {
                        if(size.x < lineWidth)
                            size.x = lineWidth;

                        size.y += scale;
                        lineWidth = 0;
                    }

                     lineWidth += w;
                }
            }
        }
        if(size.x < lineWidth)
            size.x = lineWidth;

        return size;
    }

    @Override
    public void drawText2D0(SuperText text, Vector2f pos, float distance, float scale, float maxWidth) {
        program.push();
        texture.bind();

        maxWidth *= 2f;

        final float initX = pos.x;
        float dx = 0f;

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
                    dx = 0f;
                    continue;
                }


                CharData data = get(c);

                if(data == null)
                    continue;

                final float w = scale * data.ratio;

                if(dx + w > maxWidth) {
                    pos.y -= scale;
                    pos.x = initX;
                    dx = 0f;
                }

                projectionLoc.set(new Matrix4f().translate(pos.x, pos.y, distance).scale(w, scale, 1.0f));

                pos.x += w;
                dx += w;

                uniform2d(data);

                ebo.draw(DrawMode.TRIANGLES, DataType.UNSIGNED_INT, 0, 6);
            }
        }

        program.pop();
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
    public CompiledText<BitmapTextRenderer>  compile(SuperText text, float scale, float maxWidth) {
        List<BitmapCompiledString[]> lines = new ArrayList<>();
        List<BitmapCompiledString> line = new ArrayList<>();

        float x = 0.0f, y = 0.0f;

        maxWidth *= 2f; //opengl's windows goes from -1 to 1 (so it has 2 of width) but our scale only has 1 of width

        float width = 0f;

        for (TextPiece piece : text) {

            String t = piece.text;
            final int length = t.length();
            List<CharData> chars = new ArrayList<>(length);

            for (int i = 0; i < length; i++) {
                char c = t.charAt(i);
                CharData data = get(c);
                float w = scale * data.ratio;
                if (c == '\n' || (width + w) > maxWidth) {
                    if(!chars.isEmpty()) {
                        line.add(
                                new BitmapCompiledString(
                                        chars.toArray(new CharData[0]),
                                        piece.color,
                                        piece.italic,
                                        piece.bold,
                                        piece.strikeThrough
                                )
                        );
                        chars.clear();
                    }
                    if(!line.isEmpty()) {
                        lines.add(line.toArray(new BitmapCompiledString[0]));
                        line.clear();
                        if(width > x)
                            x = width;
                        y += scale;
                        width = 0;
                    }
                }
                if(c != '\n') {
                    chars.add(data);
                    width += w;
                }
            }

            if(!chars.isEmpty()) {
                line.add(
                        new BitmapCompiledString(
                                chars.toArray(new CharData[0]),
                                piece.color,
                                piece.italic,
                                piece.bold,
                                piece.strikeThrough
                        )
                );
                chars.clear();
            }
        }

        if(!line.isEmpty()) {
            lines.add(line.toArray(new BitmapCompiledString[0]));
            y += scale;
        }

        return new BitmapCompiledText(
                text,
                this,
                lines.toArray(new BitmapCompiledString[0][]),
                new Vector2f(x, y),
                scale
        );
    }

    @Override
    public void destroy() {
        program.destroy();
    }

    @ToString
    public class BitmapCompiledText extends CompiledText<BitmapTextRenderer> {

        private final BitmapCompiledString[][] lines;
        public float scale;

        public BitmapCompiledText(SuperText text, BitmapTextRenderer renderer, BitmapCompiledString[][] compiled, Vector2f size, float scale) {
            super(text, renderer, size);
            this.lines = compiled;
            this.scale = scale;
        }

        @Override
        public void render(Vector2f pos, TextOrigin origin, float distance) {
            program.push();
            texture.bind();

            origin.translate(pos, scale, size);

            Color lastColor = null;

            final float initX = pos.x;

            for (BitmapCompiledString[] line : lines) {
                for (BitmapCompiledString piece : line) {
                    if(lastColor != piece.color) {
                        lastColor = piece.color;
                        colorLoc.set(lastColor);
                    }


                    for (CharData data : piece.chars) {
                        if (data == null)
                            continue;

                        final float widthScale = scale * data.ratio;

                        projectionLoc.set(new Matrix4f().translate(pos.x, pos.y, distance).scale(widthScale, scale, 1.0f));

                        pos.x += widthScale;

                        uniform2d(data);

                        ebo.draw(DrawMode.TRIANGLES, DataType.UNSIGNED_INT, 0, 6);
                    }
                }
                pos.y -= scale;
                pos.x = initX;
            }

            program.pop();
        }
    }

    @AllArgsConstructor
    @ToString
    public static class BitmapCompiledString {
        public final CharData[] chars;
        public final Color color;
        public final boolean italic, bold, strikeThrough;
    }
}

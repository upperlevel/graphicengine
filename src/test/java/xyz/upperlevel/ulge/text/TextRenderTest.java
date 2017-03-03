package xyz.upperlevel.ulge.text;

import org.joml.Vector2f;
import xyz.upperlevel.ulge.simple.SimpleGame;
import xyz.upperlevel.ulge.text.TextRenderer.TextOrigin;
import xyz.upperlevel.ulge.text.impl.bitmap.BitmapTextRenderer;
import xyz.upperlevel.ulge.util.Color;
import xyz.upperlevel.ulge.util.FontUtil;
import xyz.upperlevel.ulge.window.event.action.Action;
import xyz.upperlevel.ulge.window.event.key.Key;

import static java.awt.Font.SERIF;
import static java.awt.Font.getFont;

public class TextRenderTest extends SimpleGame {
    public static final float scale = 0.2f;

    private BitmapTextRenderer renderer;

    private CompiledText compiled;

    private TextOrigin origin = TextOrigin.CENTER;

    private long sum = 0, samples = 0;

    //private SuperText text = SuperText.of(TextPiece.of("Test", Color.AQUA));

    private SuperText text = SuperText.of(
            TextPiece.of("How", Color.AQUA),
            TextPiece.of(" 'r", Color.AQUA),
            TextPiece.of(" u", Color.DARK_AQUA),
            TextPiece.of(" doing", Color.YELLOW),
            TextPiece.of("?", Color.DARK_PURPLE),

            TextPiece.of("\n"),

            TextPiece.of("I'm fine thks", Color.YELLOW),

            TextPiece.of("\n"),

            TextPiece.of("Third line!", Color.RED)
    );

    @Override
    public void config() {
        vsync(false);
    }



    @Override
    public void init() {
        long init = System.nanoTime();
        renderer = FontUtil.textRenderer(getFont(SERIF));
        renderer.init();

        compiled = renderer.compile(text, scale, 0.4f);

        long end = System.nanoTime();

        System.out.println("size: " + compiled.size + ", time:" + (end - init));

        System.out.println(compiled);
    }

    @Override
    public void keyChange(Key key, Action action) {
        super.keyChange(key, action);

        switch (key) {
            case KEY_1:
                origin = TextOrigin.UPPER_LEFT;
                break;
            case KEY_2:
                origin = TextOrigin.UPPER_CENTER;
                break;
            case KEY_3:
                origin = TextOrigin.UPPER_RIGHT;
                break;
            case KEY_4:
                origin = TextOrigin.CENTER_LEFT;
                break;
            case KEY_5:
                origin = TextOrigin.CENTER;
                break;
            case KEY_6:
                origin = TextOrigin.CENTER_RIGHT;
                break;
            case KEY_7:
                origin = TextOrigin.BOTTOM_LEFT;
                break;
            case KEY_8:
                origin = TextOrigin.BOTTOM_CENTER;
                break;
            case KEY_9:
                origin = TextOrigin.BOTTOM_RIGHT;
                break;
        }
    }

    @Override
    public void update(double delta) {
    }

    @Override
    public void postDraw() {
        Vector2f vec = new Vector2f(cursorPos()).mul(1, -1).mul(2).sub(1f, -1f);
        long init = System.nanoTime();
        //renderer.drawText2D(compiled.text, vec, origin, 0f, scale);
        //origin.translate(vec, scale, compiled.size);
        compiled.render(vec, origin, 0f);
        long end = System.nanoTime();
        sum += (end - init);
        samples++;
    }

    @Override
    public void showFPS(float value) {
        super.showFPS(value);
        System.out.println("Draw time:" + (sum / ((double)samples)));
        sum = 0;
        samples = 0;
    }

    public static void main(String... args) {
        new TextRenderTest().launch();
        System.out.println("EXIT!");
    }
}

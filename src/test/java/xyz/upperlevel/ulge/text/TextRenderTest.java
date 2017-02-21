package xyz.upperlevel.ulge.text;

import org.joml.Vector2f;
import xyz.upperlevel.ulge.simple.SimpleGame;
import xyz.upperlevel.ulge.text.TextRenderer.TextOrigin;
import xyz.upperlevel.ulge.text.impl.truetype.BitmapTextRenderer;
import xyz.upperlevel.ulge.util.Colors;
import xyz.upperlevel.ulge.util.FontUtil;
import xyz.upperlevel.ulge.window.event.action.Action;
import xyz.upperlevel.ulge.window.event.key.Key;

import static java.awt.Font.SERIF;
import static java.awt.Font.getFont;

public class TextRenderTest extends SimpleGame {
    private BitmapTextRenderer renderer;

    private TextOrigin origin = TextOrigin.CENTER;

    private SuperText text = new ArraySuperText(
            new TextPiece("How").color(Colors.AQUA),
            new TextPiece(" 'r").color(Colors.BLUE),
            new TextPiece(" u").color(Colors.DARK_AQUA),
            new TextPiece(" doing").color(Colors.YELLOW),
            new TextPiece("?").color(Colors.DARK_PURPLE),

            new TextPiece("\n"),

            new TextPiece("I'm fine thks").color(Colors.YELLOW),

            new TextPiece("\n"),

            new TextPiece("Third line!").color(Colors.RED)
    );

    @Override
    public void config() {
        vsync(false);
    }



    @Override
    public void init() {
        renderer = FontUtil.textRenderer(getFont(SERIF));
        renderer.init();

        System.out.println("size: " + renderer.getSize(text, 0.2f));
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
        renderer.drawText2D(text, vec, origin, 0f, 0.2f);
    }

    public static void main(String... args) {
        new TextRenderTest().launch();
        System.out.println("EXIT!");
        System.exit(0);
    }
}

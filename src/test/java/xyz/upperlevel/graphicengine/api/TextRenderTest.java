package xyz.upperlevel.graphicengine.api;

import org.joml.Vector2f;
import xyz.upperlevel.ulge.simple.SimpleGame;
import xyz.upperlevel.ulge.text.ArraySuperText;
import xyz.upperlevel.ulge.text.SuperText;
import xyz.upperlevel.ulge.text.TextPiece;
import xyz.upperlevel.ulge.text.impl.truetype.BitmapTextRenderer;
import xyz.upperlevel.ulge.util.Colors;
import xyz.upperlevel.ulge.util.FontUtil;

import static java.awt.Font.SERIF;
import static java.awt.Font.getFont;

public class TextRenderTest extends SimpleGame {
    private BitmapTextRenderer renderer;

    private SuperText text = new ArraySuperText(
            new TextPiece("How").color(Colors.AQUA),
            new TextPiece(" 'r").color(Colors.BLUE),
            new TextPiece(" u").color(Colors.DARK_AQUA),
            new TextPiece(" doing").color(Colors.YELLOW),
            new TextPiece("?").color(Colors.DARK_PURPLE),

            new TextPiece("\n"),

            new TextPiece("I'm fine thks").color(Colors.YELLOW)
    );

    @Override
    public void config() {
        vsync(false);
    }



    @Override
    public void init() {
        renderer = FontUtil.textRenderer(
                getFont(SERIF),
                false
        );
        renderer.init();
    }

    @Override
    public void update(double delta) {
    }

    @Override
    public void postDraw() {
        renderer.drawText2D(text, new Vector2f(-1.0f, 0f), 0f, 0.2f);
    }

    public static void main(String... args) {
        new TextRenderTest().launch();
    }
}

package xyz.upperlevel.graphicengine.api;

import org.joml.Vector2f;
import xyz.upperlevel.ulge.simple.SimpleGame;
import xyz.upperlevel.ulge.text.ArraySuperText;
import xyz.upperlevel.ulge.text.SuperText;
import xyz.upperlevel.ulge.text.TextPiece;
import xyz.upperlevel.ulge.text.impl.truetype.BitmapTextRenderer;
import xyz.upperlevel.ulge.util.Colors;
import xyz.upperlevel.ulge.util.FontUtil;
import xyz.upperlevel.ulge.window.event.action.Action;
import xyz.upperlevel.ulge.window.event.key.Key;

import static java.awt.Font.SERIF;
import static java.awt.Font.getFont;

public class TextRenderTest extends SimpleGame {
    private BitmapTextRenderer renderer;

    private SuperText text = new ArraySuperText(
            new TextPiece("How").color(Colors.AQUA),
            new TextPiece(" 'r").color(Colors.BLUE),
            new TextPiece(" u").color(Colors.DARK_AQUA),
            new TextPiece(" doing").color(Colors.YELLOW),
            new TextPiece("?").color(Colors.DARK_PURPLE)
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
        /*renderer = new BitmapTextRenderer(
                new Texture().setContent(UniversalTextureLoader.INSTANCE.load(new File("cat.jpg"))),
                renderer.getChars()
        );*/
        renderer.init();
    }

    @Override
    public void update(double delta) {
    }

    @Override
    public void keyChange(Key key, Action action) {
        super.keyChange(key, action);
        if(key == Key.D)
            renderer.debug = action == Action.PRESS;
    }

    @Override
    public void postDraw() {
        renderer.drawText2D(text, new Vector2f(-1.0f, -1.0f), 0f, 0.2f);
    }

    public static void main(String... args) {
        new TextRenderTest().launch();
    }
}

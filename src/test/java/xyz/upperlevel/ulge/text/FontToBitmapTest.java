package xyz.upperlevel.ulge.text;

import org.joml.Vector2f;
import xyz.upperlevel.ulge.opengl.texture.Texture2d;
import xyz.upperlevel.ulge.simple.SimpleGame;
import xyz.upperlevel.ulge.simple.shapes.Square;
import xyz.upperlevel.ulge.util.Color;
import xyz.upperlevel.ulge.util.FontUtil;

import static java.awt.Font.SERIF;
import static java.awt.Font.getFont;

public class FontToBitmapTest extends SimpleGame {

    public FontToBitmapTest() {
        super("Font to bitmap - TEST");
    }

    @Override
    public void config() {
        background(Color.AQUA);
    }

    @Override
    public void init() {
        simpleAlpha();

        Texture2d tex = FontUtil.textRenderer(getFont(SERIF)).getTexture();

        register(
                new Square(
                        new Vector2f(10f, 10f),
                        new Vector2f(width() - 10f, height() - 10f),
                        tex
                )
        );
    }

    public static void main(String... args) {
        new FontToBitmapTest().launch();
    }
}

package xyz.upperlevel.ulge.gui;

import xyz.upperlevel.ulge.gui.impl.Button;
import xyz.upperlevel.ulge.gui.impl.containers.SingletonContainer;
import xyz.upperlevel.ulge.gui.impl.containers.UnionContainer;
import xyz.upperlevel.ulge.gui.impl.text.TextBox;
import xyz.upperlevel.ulge.opengl.texture.Texture2D;
import xyz.upperlevel.ulge.opengl.texture.TextureFormat;
import xyz.upperlevel.ulge.opengl.texture.TextureParameters;
import xyz.upperlevel.ulge.opengl.texture.loader.ImageContent;
import xyz.upperlevel.ulge.simple.SimpleGETest;
import xyz.upperlevel.ulge.simple.SimpleGame;
import xyz.upperlevel.ulge.text.SuperText;
import xyz.upperlevel.ulge.text.TextPiece;
import xyz.upperlevel.ulge.util.Color;
import xyz.upperlevel.ulge.window.event.action.Action;
import xyz.upperlevel.ulge.window.event.button.MouseButton;

import static xyz.upperlevel.ulge.gui.GuiBackground.color;
import static xyz.upperlevel.ulge.gui.GuiBackground.texture;

public class SimpleGuiTest extends SimpleGame {

    private Gui gui;

    private SimpleGuiWrapper wrapper;


    @Override
    public void config() {
        vsync(false);
    }

    @Override
    public void init() {
        simpleAlpha();

        ImageContent c = ImageContent.fromResource("simple/cat.jpg", SimpleGETest.class);
        Texture2D tex = new Texture2D().loadImage(TextureFormat.RGBA, c);
        TextureParameters.getDefault().setup();

        gui = new SingletonContainer(
                new UnionContainer(
                        new Button((Button b) -> {
                            if(b.isClick())
                                return texture(tex);
                            else if(b.isHover())
                                return color(Color.WHITE);
                            else
                                return color(Color.AQUA);
                        }),
                        new TextBox()
                                .size(0.2f)
                                .text(SuperText.of(TextPiece.of("Click me", Color.BLACK)))
                ),
                new Bounds(0.25f, 0.25f, 0.75f, 0.75f)
        );

        wrapper = new SimpleGuiWrapper(gui);

        gui.init(DefaultGuiRenderer.$);
    }



    @Override
    public void postDraw() {
        DefaultGuiRenderer.$.getProgram().bind();
        gui.draw(DefaultGuiRenderer.$);

        wrapper.move(cursorPos());
    }

    @Override
    public void mouseChange(MouseButton button, Action action) {
        if(button == MouseButton.LEFT) {
            if(action == Action.PRESS)
                wrapper.clickBegin(cursorPos());
            else
                wrapper.clickEnd(cursorPos());
        }
    }

    public static void main(String... args) {
        new SimpleGuiTest().launch();
    }
}

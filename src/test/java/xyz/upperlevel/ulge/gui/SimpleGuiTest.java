package xyz.upperlevel.ulge.gui;

import xyz.upperlevel.ulge.gui.impl.Button;
import xyz.upperlevel.ulge.gui.impl.SingletonContainer;
import xyz.upperlevel.ulge.gui.impl.text.TextBox;
import xyz.upperlevel.ulge.opengl.texture.Texture2d;
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
        Texture2d tex = new Texture2d().loadImage(TextureFormat.RGBA, c);
        TextureParameters.getDefault().setup();

        gui = new SingletonContainer(
                new CustomButton(
                        new Bounds(0f, 0f, 1f, 1f),
                        color(Color.AQUA),
                        color(Color.WHITE),
                        texture(tex)
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


    public static class CustomButton extends Button {

        private TextBox text = new TextBox()
                .size(0.2f)
                .text(SuperText.of(TextPiece.of("Click me", Color.BLACK)));

        public CustomButton(Bounds bounds, GuiBackground color, GuiBackground hoverColor, GuiBackground clickColor) {
            super(bounds, color, hoverColor, clickColor);
        }

        @Override
        public void init(GuiRenderer r) {
            text.init(r);
        }

        @Override
        public void render(GuiRenderer r) {
            super.render(r);
            text.render(r);
        }
    }

    public static void main(String... args) {
        new SimpleGuiTest().launch();
    }
}

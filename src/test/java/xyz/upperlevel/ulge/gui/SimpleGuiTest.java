package xyz.upperlevel.ulge.gui;

import xyz.upperlevel.ulge.gui.impl.Button;
import xyz.upperlevel.ulge.gui.impl.SingletonContainer;
import xyz.upperlevel.ulge.gui.impl.text.TextBox;
import xyz.upperlevel.ulge.simple.SimpleGame;
import xyz.upperlevel.ulge.text.SuperText;
import xyz.upperlevel.ulge.text.TextPiece;
import xyz.upperlevel.ulge.text.TextRenderer;
import xyz.upperlevel.ulge.util.Color;
import xyz.upperlevel.ulge.window.event.action.Action;
import xyz.upperlevel.ulge.window.event.button.MouseButton;

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

        gui = new SingletonContainer(
                new CustomButton(
                        new Bounds(0f, 0f, 0.5f, 0.25f),
                        Color.AQUA,
                        Color.YELLOW,
                        Color.RED
                ),
                new Bounds(0.5f, 0.5f, 1f, 1f)
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
                .origin(TextRenderer.TextOrigin.UPPER_LEFT)
                .size(0.15f)
                .text(SuperText.of(TextPiece.of("Click me", Color.BLACK)));

        public CustomButton(Bounds bounds, Color color, Color hoverColor, Color clickColor) {
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

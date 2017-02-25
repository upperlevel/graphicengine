package xyz.upperlevel.ulge.gui;

import xyz.upperlevel.ulge.gui.impl.Button;
import xyz.upperlevel.ulge.gui.impl.SingletonContainer;
import xyz.upperlevel.ulge.simple.SimpleGame;
import xyz.upperlevel.ulge.util.Colors;
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
        gui = new SingletonContainer(
                new Button(
                        new Bounds(0f, 0f, 0.5f, 0.5f),
                        Colors.AQUA,
                        Colors.YELLOW,
                        Colors.RED
                ),
                new Bounds(0.5f, 0.5f, 1f, 1f)
        );
        wrapper = new SimpleGuiWrapper(gui);
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

package xyz.upperlevel.graphicengine.api;

import org.joml.Vector2f;
import org.joml.Vector3f;
import xyz.upperlevel.ulge.gui.Bounds;
import xyz.upperlevel.ulge.gui.DefaultGuiRenderer;
import xyz.upperlevel.ulge.gui.Gui;
import xyz.upperlevel.ulge.gui.impl.Button;
import xyz.upperlevel.ulge.simple.SimpleGame;
import xyz.upperlevel.ulge.util.Colors;
import xyz.upperlevel.ulge.window.event.key.Key;

public class SimpleGuiTest extends SimpleGame {

    private Gui gui;

    @Override
    public void init() {
        gui = new Button(
                Bounds.FULL,
                Colors.AQUA,
                Colors.YELLOW,
                Colors.RED
        );
    }

    @Override
    public void postDraw() {
        DefaultGuiRenderer.$.getProgram().bind();
        gui.draw(DefaultGuiRenderer.$);

        Vector2f position = cursorPos();
        if (gui.getBounds().isInside(position)) {
            gui.onHover(position);
            if (key(Key.ENTER))
                gui.onClickBegin(position);
            else if (key(Key.RIGHT_SHIFT))
                gui.onClickEnd(position);
        }
    }

    public static void main(String... args) {
        new SimpleGuiTest().launch();
    }
}

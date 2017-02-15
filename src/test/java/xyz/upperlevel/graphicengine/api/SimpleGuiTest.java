package xyz.upperlevel.graphicengine.api;

import org.joml.Vector2f;
import xyz.upperlevel.ulge.gui.Bounds;
import xyz.upperlevel.ulge.gui.DefaultGuiRenderer;
import xyz.upperlevel.ulge.gui.Gui;
import xyz.upperlevel.ulge.gui.impl.Button;
import xyz.upperlevel.ulge.simple.SimpleGame;
import xyz.upperlevel.ulge.util.Colors;
import xyz.upperlevel.ulge.window.event.button.MouseButton;

public class SimpleGuiTest extends SimpleGame {

    private Gui gui;

    private Vector2f lastPos = null;
    private boolean oldClick;

    @Override
    public void config() {
        vsync(false);
    }

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
        boolean click = mouse(MouseButton.LEFT);

        if (gui.getBounds().isInside(position)) {
            if(!position.equals(lastPos)) {
                if(lastPos == null)
                    gui.onMouseEnter(position);

                lastPos = position;
                gui.onHover(position);
            }
            if (click != oldClick) {
                if(click)
                    gui.onClickBegin(position);
                else
                    gui.onClickEnd(position);
                oldClick = click;
            }
        } else {
            if(lastPos != null) {
                gui.onMouseExit(lastPos);
                lastPos = null;
            }
        }
    }

    public static void main(String... args) {
        new SimpleGuiTest().launch();
    }
}

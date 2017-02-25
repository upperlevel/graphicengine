package xyz.upperlevel.ulge.gui;

import org.joml.Vector2f;
import xyz.upperlevel.ulge.gui.impl.Button;
import xyz.upperlevel.ulge.gui.impl.SingletonContainer;
import xyz.upperlevel.ulge.simple.SimpleGame;
import xyz.upperlevel.ulge.util.Colors;
import xyz.upperlevel.ulge.window.event.button.MouseButton;

import static xyz.upperlevel.ulge.gui.Bounds.isNormal;

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
        gui = new SingletonContainer(
                new Button(
                        new Bounds(0f, 0f, 0.5f, 0.5f),
                        Colors.AQUA,
                        Colors.YELLOW,
                        Colors.RED
                ),
                new Bounds(0f, 0f, 0.5f, 0.5f)
        );
    }

    @Override
    public void postDraw() {
        DefaultGuiRenderer.$.getProgram().bind();
        gui.draw(DefaultGuiRenderer.$);

        Vector2f position = cursorPos();
        boolean click = mouse(MouseButton.LEFT);

        Vector2f rel = gui.getBounds().relative(position, new Vector2f());

        if (isNormal(rel)) {
            if(!rel.equals(lastPos)) {
                if(lastPos == null)
                    gui.onMouseEnter(rel);

                lastPos = rel;
                gui.onMouseMove(rel);
            }
            if (click != oldClick) {
                //System.out.println(position.x + ", " + position.y);
                if(click)
                    gui.onClickBegin(rel);
                else
                    gui.onClickEnd(rel);
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

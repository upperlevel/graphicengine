package xyz.upperlevel.ulge.gui;

import org.joml.Vector2f;
import xyz.upperlevel.ulge.gui.impl.GuiContainer;
import xyz.upperlevel.ulge.gui.impl.GuiPane;
import xyz.upperlevel.ulge.simple.SimpleGame;
import xyz.upperlevel.ulge.util.Color;
import xyz.upperlevel.ulge.window.event.action.Action;
import xyz.upperlevel.ulge.window.event.button.MouseButton;
import xyz.upperlevel.ulge.window.event.key.Key;

import java.awt.*;

public class GuiTest extends SimpleGame {

    public GuiTest() {
    }

    private GuiRenderer renderer;

    private GuiViewer viewer;
    private GuiContainer gui;

    @Override
    public void config() {
        vsync(false);
    }

    private Font font;

    @Override
    public void init() {
        simpleAlpha();

        gui = new GuiContainer();

        GuiPane p = new GuiPane();
        p.setColor(Color.RED);
        p.setPosition(.1, .1);
        p.setSize(0.5, 0.5);

        gui.add(p);

        viewer = new GuiViewer();
        renderer = new GuiRenderer();
    }

    private Vector2f lastPos = new Vector2f();

    @Override
    public void postDraw() {
        renderer.getProgram().bind();
        gui.render(renderer);

        Vector2f curPos = cursorPos();
        gui.onCursorMove(lastPos.x, lastPos.y, curPos.x, curPos.y);
        lastPos = curPos;
    }

    @Override
    public void mouseChange(MouseButton button, Action action) {
        Vector2f cp = cursorPos();
        if (button == MouseButton.LEFT)
            if (action == Action.PRESS)
                gui.onClickBegin(cp.x, cp.y);
            else
                gui.onClickEnd(cp.x, cp.y);
    }

    @Override
    public void keyChange(Key k, Action a) {
        super.keyChange(k, a);
        if (k == Key.UP)
            gui.setY(gui.getY() + 0.01);
        else if (k == Key.DOWN)
            gui.setY(gui.getY() - 0.01);
        else if (k == Key.LEFT)
            gui.setX(gui.getX() - 0.01);
        else if (k == Key.RIGHT)
            gui.setX(gui.getX() + 0.01);

    }

    public static void main(String[] args) {
        new GuiTest().launch();
    }
}

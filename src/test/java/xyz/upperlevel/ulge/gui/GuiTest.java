package xyz.upperlevel.ulge.gui;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import xyz.upperlevel.ulge.gui.events.GuiClickBeginEvent;
import xyz.upperlevel.ulge.gui.impl.GuiContainer;
import xyz.upperlevel.ulge.gui.impl.GuiPane;
import xyz.upperlevel.ulge.simple.SimpleGame;
import xyz.upperlevel.ulge.util.Color;
import xyz.upperlevel.ulge.window.event.KeyChangeEvent;
import xyz.upperlevel.ulge.window.event.MouseButtonChangeEvent;
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

        GuiPane p = new CustomGuiPane();
        p.setColor(Color.RED);
        p.setPosition(.1, .1);
        p.setSize(0.5, 0.5);

        gui.add(p);


        GuiPane p2 = new CustomGuiPane();
        p2.setColor(Color.GREEN);
        p2.setPosition(0.8, 0.8);
        p2.setSize(0.1, 0.1);

        gui.add(p2);

        viewer = new GuiViewer();
        viewer.open(gui);
        renderer = new GuiRenderer();

    }

    private Vector2f lastPos = new Vector2f();

    @Override
    public void postDraw() {
        renderer.getProgram().bind();
        gui.render(new Matrix4f(), renderer);

        Vector2f curPos = cursorPos();
        viewer.move(lastPos.x, lastPos.y, curPos.x, curPos.y);
        lastPos = curPos;
    }

    @Override
    public void onMouseChange(MouseButtonChangeEvent event) {
        super.onMouseChange(event);
        Vector2f cp = cursorPos();
        if (event.getButton() == MouseButton.LEFT)
            if (event.getAction() == Action.PRESS)
                viewer.clickBegin(cp.x, cp.y);
            else
                viewer.clickEnd(cp.x, cp.y);
    }

    @Override
    public void onKeyChange(KeyChangeEvent event) {
        super.onKeyChange(event);
        Key k = event.getKey();
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

    public static class CustomGuiPane extends GuiPane {

        @Override
        public void render(Matrix4f transformation, GuiRenderer renderer) {
            if (isClicked()) {
                setColor(Color.RED);
            } else if (isHover()) {
                setColor(Color.YELLOW);
            } else {
                setColor(Color.GREEN);
            }
            super.render(transformation, renderer);
        }
    }
}

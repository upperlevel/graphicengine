package xyz.upperlevel.ulge.gui;

import xyz.upperlevel.ulge.gui.impl.GuiContainer;
import xyz.upperlevel.ulge.gui.impl.GuiPane;
import xyz.upperlevel.ulge.simple.SimpleGame;
import xyz.upperlevel.ulge.util.Color;
import xyz.upperlevel.ulge.window.event.KeyChangeEvent;
import xyz.upperlevel.ulge.window.event.key.Key;

public class GuiExample extends SimpleGame {

    public GuiExample() {
    }

    private GuiViewer viewer;
    private GuiContainer gui;

    @Override
    public void config() {
        vsync(false);
    }

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

        viewer = new GuiViewer(window());
        viewer.open(gui);
    }

    @Override
    public void postDraw() {
        viewer.render();
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
        new GuiExample().launch();
    }

    public static class CustomGuiPane extends GuiPane {

        @Override
        public void render(GuiBounds bounds) {
            if (isClicked()) {
                setColor(Color.RED);
            } else if (isHovered()) {
                setColor(Color.YELLOW);
            } else {
                setColor(Color.GREEN);
            }
            super.render(bounds);
        }
    }
}

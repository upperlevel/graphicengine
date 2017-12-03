package xyz.upperlevel.ulge.gui;

import xyz.upperlevel.ulge.gui.events.GuiContainer;
import xyz.upperlevel.ulge.simple.SimpleGame;
import xyz.upperlevel.ulge.util.Color;

public class GuiExample extends SimpleGame {

    public GuiExample() {
    }

    private GuiViewer viewer;
    private Gui gui;

    @Override
    public void config() {
        vsync(false);
    }

    @Override
    public void init() {
        simpleAlpha();

        gui = new GuiContainer();

        gui.setSize(width(), height());

        MyGui p = new MyGui();
        p.setOffset(10);
        p.setSize(100, 200);
        p.setAlign(GuiAlign.TOP_LEFT);

        gui.addChild(p);


        MyGui p2 = new MyGui();
        p2.setOffset(10);
        p2.setSize(100, 200);
        p2.setAlign(GuiAlign.CENTER);

        gui.addChild(p2);

        {
            Gui group = new Gui();
            group.setSize(200, 200);
            group.setOffset(15);
            group.setAlign(GuiAlign.BOTTOM_RIGHT);

            MyGui i1 = new MyGui();
            i1.setOffset(0);
            i1.setSize(100, 200);
            i1.setAlign(GuiAlign.CENTER_LEFT);
            group.addChild(i1);

            MyGui i2 = new MyGui();
            i2.setOffset(0);
            i2.setSize(100, 200);
            i2.setAlign(GuiAlign.CENTER_RIGHT);
            group.addChild(i2);

            gui.addChild(group);
        }

        viewer = new GuiViewer(window());
        viewer.open(gui);

        gui.reloadLayout();
    }

    @Override
    public void postDraw() {
        viewer.render();
    }

    public static void main(String[] args) {
        new GuiExample().launch();
    }

    public static class MyGui extends Gui {

        @Override
        public void render() {
            if (isClicked()) {
                setBackground(GuiBackground.color(Color.RED));
            } else if (isHovered()) {
                setBackground(GuiBackground.color(Color.YELLOW));
            } else {
                setBackground(GuiBackground.color(Color.GREEN));
            }
            super.render();
        }
    }
}

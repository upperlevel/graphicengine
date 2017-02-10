package xyz.upperlevel.graphicengine.api;

import xyz.upperlevel.ulge.gui.Bounds;
import xyz.upperlevel.ulge.gui.DefaultGuiRenderer;
import xyz.upperlevel.ulge.gui.Gui;
import xyz.upperlevel.ulge.gui.impl.Button;
import xyz.upperlevel.ulge.simple.SimpleGame;
import xyz.upperlevel.ulge.util.Colors;

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
        DefaultGuiRenderer.INSTANCE.program.bind();
        gui.draw(DefaultGuiRenderer.INSTANCE);
    }



    public static void main(String... args) {
        new SimpleGuiTest().launch();
    }
}

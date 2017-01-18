package xyz.upperlevel.graphicengine.api;

import org.joml.Vector2f;
import xyz.upperlevel.graphicengine.api.simple.SimpleColor;
import xyz.upperlevel.graphicengine.api.simple.SimpleGraphicEngine;
import xyz.upperlevel.graphicengine.api.simple.Square;
import xyz.upperlevel.graphicengine.api.window.GLFW;
import xyz.upperlevel.graphicengine.api.window.Window;

import static org.lwjgl.opengl.GL11.GL_ALPHA;
import static org.lwjgl.opengl.GL11.glEnable;

public class SimpleGETest {
    private static Window w;
    public static SimpleGraphicEngine engine;

    public static void main(String... args) {
        w = GLFW.createWindow(500, 500, "Ciao");
        w.contextualize();
        w.show();

        engine = new SimpleGraphicEngine();

        glEnable(GL_ALPHA);

        Square square = new Square(
                new Vector2f(0.5f, 0.5f),
                new Vector2f(0.5f),
                SimpleColor.RED
        );

        engine.register(square);

        while (!w.isClosed()) {
            engine.draw();
            w.update();
        }
    }
}

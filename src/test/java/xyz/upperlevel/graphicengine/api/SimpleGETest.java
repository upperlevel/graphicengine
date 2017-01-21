package xyz.upperlevel.graphicengine.api;

import org.joml.Vector2f;
import xyz.upperlevel.graphicengine.api.opengl.texture.Texture;
import xyz.upperlevel.graphicengine.api.opengl.texture.loader.UniversalTextureLoader;
import xyz.upperlevel.graphicengine.api.simple.SimpleGraphicEngine;
import xyz.upperlevel.graphicengine.api.simple.shapes.Circle;
import xyz.upperlevel.graphicengine.api.window.GLFW;
import xyz.upperlevel.graphicengine.api.window.Window;
import xyz.upperlevel.graphicengine.api.window.event.key.Key;

import java.io.File;

import static org.lwjgl.glfw.GLFW.glfwGetTime;
import static org.lwjgl.opengl.GL11.GL_ALPHA;
import static org.lwjgl.opengl.GL11.glEnable;

public class SimpleGETest {
    private static Window w;
    public static SimpleGraphicEngine engine;

    public static Circle obj;

    public static void main(String... args) {
        w = GLFW.createWindow(500, 500, "Ciao", false);
        w.contextualize();
        w.show();
        w.setVSync(false);

        engine = new SimpleGraphicEngine(w.getWidth(), w.getHeight());


        glEnable(GL_ALPHA);

        /*obj = new Circle(
                new Vector2f(100f, 0f),
                new Vector2f(w.getWidth() / 8, w.getHeight() / 8),
                SimpleColor.RED
        );*/

        obj = new Circle(
                new Vector2f(100f, 50f),
                new Vector2f(w.getWidth() / 2, w.getHeight() / 2),
                new Texture().setContent(UniversalTextureLoader.INSTANCE.load(new File("cat.jpg"))),
                50
        );

        engine.register(obj);

        long lastFrame = System.currentTimeMillis();

        /*glPolygonMode(GL_FRONT, GL_LINE);
        glPolygonMode(GL_BACK, GL_LINE);*/

        while (!w.isClosed()) {
            //obj.rotation += 0.01;//only if the object is a cube

            fpsCount();

            long thisFrame = System.currentTimeMillis();
            update(thisFrame - lastFrame);
            lastFrame = thisFrame;

            engine.draw();
            w.update();
        }
    }

    public static int nbFrames = 0;
    public static double lastTime = -1;

    public static void fpsCount() {
        double currentTime = glfwGetTime();
        if(lastTime == -1) {
            lastTime = currentTime;
            return;
        }
        nbFrames++;
        if ( currentTime - lastTime >= 1.0 ){ // If last print was more than 1 sec ago
            // printf and reset timer
            System.out.println("FPS:" + nbFrames);
            nbFrames = 0;
            lastTime += 1.0;
        }
    }

    public static final float VEL = 1f;

    public static void update(long delta) {
        /*if(w.getKey(GLFWKey.ESCAPE))
            w.close();*/
        if(w.getKey(Key.RIGHT))
            obj.pos.add(VEL * delta, 0f);
        if(w.getKey(Key.LEFT))
            obj.pos.sub(VEL * delta, 0f);
    }
}

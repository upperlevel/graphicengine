package xyz.upperlevel.ulge.simple;

import org.joml.Vector2f;
import xyz.upperlevel.ulge.opengl.texture.Texture2D;
import xyz.upperlevel.ulge.opengl.texture.TextureFormat;
import xyz.upperlevel.ulge.opengl.texture.TextureParameters;
import xyz.upperlevel.ulge.opengl.texture.loader.ImageContent;
import xyz.upperlevel.ulge.simple.shapes.Circle;
import xyz.upperlevel.ulge.window.GLFW;
import xyz.upperlevel.ulge.window.Window;
import xyz.upperlevel.ulge.window.event.key.Key;

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
                new Vector2f(w.getRw() / 8, w.getRh() / 8),
                SimpleColor.RED
        );*/

        ImageContent c = ImageContent.fromResource("simple/cat.jpg", SimpleGETest.class);
        Texture2D tex = new Texture2D()
                .loadImage(TextureFormat.RGBA, c);
        TextureParameters.getDefault().setup();

        obj = new Circle(
                new Vector2f(100f, 50f),
                new Vector2f(w.getWidth() / 2, w.getHeight() / 2),
                tex,
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
        if (lastTime == -1) {
            lastTime = currentTime;
            return;
        }
        nbFrames++;
        if (currentTime - lastTime >= 1.0) { // If last print was more than 1 sec ago
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
        if (w.getKey(Key.RIGHT))
            obj.pos.add(VEL * delta, 0f);
        if (w.getKey(Key.LEFT))
            obj.pos.sub(VEL * delta, 0f);
    }
}

package xyz.upperlevel.ulge.simple;

import org.joml.Vector2f;
import xyz.upperlevel.event.EventHandler;
import xyz.upperlevel.event.Listener;
import xyz.upperlevel.ulge.util.Color;
import xyz.upperlevel.ulge.window.Glfw;
import xyz.upperlevel.ulge.window.Window;
import xyz.upperlevel.ulge.window.event.KeyChangeEvent;
import xyz.upperlevel.ulge.window.event.MouseButtonChangeEvent;
import xyz.upperlevel.ulge.window.event.button.MouseButton;
import xyz.upperlevel.ulge.window.event.key.Key;

import java.util.Arrays;
import java.util.List;

import static org.lwjgl.glfw.GLFW.glfwGetTime;
import static org.lwjgl.opengl.GL11.*;

public class SimpleGame implements Listener {

    private int width = 800, height = 500;
    private String title;
    private boolean vsync = true;
    private Window window;

    private boolean fps = true;
    private int fps_frames = 0;
    private double fps_lastTime;
    private double lastFrame = -1;

    private Color background = Color.BLACK;

    private SimpleGraphicEngine engine;

    public SimpleGame(String title) {
        this.title = title;
    }

    public SimpleGame() {
        this("ULGE");
    }


    public void launch() {
        config();
        window = Glfw.createWindow(width, height, title, false);
        window.contextualize();
        window.show();
        window.setVSync(vsync);
        window.setTitle(title);

        System.out.println("REGISTERING");
        window.getEventManager().register(this);

        engine = new SimpleGraphicEngine(width, height, background);

        init();

        while (!window.isClosed()) {
            update(updateTime());
            preDraw();
            engine.draw();
            postDraw();
            window.update();
        }
        destroy();
    }

    protected void preDraw() {
    }

    protected void postDraw() {
    }


    public void simpleAlpha() {
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }

    public int width() {
        return width;
    }

    public void width(int width) {
        checkPreWindowState();
        this.width = width;
    }


    public int height() {
        return height;
    }

    public void height(int height) {
        checkPreWindowState();
        this.height = height;
    }


    public void size(int width, int height) {
        checkPreWindowState();
        this.width = width;
        this.height = height;
    }

    public boolean vsync() {
        return vsync;
    }

    public void vsync(boolean vsync) {
        checkPreWindowState();
        this.vsync = vsync;
    }

    public String title() {
        return title;
    }

    public void title(String title) {
        checkPreWindowState();
        this.title = title;
    }

    public Color background() {
        return background;
    }

    public void background(Color color) {
        checkPreWindowState();
        this.background = color;
    }


    public boolean isStopped() {
        return window.isClosed();
    }

    public boolean isRunning() {
        return !window.isClosed();
    }

    public void stop() {
        window.close();
    }


    public void register(Renderable renderable) {
        engine.register(renderable);
    }

    public void register(Renderable... renderables) {
        engine.register(Arrays.asList(renderables));
    }

    public void register(List<Renderable> renderables) {
        engine.register(renderables);
    }

    public void remove(Renderable renderable) {
        engine.remove(renderable);
    }

    public void remove(List<Renderable> renderables) {
        engine.remove(renderables);
    }

    private double updateTime() {
        double currentTime = glfwGetTime();
        if (lastFrame == -1) {
            lastFrame = currentTime;
            return 0;
        }

        if (fps) {
            if (fps_lastTime == -1) {
                fps_lastTime = currentTime;
            } else {
                fps_frames++;
                if (currentTime - fps_lastTime >= 1.0) { // If last print was more than 1 sec ago
                    // print and reset timer
                    showFPS(fps_frames);
                    fps_frames = 0;
                    fps_lastTime += 1.0;
                }
            }
        }

        double delta = currentTime - lastFrame;
        lastFrame = currentTime;
        return delta;
    }

    public void showFPS(float fps) {
        System.out.println("FPS: " + fps);
    }


    public boolean key(Key key) {
        return window.getKey(key);
    }

    public Vector2f cursorPos() {
        return window.getCursorPosition();
    }

    public boolean mouse(MouseButton button) {
        return window.getMouseButton(button);
    }

    private void checkPreWindowState() {
        if (window != null)
            throw new IllegalStateException("Cannot call this after window creation!");
    }

    public void config() {
    }

    public void init() {
    }

    public void update(double delta) {
    }

    @EventHandler
    public void onMouseChange(MouseButtonChangeEvent event) {
    }

    @EventHandler
    public void onKeyChange(KeyChangeEvent event) {
        if (event.getKey() == Key.ESCAPE)
            window.close();
    }

    public void destroy() {
    }
}

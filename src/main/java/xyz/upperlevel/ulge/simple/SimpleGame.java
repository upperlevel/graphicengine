package xyz.upperlevel.ulge.simple;

import lombok.Getter;
import lombok.Setter;
import org.joml.Vector2d;
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
    // Window
    @Getter
    private Window window;

    @Getter
    @Setter
    private Color background = Color.BLACK;

    @Getter
    private SimpleGraphicEngine engine;

    // Timings
    @Getter
    private int currentFps;
    private int fpsCounter = 0;
    private double lastFpsTime = -1, lastFrame = -1;
    private double delta;

    private void updateTimings() {
        // Delta
        double currentTime = System.currentTimeMillis();
        if (lastFrame == -1) {
            lastFrame = currentTime;
            delta = 0;
        }
        delta = currentTime - lastFrame;
        lastFrame = currentTime;
        // Fps
        fpsCounter++;
        if (lastFpsTime == -1) {
            lastFpsTime = currentTime;
        } else {
            if (currentTime - lastFpsTime >= 1000) {
                currentFps = fpsCounter;
                lastFpsTime = currentTime;
            }
        }
    }

    /**
     * Launches the game.
     */
    public void launch() {
        window = onConfig();
        window.getEventManager().register(this);

        window.contextualize();
        window.show();

        engine = new SimpleGraphicEngine(window.getWidth(), window.getHeight(), background);

        onInit();
        while (!window.isClosed()) {
            updateTimings();
            onUpdate(delta);

            onPreDraw();
            engine.draw();
            onPostDraw();

            window.update();
        }
        onDestroy();
    }

    public int getWidth() {
        return window.getWidth();
    }

    public int getHeight() {
        return window.getHeight();
    }

    /**
     * Enables alpha.
     */
    public void enableSimpleAlpha() {
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
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

    /**
     * Gets cursor position.
     *
     * @return the position
     */
    public Vector2d getCursorPosition() {
        return window.getCursorPosition();
    }

    /**
     * Tests if the key is pressed.
     *
     * @param key the key
     * @return {@code true} if pressed.
     */
    public boolean testKey(Key key) {
        return window.testKey(key);
    }

    /**
     * Tests if the button is pressed.
     *
     * @param button the key
     * @return {@code true} if pressed.
     */
    public boolean testMouseButton(MouseButton button) {
        return window.testMouseButton(button);
    }

    private void checkPreWindowState() {
        if (window != null)
            throw new IllegalStateException("Cannot call this after window creation!");
    }

    /**
     * Called before window initializing.
     * Here you may init the window.
     */
    public Window onConfig() {
        return Glfw.createWindow(500, 250, "Ulge", false);
    }

    /**
     * Called soon after window initializing.
     * Here you may init OpenGL functions.
     */
    public void onInit() {
    }

    /**
     * Called before rendering the frame.
     *
     * @param delta the time between the current frame and the latest
     */
    public void onUpdate(double delta) {
    }

    /**
     * Called before engine draw-call.
     */
    public void onPreDraw() {
    }

    /**
     * Called after engine draw-call.
     */
    public void onPostDraw() {
    }

    /**
     * Called when the game is closed.
     */
    public void onDestroy() {
    }

    @EventHandler
    public void onMouseButtonChange(MouseButtonChangeEvent event) {
    }

    @EventHandler
    public void onKeyChange(KeyChangeEvent event) {
    }
}

package xyz.upperlevel.ulge.game;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import xyz.upperlevel.ulge.window.Glfw;
import xyz.upperlevel.ulge.window.Window;
import xyz.upperlevel.ulge.window.event.*;

import static java.lang.System.currentTimeMillis;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

@Getter
public class Game {
    private Window window;
    private Stage stage;

    public Game(Window window) {
        setWindow(window);
        this.stage = new Stage();
    }

    public void setWindow(Window window) {
        this.window = window;
        window.contextualize();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        this.stage.onEnable(null);
    }

    @SuppressWarnings("unchecked")
    public void setup() {
        WindowEventHandler event;

        // mouse button change
        event = Glfw.events().MOUSE_BUTTON_CHANGE.create();
        event.register((MouseButtonChangeEvent) (window, button, action) -> {
            stage.onMouseButtonChange(button, action);
        });
        event.apply(window);

        // key button change
        event = Glfw.events().KEY_CHANGE.create();
        event.register((KeyChangeEvent) (window, key, action) -> {
            stage.onKeyChange(key, action);
        });
        event.apply(window);

        // mouse scroll
        event = Glfw.events().MOUSE_SCROLL.create();
        event.register((MouseScrollEvent) (window, x, y) -> {
            stage.onMouseScroll(x, y);
        });
        event.apply(window);

        // cursor move
        event = Glfw.events().CURSOR_MOVE.create();
        event.register((CursorMoveEvent) (window, x, y) -> {
            if (stage.getScene() != null)
                stage.getScene().onCursorMove(x, y);
        });
        event.apply(window);
    }

    @Setter
    private long tickEach = 1000;
    private long fps = 0;

    public void start() {
        window.show();

        long lastTick = currentTimeMillis();
        long lastFps = currentTimeMillis();
        long now;

        long fpsCounter = 0;

        while (!window.isClosed()) {
            glClear(GL_COLOR_BUFFER_BIT);

            // tick update
            now = currentTimeMillis();
            if (now - lastTick >= tickEach) {
                stage.onTick();
                lastTick = now;
            }

            // fps update
            fpsCounter++;
            now = currentTimeMillis();
            if (now - lastFps >= 1000) {
                fps = fpsCounter;
                stage.onFps();
                lastFps = now;
                fpsCounter = 0;
            }

            // render update
            stage.onRender();

            window.update();
        }

        fps = 0;
    }

    public void stop() {
        stage.setScene(null);
        window.close();
    }
}

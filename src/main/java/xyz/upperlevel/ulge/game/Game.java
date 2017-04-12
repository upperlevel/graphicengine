package xyz.upperlevel.ulge.game;

import lombok.Getter;
import xyz.upperlevel.ulge.window.Glfw;
import xyz.upperlevel.ulge.window.Window;
import xyz.upperlevel.ulge.window.event.*;

import static java.lang.System.currentTimeMillis;

public class Game {

    @Getter
    private Window window;

    @Getter
    private Scene scene = null;

    public Game(GamePresettings presettings) {
        window = presettings.createWindow();
    }

    @SuppressWarnings("unchecked")
    public void setup() {
        WindowEventHandler event;

        // mouse button change
        event = Glfw.events().MOUSE_BUTTON_CHANGE.create();
        event.register((MouseButtonChangeEvent) (window, button, action) -> {
            if (scene != null)
                scene.mouseButtonChange(button, action);
        });
        event.apply(window);

        // key button change
        event = Glfw.events().KEY_CHANGE.create();
        event.register((KeyChangeEvent) (window, key, action) -> {
            if (scene != null)
                scene.keyChange(key, action);
        });
        event.apply(window);

        // mouse scroll
        event = Glfw.events().MOUSE_SCROLL.create();
        event.register((MouseScrollEvent) (window, x, y) -> {
            if (scene != null)
                scene.mouseScroll(x, y);
        });
        event.apply(window);

        // cursor move
        event = Glfw.events().CURSOR_MOVE.create();
        event.register((CursorMoveEvent) (window, x, y) -> {
            if (scene != null)
                scene.cursorMove(x, y);
        });
        event.apply(window);
    }

    @Getter
    private long tickEach = 1000;

    @Getter
    private long fps = 0;

    public void start() {
        window.contextualize();
        window.show();

        long lastTick = currentTimeMillis();
        long lastFps  = currentTimeMillis();
        long now;

        long fpsCounter = 0;

        while (!window.isClosed()) {
            // tick update
            now = currentTimeMillis();
            if (now - lastTick >= tickEach) {
                if (scene != null)
                    scene.tick();
                lastTick = now;
            }

            // fps update
            fpsCounter++;
            now = currentTimeMillis();
            if (now - lastFps >= 1000) {
                fps = fpsCounter;
                if (scene != null)
                    scene.fps();
                fpsCounter = 0;
            }

            // render update
            if (scene != null)
                scene.render();

            window.update();
        }

        fps = 0;
    }

    public void stop() {
        window.close();
    }
}

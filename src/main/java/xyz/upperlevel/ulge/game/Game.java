package xyz.upperlevel.ulge.game;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import xyz.upperlevel.ulge.window.Glfw;
import xyz.upperlevel.ulge.window.Window;
import xyz.upperlevel.ulge.window.event.*;

import static java.lang.System.currentTimeMillis;
import static org.lwjgl.opengl.GL11.*;

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

    @Setter
    private long tickEach = 1000 / 20;
    private long fps = 0;

    public void start() {
        window.show();

        long lastTick = currentTimeMillis();
        long lastFps = currentTimeMillis();
        long now;

        long fpsCounter = 0;

        glEnable(GL_DEPTH_TEST);
        glEnable(GL_ALPHA_TEST);

        while (!window.isClosed()) {
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

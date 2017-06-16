package xyz.upperlevel.ulge.game;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import xyz.upperlevel.ulge.window.Glfw;
import xyz.upperlevel.ulge.window.Window;

@Accessors(
        fluent = true,
        chain  = true
)
public class GameSettings {

    @Getter
    @Setter
    public int width, height;

    @Getter
    @Setter
    public String title;

    @Getter
    @Setter
    public boolean fullscreen;

    public GameSettings() {
    }

    public Window createWindow() {
        return Glfw.createWindow(width, height, title, fullscreen);
    }
}
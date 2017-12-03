package xyz.upperlevel.ulge.window.event;

import lombok.Getter;
import xyz.upperlevel.ulge.window.Window;

public class ResizeEvent extends WindowEvent {
    @Getter
    private final int width, height;

    public ResizeEvent(Window window, int width, int height) {
        super(window);
        this.width = width;
        this.height = height;
    }
}

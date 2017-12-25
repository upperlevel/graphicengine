package xyz.upperlevel.ulge.window.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.upperlevel.ulge.window.Window;

/**
 * Event called when user scrolls testMouseButton wheel.
 */
@Getter
public class MouseScrollEvent extends WindowEvent {
    private final double x;
    private final double y;

    public MouseScrollEvent(Window window, double x, double y) {
        super(window);
        this.x = x;
        this.y = y;
    }
}

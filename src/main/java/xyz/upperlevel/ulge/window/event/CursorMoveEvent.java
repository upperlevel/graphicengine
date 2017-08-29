package xyz.upperlevel.ulge.window.event;

import lombok.Getter;
import xyz.upperlevel.ulge.window.Window;

/**
 * Event called when user moves the cursor.
 */
@Getter
public class CursorMoveEvent extends WindowEvent {
    private final double x;
    private final double y;

    public CursorMoveEvent(Window window, double x, double y) {
        super(window);
        this.x = x;
        this.y = y;
    }
}

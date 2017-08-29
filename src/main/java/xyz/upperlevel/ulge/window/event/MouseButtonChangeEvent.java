package xyz.upperlevel.ulge.window.event;

import lombok.Getter;
import xyz.upperlevel.ulge.window.Window;
import xyz.upperlevel.ulge.window.event.action.Action;
import xyz.upperlevel.ulge.window.event.button.MouseButton;

/**
 * Event called when user clicks.
 */
@Getter
public class MouseButtonChangeEvent extends WindowEvent {
    private final MouseButton button;
    private final Action action;

    public MouseButtonChangeEvent(Window window, MouseButton button, Action action) {
        super(window);
        this.button = button;
        this.action = action;
    }
}

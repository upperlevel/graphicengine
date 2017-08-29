package xyz.upperlevel.ulge.window.event;

import lombok.Getter;
import xyz.upperlevel.ulge.window.Window;
import xyz.upperlevel.ulge.window.event.action.Action;
import xyz.upperlevel.ulge.window.event.key.Key;

/**
 * Event called when user press/release a keyboard key.
 **/
@Getter
public class KeyChangeEvent extends WindowEvent {
    private final Key key;
    private final Action action;

    public KeyChangeEvent(Window window, Key key, Action action) {
        super(window);
        this.key = key;
        this.action = action;
    }
}

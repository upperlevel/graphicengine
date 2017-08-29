package xyz.upperlevel.ulge.game.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.upperlevel.event.Event;
import xyz.upperlevel.ulge.window.event.action.Action;
import xyz.upperlevel.ulge.window.event.button.MouseButton;

/**
 * Event called when user clicks.
 */
@Getter
@RequiredArgsConstructor
public class MouseButtonChangeEvent implements Event {
    private final MouseButton button;
    private final Action action;
}

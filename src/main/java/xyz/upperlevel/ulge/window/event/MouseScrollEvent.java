package xyz.upperlevel.ulge.game.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.upperlevel.event.Event;

/**
 * Event called when user scrolls mouse wheel.
 */
@Getter
@RequiredArgsConstructor
public class MouseScrollEvent implements Event {
    private final double x;
    private final double y;
}

package xyz.upperlevel.ulge.game.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.upperlevel.event.Event;

/**
 * Event called when user moves the cursor.
 */
@Getter
@RequiredArgsConstructor
public class CursorMoveEvent implements Event {
    private final double x;
    private final double y;
}

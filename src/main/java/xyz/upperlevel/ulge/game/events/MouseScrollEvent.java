package xyz.upperlevel.ulge.game.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Event called when user scrolls mouse wheel.
 */
@Getter
@RequiredArgsConstructor
public class MouseScrollEvent {
    private final double x;
    private final double y;
}

package xyz.upperlevel.ulge.game.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.upperlevel.event.Event;
import xyz.upperlevel.ulge.window.event.action.Action;
import xyz.upperlevel.ulge.window.event.key.Key;

/**
 * Event called when user press/release a keyboard key.
 **/
@Getter
@RequiredArgsConstructor
public class KeyChangeEvent implements Event {
    private final Key key;
    private final Action action;
}

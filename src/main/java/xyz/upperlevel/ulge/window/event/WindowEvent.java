package xyz.upperlevel.ulge.window.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.upperlevel.event.Event;
import xyz.upperlevel.ulge.window.Window;

@RequiredArgsConstructor
public class WindowEvent implements Event {
    @Getter
    private final Window window;
}

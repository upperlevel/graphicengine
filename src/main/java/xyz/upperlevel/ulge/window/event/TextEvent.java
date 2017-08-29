package xyz.upperlevel.ulge.window.event;

import lombok.Getter;
import xyz.upperlevel.ulge.window.Window;

@Getter
public class TextEvent extends WindowEvent {
    private final char character;

    public TextEvent(Window window, char character) {
        super(window);
        this.character = character;
    }
}

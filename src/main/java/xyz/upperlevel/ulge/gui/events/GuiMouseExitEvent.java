package xyz.upperlevel.ulge.gui.events;

import lombok.NonNull;
import org.joml.Vector2f;
import xyz.upperlevel.ulge.gui.Gui;

public class GuiMouseExitEvent extends GuiEvent {

    private final Vector2f lastPos;

    public GuiMouseExitEvent(Gui gui, @NonNull Vector2f lastPos) {
        super(gui);
        this.lastPos = lastPos;
    }

    public Vector2f getLastPos() {
        return new Vector2f(lastPos);
    }

    public float getLastX() {
        return lastPos.x;
    }

    public float getLastY() {
        return lastPos.y;
    }
}

package xyz.upperlevel.ulge.gui.events;

import lombok.NonNull;
import org.joml.Vector2f;
import xyz.upperlevel.ulge.gui.Gui;

public class GuiDragEvent extends GuiEvent {

    private final Vector2f old, n;

    public GuiDragEvent(Gui gui, @NonNull Vector2f old, @NonNull Vector2f n) {
        super(gui);
        this.old = old;
        this.n = n;
    }

    public Vector2f getNewPos() {
        return new Vector2f(n);
    }

    public float getNewX() {
        return n.x;
    }

    public float getNewY() {
        return n.y;
    }

    public Vector2f getOldPos() {
        return new Vector2f(old);
    }

    public float getOldX() {
        return old.x;
    }

    public float getOldY() {
        return old.y;
    }
}

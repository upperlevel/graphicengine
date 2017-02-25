package xyz.upperlevel.ulge.gui.events;

import lombok.NonNull;
import org.joml.Vector2f;
import xyz.upperlevel.ulge.gui.Gui;

public class GuiMouseMoveEvent extends GuiEvent {
    private final Vector2f old, pos;

    public GuiMouseMoveEvent(Gui gui, @NonNull Vector2f old, @NonNull Vector2f pos) {
        super(gui);
        this.old = old;
        this.pos = pos;
    }

    public Vector2f getPos() {
        return new Vector2f(pos);
    }

    public float getPosX() {
        return pos.x;
    }

    public float getPosY() {
        return pos.y;
    }

    public Vector2f getOld() {
        return new Vector2f(old);
    }

    public float getOldX() {
        return old.x;
    }

    public float getOldY() {
        return old.y;
    }


    public float getX() {
        return pos.x;
    }

    public float getY() {
        return pos.y;
    }
}

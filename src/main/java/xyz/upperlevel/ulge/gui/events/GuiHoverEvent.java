package xyz.upperlevel.ulge.gui.events;

import org.joml.Vector2f;
import xyz.upperlevel.ulge.gui.Gui;

public class GuiHoverEvent extends GuiEvent {
    private final Vector2f pos;

    public GuiHoverEvent(Gui gui, Vector2f pos) {
        super(gui);
        this.pos = pos;
    }

    public Vector2f getPos() {
        return pos == null ? null : new Vector2f(pos);
    }

    public float getX() {
        return pos.x;
    }

    public float getY() {
        return pos.y;
    }
}

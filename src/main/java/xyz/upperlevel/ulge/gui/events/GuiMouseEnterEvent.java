package xyz.upperlevel.ulge.gui.events;

import org.joml.Vector2f;
import xyz.upperlevel.ulge.gui.Gui;

public class GuiMouseEnterEvent extends GuiEvent {

    private final Vector2f enterPos;

    public GuiMouseEnterEvent(Gui gui, Vector2f enterPos) {
        super(gui);
        this.enterPos = enterPos;
    }

    public Vector2f getEnterPos() {
        return new Vector2f(enterPos);
    }

    public float getX() {
        return enterPos.x;
    }

    public float getY() {
        return enterPos.y;
    }
}

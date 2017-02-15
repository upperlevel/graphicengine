package xyz.upperlevel.ulge.gui.events;

import lombok.Getter;
import org.joml.Vector2f;
import xyz.upperlevel.ulge.gui.Gui;

public class GuiClickEvent extends GuiEvent {
    private final Vector2f pos;
    @Getter
    private final Type type;


    public GuiClickEvent(Gui gui, Vector2f pos, Type type) {
        super(gui);
        this.pos = pos;
        this.type = type;
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

    public enum Type {
        BEGIN, DRAG, END
    }
}

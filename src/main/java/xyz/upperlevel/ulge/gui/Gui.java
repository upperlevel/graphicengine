package xyz.upperlevel.ulge.gui;

import org.joml.Vector2f;
import xyz.upperlevel.ulge.events.EventManager;
import xyz.upperlevel.ulge.gui.events.GuiClickEvent;
import xyz.upperlevel.ulge.gui.events.GuiCloseEvent;
import xyz.upperlevel.ulge.gui.events.GuiHoverEvent;
import xyz.upperlevel.ulge.gui.events.GuiOpenEvent;

public interface Gui {
    void init(GuiRenderer r);

    void draw(GuiRenderer r);

    default boolean hover(Vector2f pos) {
        return getEventManager().call(new GuiHoverEvent(this, pos));
    }

    default boolean clickBegin(Vector2f pos) {
        return getEventManager().call(new GuiClickEvent(this, pos, GuiClickEvent.Type.BEGIN));
    }

    default boolean clickDrag(Vector2f pos) {
        return getEventManager().call(new GuiClickEvent(this, pos, GuiClickEvent.Type.DRAG));
    }

    default boolean clickEnd(Vector2f pos) {
        return getEventManager().call(new GuiClickEvent(this, pos, GuiClickEvent.Type.END));
    }

    default boolean onOpen() {
        return getEventManager().call(new GuiOpenEvent(this));
    }

    default boolean onClose() {
        return getEventManager().call(new GuiCloseEvent(this));
    }

    EventManager getEventManager();

    Bounds getBounds();

    default void setBounds(Bounds bounds) {
        throw new UnsupportedOperationException();
    }
}

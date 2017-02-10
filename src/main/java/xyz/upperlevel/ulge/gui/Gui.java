package xyz.upperlevel.ulge.gui;

import org.joml.Vector2f;
import xyz.upperlevel.ulge.events.EventManager;
import xyz.upperlevel.ulge.gui.events.GuiClickEvent;
import xyz.upperlevel.ulge.gui.events.GuiCloseEvent;
import xyz.upperlevel.ulge.gui.events.GuiHoverEvent;
import xyz.upperlevel.ulge.gui.events.GuiOpenEvent;

public interface Gui {

    void init(GuiRenderer renderer);

    void draw(GuiRenderer renderer);

    default boolean onHover(Vector2f position) {
        return getEventManager().call(new GuiHoverEvent(this, position));
    }

    default boolean onClickBegin(Vector2f position) {
        return getEventManager().call(new GuiClickEvent(this, position, GuiClickEvent.Type.BEGIN));
    }

    default boolean onClickDrag(Vector2f position) {
        return getEventManager().call(new GuiClickEvent(this, position, GuiClickEvent.Type.DRAG));
    }

    default boolean onClickEnd(Vector2f position) {
        return getEventManager().call(new GuiClickEvent(this, position, GuiClickEvent.Type.END));
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

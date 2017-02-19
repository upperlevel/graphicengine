package xyz.upperlevel.ulge.gui;

import org.joml.Vector2f;
import xyz.upperlevel.ulge.gui.events.*;
import xyz.upperlevel.utils.event.EventManager;

public interface Gui {

    void init(GuiRenderer renderer);

    void draw(GuiRenderer renderer);

    default boolean onMouseMove(Vector2f position) {
        return getEventManager().call(new GuiMouseMoveEvent(this, position));
    }

    default boolean onClickBegin(Vector2f position) {
        return getEventManager().call(new GuiClickEvent(this, position, GuiClickEvent.Type.BEGIN));
    }

    default boolean onClickEnd(Vector2f position) {
        return getEventManager().call(new GuiClickEvent(this, position, GuiClickEvent.Type.END));
    }

    default boolean onMouseEnter(Vector2f enterPos) {
        return getEventManager().call(new GuiMouseEnterEvent(this, enterPos));
    }

    default boolean onMouseExit(Vector2f lastPos) {
        return getEventManager().call(new GuiMouseExitEvent(this, lastPos));
    }

    default boolean onOpen() {
        return getEventManager().call(new GuiOpenEvent(this));
    }

    default boolean onClose() {
        return getEventManager().call(new GuiCloseEvent(this));
    }

    default boolean onChange(Gui gui) {
        return getEventManager().call(new GuiChangeEvent(this, gui));
    }

    EventManager getEventManager();

    Bounds getBounds();

    default void setBounds(Bounds bounds) {
        throw new UnsupportedOperationException();
    }
}

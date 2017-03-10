package xyz.upperlevel.ulge.gui;

import lombok.Getter;
import org.joml.Vector2f;
import xyz.upperlevel.ulge.gui.events.*;
import xyz.upperlevel.utils.event.EventManager;

public abstract class Gui {
    @Getter
    private boolean hover = false;
    @Getter
    private boolean click = false;

    public abstract void init(GuiRenderer renderer);

    public abstract void draw(GuiRenderer renderer);

    public abstract EventManager getEventManager();



    public boolean onMouseMove(Vector2f lastPos, Vector2f pos) {
        return getEventManager().call(new GuiMouseMoveEvent(this, lastPos, pos));
    }

    public boolean onClickBegin(Vector2f position) {
        if(getEventManager().call(new GuiClickEvent(this, position, GuiClickEvent.Type.BEGIN))) {
            click = true;
            return true;
        } else return false;
    }

    public boolean onClickEnd(Vector2f position) {
        if(getEventManager().call(new GuiClickEvent(this, position, GuiClickEvent.Type.END))) {
            click = false;
            return true;
        } else return false;
    }

    public boolean onMouseEnter(Vector2f enterPos) {
        if(getEventManager().call(new GuiMouseEnterEvent(this, enterPos))) {
            hover = true;
            return true;
        } else return false;
    }

    public boolean onMouseExit(Vector2f lastPos) {
        if(getEventManager().call(new GuiMouseExitEvent(this, lastPos))) {
            hover = false;
            return true;
        } else return false;
    }

    public boolean onOpen() {
        return getEventManager().call(new GuiOpenEvent(this));
    }

    public boolean onClose() {
        return getEventManager().call(new GuiCloseEvent(this));
    }

    public boolean onChange(Gui gui) {
        return getEventManager().call(new GuiChangeEvent(this, gui));
    }

    public boolean onDrag(Vector2f lastPos, Vector2f newPos) {
        return getEventManager().call(new GuiDragEvent(this, lastPos, newPos));
    }
}

package xyz.upperlevel.ulge.gui;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import xyz.upperlevel.ulge.gui.events.*;
import xyz.upperlevel.utils.event.EventManager;
import xyz.upperlevel.utils.event.impl.SimpleEventManager;

public class Gui {

    @Getter
    private boolean clicked = false;

    @Getter
    private boolean hover = false;

    @Getter
    @Setter
    private EventManager eventManager = new SimpleEventManager();

    @Getter
    @Setter
    private double x = 0, y = 0;

    @Getter
    @Setter
    private double width = 0, height = 0;

    public Gui() {
    }

    public boolean isIn(double absX, double absY) {
        return absX >= this.x && absY >= this.y &&
                absX <= width && absY <= height;
    }

    public void render(GuiRenderer renderer) {
        render(new Matrix4f(), renderer);
    }

    public void render(Matrix4f transformation, GuiRenderer renderer) {
        float x = (float) this.x;
        float y = (float) this.y;

        float w = (float) width;
        float h = (float) height;

        transformation.translate(new Vector3f(x, y, 0));
        transformation.scale(new Vector3f(w, h, 1));

        renderer.setTransformation(transformation);
    }

    public boolean onCursorEnter(double x, double y) {
        if (eventManager != null && eventManager.call(new GuiCursorEnterEvent(this, x, y))) {
            hover = true;
            return true;
        }
        return false;
    }

    public boolean onCursorMove(double startX, double startY, double endX, double endY) {
        return eventManager != null &&
                eventManager.call(new GuiCursorMoveEvent(this, startX, startY, endX, endY));
    }

    public boolean onCursorExit(double x, double y) {
        if (eventManager != null && eventManager.call(new GuiCursorExitEvent(this, x, y))) {
            hover = false;
            return true;
        }
        return false;
    }

    public boolean onClickBegin(double x, double y) {
        if (eventManager != null && eventManager.call(new GuiClickBeginEvent(this, x, y))) {
            clicked = false;
            return true;
        }
        return false;
    }

    public boolean onClickEnd(double x, double y) {
        if (eventManager != null && eventManager.call(new GuiClickEndEvent(this, x, y))) {
            clicked = false;
            return true;
        }
        return false;
    }

    public boolean onOpen() {
        return eventManager != null &&
                eventManager.call(new GuiOpenEvent(this));
    }

    public boolean onClose() {
        return eventManager != null &&
                eventManager.call(new GuiCloseEvent(this));
    }

    public boolean onChange(Gui gui) {
        return false;
    }

    public boolean onDrag(double startX, double startY, double endX, double endY) {
        return eventManager != null &&
                eventManager.call(new GuiDragEvent(this, startX, startY, endX, endY));
    }
}
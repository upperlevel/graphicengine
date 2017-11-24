package xyz.upperlevel.ulge.gui;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import xyz.upperlevel.event.EventManager;
import xyz.upperlevel.ulge.gui.events.*;

@NoArgsConstructor
public class Gui {

    @Getter
    private boolean clicked = false;

    @Getter
    private boolean hover = false;

    @Getter
    @Setter
    private EventManager eventManager = new EventManager();

    @Getter
    @Setter
    private double x = 0, y = 0;

    @Getter
    @Setter
    private double width = 1.0, height = 1.0;

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setSize(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public boolean isIn(double x, double y) {
        return x >= this.x && x <= this.x + width &&
               y >= this.y && y <= this.y + height;
    }

    public void render(Matrix4f transformation, GuiRenderer renderer) {
        float x = (float) this.x;
        float y = (float) this.y;

        float w = (float) width;
        float h = (float) height;

        // translate to pos
        transformation.translate(new Vector3f(x * 2, -y * 2, 0));

        // scale and fix scale issues
        transformation.translate(new Vector3f(-1 + w, 1 - h, 0));
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
        return eventManager != null && eventManager.call(new GuiCursorMoveEvent(this, startX, startY, endX, endY));
    }

    public boolean onCursorExit(double x, double y) {
        if (eventManager != null && eventManager.call(new GuiCursorExitEvent(this, x, y))) {
            hover = false;
            clicked = false;
            return true;
        }
        return false;
    }

    public boolean onClickBegin(double x, double y) {
        if (eventManager != null && eventManager.call(new GuiClickBeginEvent(this, x, y))) {
            clicked = true;
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
        return eventManager != null && eventManager.call(new GuiOpenEvent(this));
    }

    public boolean onClose() {
        return eventManager != null && eventManager.call(new GuiCloseEvent(this));
    }

    public boolean onChange(Gui gui) {
        return false;
    }

    public boolean onDrag(double startX, double startY, double endX, double endY) {
        return eventManager != null && eventManager.call(new GuiDragEvent(this, startX, startY, endX, endY));
    }
}
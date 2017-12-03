package xyz.upperlevel.ulge.gui;

import lombok.Getter;
import lombok.Setter;
import xyz.upperlevel.event.EventManager;
import xyz.upperlevel.ulge.gui.events.*;
import xyz.upperlevel.ulge.window.Window;
import xyz.upperlevel.ulge.window.event.button.MouseButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Gui {
    @Getter
    @Setter
    private Gui parent;
    @Getter
    private Window window;
    @Getter
    @Setter
    private int width, height;
    @Getter
    @Setter
    private int offsetLeft, offsetRight, offsetTop, offsetBottom;
    @Getter
    @Setter
    private GuiAlign align = GuiAlign.CENTER;

    private List<Gui> children = new ArrayList<>();

    // LAYOUT DEPENDANT VARS

    @Getter
    private int realX, realY, relX, relY;

    @Getter
    private GuiBounds bounds;

    // END LAYOUT DEPENDANT VARS

    private boolean hovered, clicked;

    private EventManager eventHandler;

    @Getter
    @Setter
    private GuiBackground background = GuiBackground.TRANSPARENT;

    public Gui(Window window, EventManager handler) {
        this.eventHandler = handler;
        if (window != null) {
            setWindow(window);
            reloadLayout();
        }
    }

    public Gui(Window window) {
        this(window, new EventManager());
    }

    public Gui () {
        this(null, new EventManager());
    }

    public void setOffset(int left, int right, int top, int bottom) {
        offsetLeft = left;
        offsetRight = right;
        offsetTop = top;
        offsetBottom = bottom;
    }

    public void setOffset(int vertical, int horizontal) {
        setOffset(horizontal, horizontal, vertical, vertical);
    }

    public void setOffset(int offset) {
        setOffset(offset, offset, offset, offset);
    }

    /**
     * Sets the dimension of the gui
     * @param w the new width
     * @param h the new height
     */
    public void setSize(int w, int h) {
        this.width = w;
        this.height = h;
    }

    /**
     * Checks if a point is inside of the Gui using the parent's reference
     * @param x the point's x coordinate
     * @param y the point's y coordinate
     * @return true only if the point is inside of the Gui, false otherwise
     */
    public boolean isInside(double x, double y) {
        return  x >= relX && x <= relX + width &&
                y >= relY && y <= relY + height;
    }

    public void reloadLayout() {
        int parWidth, parHeight, parX, parY;
        if (parent != null) {
            parX = parent.realX;
            parY = parent.realY;
            parWidth = parent.width;
            parHeight = parent.height;
            setWindow(parent.window);
        } else {
            parX = 0;
            parY = 0;
            parWidth = window.getWidth();
            parHeight = window.getHeight();
        }

        { // horizontal layout
            switch (align.getHorizontal()) {
                case 0: // Left
                    realX = parX + offsetLeft;
                    break;
                case 1: // Center
                    realX = parX + (parWidth - (offsetLeft + width + offsetRight)) / 2;
                    break;
                case 2: // Right
                    realX = parX + parWidth - (width + offsetRight);
                    break;
            }
        }

        { // vertical layout
            switch (align.getVertical()) {
                case 0: // Left
                    realY = parY + offsetTop;
                    break;
                case 1: // Center
                    realY = parY + (parHeight - (offsetTop + height + offsetBottom)) / 2;
                    break;
                case 2: // Right
                    realY = parY + parHeight - (height + offsetBottom);
                    break;
            }
        }

        relX = realX - parX;
        relY = realY - parY;

        bounds = new GuiBounds(realX, realY, realX + width, realY + height);

        children.forEach(Gui::reloadLayout);
    }

    protected void setWindow(Window window) {
        this.window = window;
    }

    /**
     * returns true if the mouse is currently inside of the gui (even if it's clicked)
     * @return true if hovered
     */
    public boolean isHovered() {
        return hovered;
    }

    /**
     * returns true if the Gui is being clicked by the mouse
     * @return true if clicked
     */
    public boolean isClicked() {
        return clicked;
    }

    public List<Gui> getChildren() {
        return Collections.unmodifiableList(children);
    }

    /**
     * Returns true only if the container doesn't contain any child
     * @return true if no child is found
     */
    public boolean isEmpty() {
        return children.isEmpty();
    }

    public void addChild(Gui child) {
        children.add(child);
        child.setParent(this);
    }

    public boolean removeChild(Gui child) {
        if (children.remove(child)) {
            child.setParent(null);
            return true;
        } else return false;
    }

    /**
     * Gets the {@link EventManager}
     * @return the {@link EventManager}
     */
    public EventManager getEventManager() {
        return eventHandler;
    }

    /**
     * Called when the cursor enters the Gui
     * @param x the entry x coordinate
     * @param y the entry y coordinate
     */
    public void onCursorEnter(double x, double y) {
        for (Gui child : children) {
            if (child.isInside(x, y)) {
                child.onCursorEnter(x - child.relX, y - child.relY);
            }
        }
        getEventManager().call(new GuiCursorEnterEvent(this, x, y));
        hovered = true;
    }

    /**
     * Called when the cursor exits the Gui
     * @param x the last x coordinate
     * @param y the last y coordinate
     */
    public void onCursorExit(double x, double y) {
        for (Gui child : children) {
            if (child.isHovered()) {
                child.onCursorExit(x - child.relX, y - child.relY);
            }
        }
        getEventManager().call(new GuiCursorExitEvent(this, x, y));
        hovered = false;
        clicked = false;
    }

    /**
     * Called when the cursor moves
     * @param startX the x of the starting point
     * @param startY the y of the starting point
     * @param endX the x of the ending point
     * @param endY the y of the ending point
     */
    public void onCursorMove(double startX, double startY, double endX, double endY) {
        for (Gui child : children) {

            // Check if mouse was or is inside of the handle
            boolean wasInside = child.isHovered();
            boolean isInside = child.isInside(endX, endY);

            // No exit nor enter, always outside
            if (!wasInside && !isInside) continue;

            if (wasInside && isInside) {
                // Mouse was and still is inside, just a move
                child.onCursorMove(
                        startX - child.relX, startY - child.relY,
                        endX - child.relX, endY - child.relY
                );
            } else if (isInside) {
                // Mouse was outside and came inside (enter)
                child.onCursorEnter(endX - child.relX, endY - child.relY);
            } else {
                // Mouse was inside and went outside (exit)
                child.onCursorExit(startX - child.relX, startY - child.relY);
            }
        }
        eventHandler.call(new GuiCursorMoveEvent(this, startX, startY, endX, endY));
    }

    /**
     * Called when the cursor begins a click (button pressed is specified in MouseButton)
     * @param x the x of the cursor
     * @param y the y of the cursor
     * @param button the pressed button
     */
    public void onClickBegin(double x, double y, MouseButton button) {
        for (Gui child : children) {
            if (child.isInside(x, y)) {
                child.onClickBegin(x - child.relX, y - child.relY, button);
            }
        }
        eventHandler.call(new GuiClickBeginEvent(this, x, y, button));
        if (button == MouseButton.LEFT) {
            clicked = true;
        }
    }

    /**
     * Called when the cursor ends a click (button pressed is specified in MouseButton)
     * @param x the x of the cursor
     * @param y the y of the cursor
     * @param button the pressed button
     */
    public void onClickEnd(double x, double y, MouseButton button) {
        for (Gui child : children) {
            if (child.isHovered()) {
                child.onClickEnd(x - child.relX, y - child.relY, button);
            }
        }
        eventHandler.call(new GuiClickEndEvent(this, x, y, button));
        if (button == MouseButton.LEFT) {
            clicked = false;
        }
    }

    /**
     * Called when the gui is opened
     */
    public void onOpen() {
        eventHandler.call(new GuiOpenEvent(this));
        children.forEach(Gui::onOpen);
    }

    /**
     * Called when the gui is closed
     */
    public void onClose() {
        children.forEach(Gui::onClose);
        eventHandler.call(new GuiCloseEvent(this));
    }

    /**
     * Called when the window is resized (before the layout call)
     */
    public void onResize() {
        for (Gui child : children) {
            child.onResize();
        }
    }

    /**
     * Renders the gui
     */
    public void render() {
        renderCurrent();
        renderChildren();
    }

    protected void renderCurrent() {
        background.render(window, bounds);
    }

    protected void renderChildren() {
        for (Gui child : children) {
            child.render();
        }
    }
}
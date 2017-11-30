package xyz.upperlevel.ulge.gui;

import xyz.upperlevel.event.EventManager;
import xyz.upperlevel.ulge.gui.events.*;
import xyz.upperlevel.ulge.window.event.button.MouseButton;

public interface Gui {

    /**
     * Gets the x coordinate of this gui (top-left convention)
     * @return the x coordinate
     */
    double getX();

    /**
     * Gets the y coordinate of this gui (top-left convention)
     * @return the y coordinate
     */
    double getY();

    /**
     * Gets the width of the gui
     * <br>If the gui isn't box-shaped returns the width of the box that approximates the shape
     * @return the width
     */
    double getW();

    /**
     * Gets the height of the gui
     * <br>If the gui isn't box-shaped returns the height of the box that approximates the shape
     * @return the height
     */
    double getH();

    /**
     * Sets the x coordinate of this gui (top-left convention)
     * @param x the new x coordinate
     */
    void setX(double x);

    /**
     * Sets the y coordinate of this gui (top-left convention)
     * @param y the new y coordinate
     */
    void setY(double y);

    /**
     * Sets the width of the gui
     * @param w the new width
     */
    void setW(double w);

    /**
     * Sets the height of the gui
     * @param h the new height
     */
    void setH(double h);

    /**
     * Sets both position and size of the gui
     * @param x the new x coordinate
     * @param y the new y coordinate
     * @param w the new width
     * @param h the new height
     */
    default void setBounds(double x, double y, double w, double h) {
        setX(x);
        setY(y);
        setW(w);
        setH(h);
    }

    default void setBounds(GuiBounds bounds) {
        setBounds(bounds.minX, bounds.minY, bounds.maxX + bounds.minX, bounds.maxY - bounds.minY);
    }

    default GuiBounds getBounds() {
        return new GuiBounds(getX(), getY(), getX() + getW(), getY() + getH());
    }

    /**
     * Sets the position of the gui
     * @param x the new x coordinate
     * @param y the new y coordinate
     */
    default void setPosition(double x, double y) {
        setX(x);
        setY(y);
    }

    /**
     * Sets the dimension of the gui
     * @param w the new width
     * @param h the new height
     */
    default void setSize(double w, double h) {
        setW(w);
        setH(h);
    }

    /**
     * Checks if a point is inside of the Gui using the parent's reference
     * @param x the point's x coordinate
     * @param y the point's y coordinate
     * @return true only if the point is inside of the Gui, false otherwise
     */
    default boolean isInside(double x, double y) {
        return  x >= getX() && x <= getX() + getW() &&
                y >= getY() && y <= getY() + getH();
    }

    /**
     * returns true if the mouse is currently inside of the gui (even if it's clicked)
     * @return true if hovered
     */
    boolean isHovered();

    /**
     * returns true if the Gui is being clicked by the mouse
     * @return true if clicked
     */
    boolean isClicked();

    /**
     * Gets the {@link EventManager}
     * @return the {@link EventManager}
     */
    EventManager getEventManager();

    /**
     * Called when the cursor enters the Gui
     * @param x the entry x coordinate
     * @param y the entry y coordinate
     */
    default void onCursorEnter(double x, double y) {
        getEventManager().call(new GuiCursorEnterEvent(this, x, y));
    }

    /**
     * Called when the cursor exits the Gui
     * @param x the last x coordinate
     * @param y the last y coordinate
     */
    default void onCursorExit(double x, double y) {
        getEventManager().call(new GuiCursorExitEvent(this, x, y));
    }

    /**
     * Called when the cursor moves
     * @param startX the x of the starting point
     * @param startY the y of the starting point
     * @param endX the x of the ending point
     * @param endY the y of the ending point
     */
    default void onCursorMove(double startX, double startY, double endX, double endY) {
        getEventManager().call(new GuiCursorMoveEvent(this, startX, startY, endX, endY));
    }

    /**
     * Called when the cursor begins a click (button pressed is specified in MouseButton)
     * @param x the x of the cursor
     * @param y the y of the cursor
     * @param button the pressed button
     */
    default void onClickBegin(double x, double y, MouseButton button) {
        getEventManager().call(new GuiClickBeginEvent(this, x, y, button));
    }

    /**
     * Called when the cursor ends a click (button pressed is specified in MouseButton)
     * @param x the x of the cursor
     * @param y the y of the cursor
     * @param button the pressed button
     */
    default void onClickEnd(double x, double y, MouseButton button) {
        getEventManager().call(new GuiClickEndEvent(this, x, y, button));
    }

    /**
     * Called when the gui is opened
     */
    default void onOpen() {
        getEventManager().call(new GuiOpenEvent(this));
    }

    /**
     * Called when the gui is closed
     */
    default void onClose() {
        getEventManager().call(new GuiCloseEvent(this));
    }

    /**
     * Renders the gui using the normalized bounds of the upper window (need to relativize them)
     * <br>Example: if this Gui has [0, 0, .5, .5] of xywh and has no parents then upperBounds = [0, 0, 1, 1]
     * <br>to normalize use <pre>{@code GuiBounds bounds = upperBounds.insideRelative(getBounds()); }</pre>
     * @param upperBounds the normalized gui bounds of the parennt window relative to the screen
     */
    void render(GuiBounds upperBounds);
}
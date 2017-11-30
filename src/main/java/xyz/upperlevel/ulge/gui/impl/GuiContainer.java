package xyz.upperlevel.ulge.gui.impl;

import lombok.Getter;
import xyz.upperlevel.ulge.gui.BaseGui;
import xyz.upperlevel.ulge.gui.Gui;
import xyz.upperlevel.ulge.gui.GuiBounds;
import xyz.upperlevel.ulge.gui.GuiRenderer;
import xyz.upperlevel.ulge.opengl.texture.Texture2d;
import xyz.upperlevel.ulge.util.Color;
import xyz.upperlevel.ulge.window.event.button.MouseButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class used to group some child Guis
 * <br>This can also have some color and texture to use as background (see {@link #setColor(Color)} and {@link #setTexture(Texture2d)})
 */
public class GuiContainer extends BaseGui {
    private List<Gui> handles;
    @Getter
    private Color color = Color.TRANSPARENT;
    @Getter
    private Texture2d texture = Texture2d.NULL;

    public GuiContainer(List<Gui> handles) {
        this.handles = handles;
    }

    public GuiContainer() {
        this(new ArrayList<>());
    }

    /**
     * Returns true only if the container doesn't contain any child
     * @return true if no child is found
     */
    public boolean isEmpty() {
        return handles.isEmpty();
    }

    /**
     * Returns an unmodifiable view of all the gui's children
     * @return the gui's children
     */
    public List<Gui> getChildren() {
        return Collections.unmodifiableList(handles);
    }

    /**
     * Sets the background's color
     * @param color the background color
     */
    public void setColor(Color color) {
        this.color = color == null ? Color.TRANSPARENT : color;
    }

    /**
     * Sets the background's texture
     * @param texture the background texture
     */
    public void setTexture(Texture2d texture) {
        this.texture = texture == null ? Texture2d.NULL : texture;
    }

    /**
     * Adds a child to handle
     * @param gui the new child
     */
    public void add(Gui gui) {
        handles.add(gui);
    }

    /**
     * Removes a child (if present) and returns true on success
     * @param gui the child to remove
     * @return true only if the operation succeed
     */
    public boolean remove(Gui gui) {
        return handles.remove(gui);
    }

    @Override
    public void onCursorEnter(double x, double y) {
        for (Gui handle : handles) {
            if (handle.isInside(x, y)) {
                handle.onCursorEnter(x - handle.getX(), y - handle.getY());
            }
        }
        super.onCursorEnter(x, y);
    }

    @Override
    public void onCursorExit(double x, double y) {
        for (Gui handle : handles) {
            if (handle.isHovered()) {
                handle.onCursorExit(x - handle.getX(), y - handle.getY());
            }
        }
        super.onCursorExit(x, y);
    }

    @Override
    public void onCursorMove(double startX, double startY, double endX, double endY) {
        for (Gui handle : handles) {

            // Check if mouse was or is inside of the handle
            boolean wasInside = handle.isHovered();
            boolean isInside = handle.isInside(endX, endY);

            // No exit nor enter, always outside
            if (!wasInside && !isInside) continue;

            if (wasInside && isInside) {
                // Mouse was and still is inside, just a move
                handle.onCursorMove(
                        startX - handle.getX(), startY - handle.getY(),
                        endX - handle.getX(), endY - handle.getY()
                );
            } else if (isInside) {
                // Mouse was outside and came inside (enter)
                handle.onCursorEnter(endX - handle.getX(), endY - handle.getY());
            } else {
                // Mouse was inside and went outside (exit)
                handle.onCursorExit(startX - handle.getX(), startY - handle.getY());
            }
        }
        super.onCursorMove(startX, startY, endX, endY);
    }

    @Override
    public void onClickBegin(double x, double y, MouseButton button) {
        for (Gui handle : handles) {
            if (handle.isInside(x, y)) {
                handle.onClickBegin(x - handle.getX(), y - handle.getY(), button);
            }
        }
        super.onClickBegin(x, y, button);
    }

    @Override
    public void onClickEnd(double x, double y, MouseButton button) {
        for (Gui handle : handles) {
            if (handle.isHovered()) {
                handle.onClickEnd(x - handle.getX(), y - handle.getY(), button);
            }
        }
        super.onClickEnd(x, y, button);
    }

    @Override
    public void onOpen() {
        super.onOpen();
        handles.forEach(Gui::onOpen);
    }

    @Override
    public void onClose() {
        handles.forEach(Gui::onClose);
        super.onClose();
    }

    @Override
    public void render(GuiBounds upperBounds) {
        GuiBounds bounds = upperBounds.insideRelative(getBounds());
        if (color.a != 0 || texture != Texture2d.NULL) {
            GuiRenderer renderer = GuiRenderer.get();
            // Both texture and color needs to be replaced even if transparent
            // or another one might be used
            renderer.setColor(color);
            renderer.setTexture(texture);
            renderer.render(bounds);
        }
        for (Gui handle : handles) {
            handle.render(bounds);
        }
    }
}

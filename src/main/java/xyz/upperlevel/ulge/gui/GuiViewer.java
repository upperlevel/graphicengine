package xyz.upperlevel.ulge.gui;

import lombok.Getter;
import org.joml.Vector2f;
import xyz.upperlevel.event.EventHandler;
import xyz.upperlevel.event.Listener;
import xyz.upperlevel.ulge.gui.events.GuiContainer;
import xyz.upperlevel.ulge.window.Window;
import xyz.upperlevel.ulge.window.event.*;
import xyz.upperlevel.ulge.window.event.action.Action;

import java.util.List;

/**
 * Used to wrap a Gui to simpify it's usage
 * <br>This class manages the input from the window and the Gui opening/closing
 * <br>use {@link #open(Gui)}, {@link #close()} and {@link #render()} for some basic usage
 */
public class GuiViewer implements Listener {
    @Getter
    private GuiContainer handle;
    private double lastMouseX, lastMouseY;

    /**
     * Creates the viewer and sets up the hooks for the user interaction
     * @param window the windows used to interact
     */
    public GuiViewer(Window window) {
        handle = new GuiContainer(window);
        handle.setOffset(0,0);
        handle.setSize(window.getWidth(), window.getHeight());
        handle.reloadLayout();
        handle.onOpen();
        window.getEventManager().register(this);
        Vector2f pos = window.getCursorPosition();
        lastMouseX = pos.x;
        lastMouseY = pos.y;
    }

    /**
     * Opens the Gui and, if necessary, closes the old one
     * @param gui
     */
    public void open(Gui gui) {
        if (!handle.isEmpty()) {
            close();
        }
        gui.onOpen();
        handle.addChild(gui);
    }

    /**
     * Returns the currently open Gui (if any is present)
     * @return the currently open Gui or null
     */
    public Gui getCurrent() {
        List<Gui> children = handle.getChildren();
        return children.isEmpty() ? null : children.get(0);
    }

    /**
     * Closes the currently open Gui, if any is out
     * <br>If no Gui is found it does nothing
     */
    public void close() {
        Gui gui = getCurrent();
        if (gui != null) {
            gui.onClose();
            handle.removeChild(gui);
        }
    }

    /**
     * Renders the Gui using the whole screen
     */
    public void render() {
        handle.render();
    }

    @EventHandler
    public void onClickBegin(MouseButtonChangeEvent event) {
        Window window = event.getWindow();
        Vector2f pos = window.getCursorPosition();
        pos.mul(window.getWidth(), window.getHeight());
        if (event.getAction() == Action.PRESS) {
            handle.onClickBegin(pos.x, pos.y, event.getButton());
        } else { // action == RELEASE
            handle.onClickEnd(pos.x, pos.y, event.getButton());
        }
    }

    @EventHandler
    public void onCursorEnter(CursorEnterEvent event) {
        Window window = event.getWindow();
        Vector2f pos = window.getCursorPosition();
        pos.mul(window.getWidth(), window.getHeight());
        handle.onCursorEnter(pos.x, pos.y);
    }

    @EventHandler
    public void onCursorExit(CursorExitEvent event) {
        Window window = event.getWindow();
        Vector2f pos = window.getCursorPosition();
        pos.mul(window.getWidth(), window.getHeight());
        handle.onCursorExit(pos.x, pos.y);
    }

    @EventHandler
    public void onCursorMove(CursorMoveEvent event) {
        double mouseX = event.getX();
        double mouseY = event.getY();
        handle.onCursorMove(lastMouseX, lastMouseY, mouseX, mouseY);
        lastMouseX = mouseX;
        lastMouseY = mouseY;
    }

    @EventHandler
    public void onResize(ResizeEvent event) {
        handle.onResize();
        handle.reloadLayout();
    }
}

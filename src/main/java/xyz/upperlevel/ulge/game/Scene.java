package xyz.upperlevel.ulge.game;

import xyz.upperlevel.ulge.window.event.action.Action;
import xyz.upperlevel.ulge.window.event.button.MouseButton;
import xyz.upperlevel.ulge.window.event.key.Key;

public class Scene {

    public Scene() {
    }

    //<editor-fold desc="lifecycle events">
    public final void tick() {
        onTick();
    }

    protected void onTick() {
    }

    public final void fps() {
        onFps();
    }

    protected void onFps() {
    }

    public final void render() {
        onRender();
    }

    protected void onRender() {
    }
    //</editor-fold>

    //<editor-fold desc="window events">
    public final void mouseButtonChange(MouseButton button, Action action) {
        onMouseButtonChange(button, action);
    }

    protected void onMouseButtonChange(MouseButton button, Action action) {
    }

    public final void keyChange(Key key, Action action) {
        onKeyChange(key, action);
    }

    protected void onKeyChange(Key key, Action action) {
    }

    public final void mouseScroll(double x, double y) {
        onMouseScroll(x, y);
    }

    protected void onMouseScroll(double x, double y) {
    }

    public final void cursorMove(double x , double y) {
        onCursorMove(x, y);
    }

    protected void onCursorMove(double x, double y) {
    }
    //</editor-fold>
}

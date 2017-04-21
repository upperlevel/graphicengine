package xyz.upperlevel.ulge.game;

import lombok.Getter;
import xyz.upperlevel.ulge.window.event.action.Action;
import xyz.upperlevel.ulge.window.event.button.MouseButton;
import xyz.upperlevel.ulge.window.event.key.Key;

public class Stage implements Scene {

    private Scene staged = null;

    public Stage() {
    }

    public Stage(Scene start) {
        stage(start);
    }

    public Scene staged() {
        return staged;
    }

    public void stage(Scene scene) {
        Scene old = staged;
        staged = scene;
        if (old != null)
            old.onDisable(scene);
        if (scene != null)
            scene.onEnable(old);
    }

    public void clear() {
        stage(null);
    }

    //<editor-fold desc="Enable/disable events">
    protected void onPreEnable(Scene prev) {
    }

    protected void onPostEnable(Scene prev) {
    }

    @Override
    public void onEnable(Scene prev) {
        onPreEnable(prev);
        if (staged != null)
            staged.onEnable(prev);
        onPostEnable(prev);
    }

    protected void onPreDisable(Scene next) {
    }

    protected void onPostDisable(Scene next) {
    }

    @Override
    public void onDisable(Scene next) {
        onPreDisable(next);
        if (staged != null)
            staged.onDisable(next);
        clear();
        onPostDisable(next);
    }
    //</editor-fold>

    //<editor-fold desc="Lifecycle events">
    protected void onPreTick() {
    }

    protected void onPostTick() {
    }

    @Override
    public void onTick() {
        onPreTick();
        if (staged != null)
            staged.onTick();
        onPostTick();
    }

    @Override
    public void onFps() {
        if (staged != null)
            staged.onFps();
    }

    protected void onPreRender() {
    }

    protected void onPostRender() {
    }

    @Override
    public void onRender() {
        onPreRender();
        if (staged != null)
            staged.onRender();
        onPostRender();
    }
    //</editor-fold>

    //<editor-fold desc="Window events">
    @Override
    public void onMouseButtonChange(MouseButton button, Action action) {
        if (staged != null)
            staged.onMouseButtonChange(button, action);
    }

    @Override
    public void onKeyChange(Key key, Action action) {
        if (staged != null)
            staged.onKeyChange(key, action);
    }

    @Override
    public void onMouseScroll(double x, double y) {
        if (staged != null)
            staged.onMouseScroll(x, y);
    }

    @Override
    public void onCursorMove(double x, double y) {
        if (staged != null)
            staged.onCursorMove(x, y);
    }
    //</editor-fold>
}

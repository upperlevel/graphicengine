package xyz.upperlevel.ulge.game;

import xyz.upperlevel.ulge.window.event.action.Action;
import xyz.upperlevel.ulge.window.event.button.MouseButton;
import xyz.upperlevel.ulge.window.event.key.Key;

public interface Scene {

    //<editor-fold desc="enable/disable events">
    default void onEnable(Scene previous) {}

    default void onDisable(Scene next) {}
    //</editor-fold>

    //<editor-fold desc="lifecycle events">
    default void onTick() {}

    default void onFps() {}

    default void onRender() {}
    //</editor-fold>

    //<editor-fold desc="window events">
    default void onMouseButtonChange(MouseButton button, Action action) {}

    default void onKeyChange(Key key, Action action) {}

    default void onMouseScroll(double x, double y) {}

    default void onCursorMove(double x, double y) {}
    //</editor-fold>
}

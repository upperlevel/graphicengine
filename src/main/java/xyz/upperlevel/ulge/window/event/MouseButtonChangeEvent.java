package xyz.upperlevel.ulge.window.event;

import xyz.upperlevel.ulge.window.Window;
import xyz.upperlevel.ulge.window.event.button.MouseButton;
import xyz.upperlevel.ulge.window.event.action.Action;

@FunctionalInterface
public interface MouseButtonChangeEvent extends WindowEvent {

    void onCall(Window window, MouseButton button, Action action);
}
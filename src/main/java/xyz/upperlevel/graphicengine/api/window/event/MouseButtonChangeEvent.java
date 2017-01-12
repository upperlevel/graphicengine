package xyz.upperlevel.graphicengine.api.window.event;

import xyz.upperlevel.graphicengine.api.window.Window;
import xyz.upperlevel.graphicengine.api.window.event.button.MouseButton;
import xyz.upperlevel.graphicengine.api.window.event.action.Action;

@FunctionalInterface
public interface MouseButtonChangeEvent extends WindowEvent {

    void onCall(Window window, MouseButton button, Action action);
}
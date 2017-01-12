package xyz.upperlevel.graphicengine.api.window.event;

import xyz.upperlevel.graphicengine.api.window.Window;
import xyz.upperlevel.graphicengine.api.window.event.action.Action;
import xyz.upperlevel.graphicengine.api.window.event.key.Key;

@FunctionalInterface
public interface KeyChangeEvent extends WindowEvent {

    void onCall(Window window, Key key, Action action);
}

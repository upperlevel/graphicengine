package xyz.upperlevel.ulge.window.event;

import xyz.upperlevel.ulge.window.Window;
import xyz.upperlevel.ulge.window.event.action.Action;
import xyz.upperlevel.ulge.window.event.key.Key;

@FunctionalInterface
public interface KeyChangeEvent extends WindowEvent {

    void onCall(Window window, Key key, Action action);
}

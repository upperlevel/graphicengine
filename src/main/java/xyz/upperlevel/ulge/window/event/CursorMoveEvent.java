package xyz.upperlevel.ulge.window.event;

import xyz.upperlevel.ulge.window.Window;

@FunctionalInterface
public interface CursorMoveEvent extends WindowEvent {

    void onCall(Window window, double x, double y);
}
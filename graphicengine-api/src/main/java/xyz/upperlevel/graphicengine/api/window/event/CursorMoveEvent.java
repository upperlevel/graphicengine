package xyz.upperlevel.graphicengine.api.window.event;

import xyz.upperlevel.graphicengine.api.window.Window;

@FunctionalInterface
public interface CursorMoveEvent extends WindowEvent {

    void onCall(Window window, double x, double y);
}
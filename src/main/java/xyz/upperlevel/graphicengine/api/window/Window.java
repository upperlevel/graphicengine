package xyz.upperlevel.graphicengine.api.window;

import xyz.upperlevel.graphicengine.api.window.event.WindowEventHandler;
import xyz.upperlevel.graphicengine.api.window.event.action.Action;
import xyz.upperlevel.graphicengine.api.window.event.key.Key;
import xyz.upperlevel.graphicengine.api.util.math.Vec2;

public interface Window {

    long getId();

    String getTitle();

    void setTitle(String title);

    void centerPosition();

    int getWidth();

    void setWidth(int width);

    int getHeight();

    void setHeight(int height);

    void setSize(int width, int height);

    Vec2 getPosition();

    void setPosition(int x, int y);

    Action getKeyState(Key key);

    Vec2 getCursorPosition();

    void setCursorPosition(double x, double y);

    boolean isCursorShowed();

    void showCursor();

    boolean isCursorHidden();

    void hideCursor();

    boolean isCursorDisabled();

    void disableCursor();

    void iconify();

    void maximize();

    void show();

    void hide();

    boolean isClosed();

    void close();

    void setVSync(boolean vSync);

    void update();

    default void registerEventHandler(WindowEventHandler eventHandler) {
        eventHandler.apply(this);
    }

    void contextualize();

    void destroy();
}

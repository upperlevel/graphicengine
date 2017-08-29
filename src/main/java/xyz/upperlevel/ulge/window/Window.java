package xyz.upperlevel.ulge.window;

import org.joml.Vector2f;
import xyz.upperlevel.event.EventManager;
import xyz.upperlevel.ulge.window.event.button.MouseButton;
import xyz.upperlevel.ulge.window.event.key.Key;

public interface Window {

    EventManager getEventManager();

    long getId();

    String getTitle();

    void setTitle(String title);

    void centerPosition();

    int getWidth();

    void setWidth(int width);

    int getHeight();

    void setHeight(int height);

    void setSize(int width, int height);

    Vector2f getPosition();

    void setPosition(int x, int y);

    boolean getKey(Key key);

    Vector2f getCursorPosition();

    void setCursorPosition(double x, double y);

    boolean isCursorShowed();

    void showCursor();

    boolean isCursorHidden();

    void hideCursor();

    boolean isCursorDisabled();

    void disableCursor();

    boolean getMouseButton(MouseButton button);

    void iconify();

    void maximize();

    void show();

    void hide();

    boolean isClosed();

    void close();

    void setVSync(boolean vSync);

    void update();

    void contextualize();

    void destroy();
}

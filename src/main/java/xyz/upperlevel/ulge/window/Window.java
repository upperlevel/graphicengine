package xyz.upperlevel.ulge.window;

import org.joml.Vector2d;
import org.joml.Vector2f;
import org.joml.Vector2i;
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

    boolean testKey(Key key);

    Vector2d getCursorPosition();

    void setCursorPosition(double x, double y);

    boolean isCursorShowed();

    void showCursor();

    boolean isCursorHidden();

    void hideCursor();

    boolean isCursorEnabled();

    void setCursorEnabled(boolean enabled);

    boolean testMouseButton(MouseButton button);

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

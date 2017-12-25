package xyz.upperlevel.ulge.window;

import lombok.Getter;
import lombok.NonNull;
import org.joml.Vector2d;
import org.joml.Vector2f;
import org.joml.Vector2i;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL11;
import xyz.upperlevel.event.EventManager;
import xyz.upperlevel.ulge.window.event.*;
import xyz.upperlevel.ulge.window.event.action.Action;
import xyz.upperlevel.ulge.window.event.button.GLFWMouseButton;
import xyz.upperlevel.ulge.window.event.button.MouseButton;
import xyz.upperlevel.ulge.window.event.key.GLFWKey;
import xyz.upperlevel.ulge.window.event.key.Key;

import java.io.File;
import java.nio.DoubleBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class GlfwWindow extends GlWindow {

    @Getter
    private final long id;

    @Getter
    @NonNull
    private String title;

    // Cache width & height
    // A lot of code is reliant on those and it would be expensive to do a JNI call at every get
    @Getter
    private int width, height;

    @Getter
    private EventManager eventManager = new EventManager();

    public GlfwWindow(int width, int height, String title, boolean fullscreen) {
        if (width < 0 || height < 0)
            throw new IllegalArgumentException("Window's dimensions cannot be negative.");
        if (title == null)
            throw new IllegalArgumentException("Title cannot be null.");
        this.title = title;

        if (fullscreen) {
            GLFWVidMode mode = glfwGetVideoMode(glfwGetPrimaryMonitor());
            width = mode.width();
            height = mode.height();
        }
        this.width = width;
        this.height = height;

        id = glfwCreateWindow(width, height, title, fullscreen ? glfwGetPrimaryMonitor() : NULL, NULL);
        setupHooks();
    }

    protected void setupHooks() {
        GLFW.glfwSetKeyCallback(id, new GLFWKeyCallback() {
            @Override
            public void invoke(long id, int key, int scanCode, int action, int mods) {
                final Key stKey = GLFWKey.toStandard(key);
                final Action stAction = action != GLFW.GLFW_RELEASE ? Action.PRESS : Action.RELEASE;
                eventManager.call(new KeyChangeEvent(GlfwWindow.this, stKey, stAction));
            }
        });

        GLFW.glfwSetCursorPosCallback(id, new GLFWCursorPosCallback() {
            @Override
            public void invoke(long id, double x, double y) {
                eventManager.call(new CursorMoveEvent(GlfwWindow.this, x, y));
            }
        });

        GLFW.glfwSetWindowSizeCallback(id, new GLFWWindowSizeCallback() {
            @Override
            public void invoke(long window, int width, int height) {
                // Reset cache
                GlfwWindow.this.width = width;
                GlfwWindow.this.height = height;

                // Idk
                GL11.glViewport(0, 0, width, height);

                eventManager.call(new ResizeEvent(GlfwWindow.this, width, height));
            }
        });

        GLFW.glfwSetMouseButtonCallback(id, new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long id, int button, int action, int mode) {
                final Action stAction = action != GLFW.GLFW_RELEASE ? Action.PRESS : Action.RELEASE;
                final MouseButton stButton = GLFWMouseButton.toStandard(button);
                eventManager.call(new MouseButtonChangeEvent(GlfwWindow.this, stButton, stAction));
            }
        });

        GLFW.glfwSetScrollCallback(id, new GLFWScrollCallback() {
            @Override
            public void invoke(long id, double x, double y) {
                eventManager.call(new MouseScrollEvent(GlfwWindow.this, x, y));
            }
        });

        GLFW.glfwSetCursorEnterCallback(id, new GLFWCursorEnterCallback() {
            @Override
            public void invoke(long id, boolean entered) {
                eventManager.call(entered ? new CursorEnterEvent(GlfwWindow.this) : new CursorExitEvent(GlfwWindow.this));
            }
        });

        GLFW.glfwSetCharCallback(id, new GLFWCharCallback() {
            @Override
            public void invoke(long id, int ch) {
                eventManager.call(new TextEvent(GlfwWindow.this, (char) ch));
            }
        });

        GLFW.glfwSetDropCallback(id, new GLFWDropCallback() {
            @Override
            public void invoke(long id, int count, long rawNames) {
                File[] names = new File[count];
                for(int i = 0; i < count; i++)
                    names[i] = new File(getName(rawNames, i));
                eventManager.call(new FileDropEvent(GlfwWindow.this, names));
            }
        });
    }

    public void reloadSize() {
        IntBuffer widthBuffer = BufferUtils.createIntBuffer(1);
        IntBuffer heightBuffer = BufferUtils.createIntBuffer(1);
        glfwGetWindowSize(id, widthBuffer, heightBuffer);
        this.width = widthBuffer.get();
        this.height = heightBuffer.get();
    }

    @Override
    public void setTitle(String title) {
        if (title == null)
            throw new IllegalArgumentException("Title cannot be null.");
        this.title = title;
        glfwSetWindowTitle(id, title);
    }

    @Override
    public void centerPosition() {
        glfwSetWindowPos(
                id,
                (Glfw.PRIMARY_MONITOR.width() - width) / 2,
                (Glfw.PRIMARY_MONITOR.height() - height) / 2
        );
    }

    @Override
    public void setWidth(int width) {
        setSize(width, height);
    }

    @Override
    public void setHeight(int height) {
        setSize(width, height);
    }

    @Override
    public void setSize(int width, int height) {
        if (width < 0 || height < 0)
            throw new IllegalArgumentException("Window's dimensions cannot be negative.");
        glfwSetWindowSize(id, width, height);
    }

    @Override
    public Vector2f getPosition() {
        IntBuffer x = BufferUtils.createIntBuffer(1), y = BufferUtils.createIntBuffer(1);
        glfwGetWindowPos(id, x, y);
        return new Vector2f(x.get(), y.get());
    }

    @Override
    public void setPosition(int x, int y) {
        glfwSetWindowPos(id, x, y);
    }

    @Override
    public boolean testKey(Key key) {
        return glfwGetKey(id, GLFWKey.toGlfw(key)) != GLFW_RELEASE;
    }

    @Override
    public Vector2d getCursorPosition() {
        DoubleBuffer x = BufferUtils.createDoubleBuffer(1), y = BufferUtils.createDoubleBuffer(1);
        glfwGetCursorPos(id, x, y);
        return new Vector2d(x.get(), y.get());
    }

    @Override
    public boolean testMouseButton(MouseButton button) {
        return glfwGetMouseButton(id, GLFWMouseButton.toGlfw(button)) == GLFW_PRESS;
    }

    @Override
    public void setCursorPosition(double x, double y) {
        glfwSetCursorPos(id, x, y);
    }

    @Override
    public boolean isCursorShowed() {
        return glfwGetInputMode(id, GLFW_CURSOR) == GLFW_CURSOR_NORMAL;
    }

    @Override
    public void showCursor() {
        glfwSetInputMode(id, GLFW_CURSOR, GLFW_CURSOR_NORMAL);
    }

    @Override
    public boolean isCursorHidden() {
        return glfwGetInputMode(id, GLFW_CURSOR) == GLFW_CURSOR_HIDDEN;
    }

    @Override
    public void hideCursor() {
        glfwSetInputMode(id, GLFW_CURSOR, GLFW_CURSOR_HIDDEN);
    }

    @Override
    public boolean isCursorEnabled() {
        return glfwGetInputMode(id, GLFW_CURSOR) == GLFW_CURSOR_NORMAL;
    }

    @Override
    public void setCursorEnabled(boolean enable) {
        glfwSetInputMode(id, GLFW_CURSOR, enable ? GLFW_CURSOR_NORMAL : GLFW_CURSOR_DISABLED);
    }

    @Override
    public void iconify() {
        glfwIconifyWindow(id);
    }

    @Override
    public void maximize() {
        glfwMaximizeWindow(id);
    }

    @Override
    public void show() {
        glfwShowWindow(id);
    }

    @Override
    public void hide() {
        glfwHideWindow(id);
    }

    @Override
    public boolean isClosed() {
        return glfwWindowShouldClose(id);
    }

    @Override
    public void close() {
        glfwSetWindowShouldClose(id, true);
    }

    @Override
    public void setVSync(boolean vSync) {
        glfwSwapInterval(vSync ? 1 : 0);
    }

    @Override
    public void update() {
        glfwSwapBuffers(id);
        glfwPollEvents();
    }

    @Override
    protected void makeContext() {
        glfwMakeContextCurrent(id);
    }

    @Override
    public void destroy() {
        glfwDestroyWindow(id);
    }
}

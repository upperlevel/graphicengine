package xyz.upperlevel.ulge.window;

import lombok.Getter;
import lombok.NonNull;
import org.joml.Vector2f;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWVidMode;
import xyz.upperlevel.ulge.window.event.button.GLFWMouseButton;
import xyz.upperlevel.ulge.window.event.button.MouseButton;
import xyz.upperlevel.ulge.window.event.key.GLFWKey;
import xyz.upperlevel.ulge.window.event.key.Key;

import java.nio.DoubleBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class GlfwWindow extends GlWindow {

    @Getter
    public final long id;

    @Getter
    @NonNull
    private String title;

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
        id = glfwCreateWindow(width, height, title, fullscreen ? glfwGetPrimaryMonitor() : NULL, NULL);
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
                (Glfw.PRIMARY_MONITOR.width() - getWidth()) / 2,
                (Glfw.PRIMARY_MONITOR.height() - getHeight()) / 2
        );
    }

    @Override
    public int getWidth() {
        IntBuffer wdt = BufferUtils.createIntBuffer(1);
        glfwGetWindowSize(id, wdt, null);
        return wdt.get();
    }

    @Override
    public void setWidth(int width) {
        setSize(width, getHeight());
    }

    @Override
    public int getHeight() {
        IntBuffer hgt = BufferUtils.createIntBuffer(1);
        glfwGetWindowSize(id, null, hgt);
        return hgt.get();
    }

    @Override
    public void setHeight(int height) {
        setSize(getWidth(), height);
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
    public boolean getKey(Key key) {
        return glfwGetKey(id, GLFWKey.toGlfw(key)) != GLFW_RELEASE;
    }

    @Override
    public Vector2f getCursorPosition() {
        DoubleBuffer x = BufferUtils.createDoubleBuffer(1), y = BufferUtils.createDoubleBuffer(1);
        glfwGetCursorPos(id, x, y);
        return new Vector2f((float) x.get() / getWidth(), (float) y.get() / getHeight());
    }

    @Override
    public boolean getMouseButton(MouseButton button) {
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
    public boolean isCursorDisabled() {
        return glfwGetInputMode(id, GLFW_CURSOR) == GLFW_CURSOR_DISABLED;
    }

    @Override
    public void disableCursor() {
        glfwSetInputMode(id, GLFW_CURSOR, GLFW_CURSOR_DISABLED);
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

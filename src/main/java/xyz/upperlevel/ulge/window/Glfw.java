package xyz.upperlevel.ulge.window;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.lwjgl.glfw.GLFWVidMode;
import xyz.upperlevel.ulge.window.event.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.lwjgl.glfw.GLFW.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Glfw {
    public static final GLFWVidMode PRIMARY_MONITOR;

    static {
        if (!glfwInit())
            throw new IllegalStateException("Glfw not initialized.");
        PRIMARY_MONITOR = glfwGetVideoMode(glfwGetPrimaryMonitor());
    }

    public static GlfwWindow createWindow(int width, int height, String title, boolean fullscreen) {
        return new GlfwWindow(width, height, title, fullscreen);
    }

    public static GlfwWindowSettings createWindowSettings() {
        return new GlfwWindowSettings();
    }

    public static void destroy() {
        glfwTerminate();
    }
}
package xyz.upperlevel.graphicengine.api.window;

import static org.lwjgl.glfw.GLFW.*;

public class GLFWWindowSettings {

    public GLFWWindowSettings() {
        glfwDefaultWindowHints();
    }

    private static int glfwBool(boolean bool) {
        return bool ? GLFW_TRUE : GLFW_FALSE;
    }

    public GLFWWindowSettings setFocused(boolean focused) {
        glfwWindowHint(GLFW_FOCUSED, glfwBool(focused));
        return this;
    }

    public GLFWWindowSettings setIconified(boolean iconified) {
        glfwWindowHint(GLFW_ICONIFIED, glfwBool(iconified));
        return this;
    }

    public GLFWWindowSettings setVisible(boolean visible) {
        glfwWindowHint(GLFW_VISIBLE, glfwBool(visible));
        return this;
    }

    public GLFWWindowSettings setMaximized(boolean maximized) {
        glfwWindowHint(GLFW_MAXIMIZED, glfwBool(maximized));
        return this;
    }

    public GLFWWindowSettings setResizable(boolean resizable) {
        glfwWindowHint(GLFW_RESIZABLE, glfwBool(resizable));
        return this;
    }

    public GLFWWindowSettings setDecorated(boolean decorated) {
        glfwWindowHint(GLFW_DECORATED, glfwBool(decorated));
        return this;
    }

    public GLFWWindowSettings setFloating(boolean floating) {
        glfwWindowHint(GLFW_FLOATING, glfwBool(floating));
        return this;
    }

    public GLFWWindow createWindow(int width, int height, String title, boolean fullscreen) {
        return new GLFWWindow(width, height, title, fullscreen);
    }
}

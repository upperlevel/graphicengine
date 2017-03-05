package xyz.upperlevel.ulge.window;

import static org.lwjgl.glfw.GLFW.*;

public class GlfwWindowSettings {

    public GlfwWindowSettings() {
        glfwDefaultWindowHints();
    }

    private static int glfwBool(boolean bool) {
        return bool ? GLFW_TRUE : GLFW_FALSE;
    }

    public GlfwWindowSettings setRedBits(int redBits) {
        glfwWindowHint(GLFW_RED_BITS, redBits);
        return this;
    }

    public GlfwWindowSettings setGreenBits(int greenBits) {
        glfwWindowHint(GLFW_GREEN_BITS, greenBits);
        return this;
    }

    public GlfwWindowSettings setBlueBits(int blueBits) {
        glfwWindowHint(GLFW_BLUE_BITS, blueBits);
        return this;
    }

    public GlfwWindowSettings setAlphaBits(int alphaBits) {
        glfwWindowHint(GLFW_ALPHA_BITS, alphaBits);
        return this;
    }

    public GlfwWindowSettings setDepthBits(int depthBits) {
        glfwWindowHint(GLFW_DEPTH_BITS, depthBits);
        return this;
    }

    public GlfwWindowSettings setStencilBits(int stencilBits) {
        glfwWindowHint(GLFW_STENCIL_BITS, stencilBits);
        return this;
    }

    public GlfwWindowSettings setSamples(int samples) {
        glfwWindowHint(GLFW_SAMPLES, samples);
        return this;
    }

    public GlfwWindowSettings setFocused(boolean focused) {
        glfwWindowHint(GLFW_FOCUSED, glfwBool(focused));
        return this;
    }

    public GlfwWindowSettings setIconified(boolean iconified) {
        glfwWindowHint(GLFW_ICONIFIED, glfwBool(iconified));
        return this;
    }

    public GlfwWindowSettings setVisible(boolean visible) {
        glfwWindowHint(GLFW_VISIBLE, glfwBool(visible));
        return this;
    }

    public GlfwWindowSettings setMaximized(boolean maximized) {
        glfwWindowHint(GLFW_MAXIMIZED, glfwBool(maximized));
        return this;
    }

    public GlfwWindowSettings setResizable(boolean resizable) {
        glfwWindowHint(GLFW_RESIZABLE, glfwBool(resizable));
        return this;
    }

    public GlfwWindowSettings setDecorated(boolean decorated) {
        glfwWindowHint(GLFW_DECORATED, glfwBool(decorated));
        return this;
    }

    public GlfwWindowSettings setFloating(boolean floating) {
        glfwWindowHint(GLFW_FLOATING, glfwBool(floating));
        return this;
    }

    public GlfwWindow createWindow(int width, int height, String title, boolean fullscreen) {
        return new GlfwWindow(width, height, title, fullscreen);
    }
}

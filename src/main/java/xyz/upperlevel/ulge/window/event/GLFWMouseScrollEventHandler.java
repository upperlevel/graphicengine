package xyz.upperlevel.ulge.window.event;

import xyz.upperlevel.ulge.window.Window;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWScrollCallback;

public class GLFWMouseScrollEventHandler extends AbstractGLFWEventHandler<MouseScrollEvent> {
    @Override
    public void apply(Window window) {
        GLFW.glfwSetScrollCallback(window.getId(), new GLFWScrollCallback() {
            @Override
            public void invoke(long id, double x, double y) {
                events.forEach(event -> event.onCall(window, x, y));
            }
        });
    }
}

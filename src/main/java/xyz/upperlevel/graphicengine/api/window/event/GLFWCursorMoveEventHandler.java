package xyz.upperlevel.graphicengine.api.window.event;

import xyz.upperlevel.graphicengine.api.window.Window;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;

public class GLFWCursorMoveEventHandler extends AbstractGLFWEventHandler<CursorMoveEvent> {

    @Override
    public void apply(Window window) {
        GLFW.glfwSetCursorPosCallback(window.getId(), new GLFWCursorPosCallback() {
            @Override
            public void invoke(long id, double x, double y) {
                events.forEach(event -> event.onCall(window, x, y));
            }
        });
    }
}

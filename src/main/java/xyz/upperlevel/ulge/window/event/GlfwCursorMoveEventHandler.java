package xyz.upperlevel.ulge.window.event;

import xyz.upperlevel.ulge.window.Window;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;

public class GlfwCursorMoveEventHandler extends AbstractGlfwEventHandler<CursorMoveEvent> {

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

package xyz.upperlevel.graphicengine.api.window.event;

import xyz.upperlevel.graphicengine.api.window.Window;
import xyz.upperlevel.graphicengine.api.window.event.action.Action;
import xyz.upperlevel.graphicengine.api.window.event.button.MouseButton;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

public class GLFWMouseButtonChangeEventHandler extends AbstractGLFWEventHandler<MouseButtonChangeEvent> {

    @Override
    public void apply(Window window) {
        GLFW.glfwSetMouseButtonCallback(window.getId(), new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long id, int button, int action, int mode) {
                events.forEach(event -> event.onCall(window, new MouseButton(button), new Action(action)));
            }
        });
    }
}

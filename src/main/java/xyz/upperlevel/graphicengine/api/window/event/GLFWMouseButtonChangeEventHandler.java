package xyz.upperlevel.graphicengine.api.window.event;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import xyz.upperlevel.graphicengine.api.window.Window;
import xyz.upperlevel.graphicengine.api.window.event.action.Action;
import xyz.upperlevel.graphicengine.api.window.event.button.GLFWMouseButton;
import xyz.upperlevel.graphicengine.api.window.event.button.MouseButton;

public class GLFWMouseButtonChangeEventHandler extends AbstractGLFWEventHandler<MouseButtonChangeEvent> {

    @Override
    public void apply(Window window) {
        GLFW.glfwSetMouseButtonCallback(window.getId(), new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long id, int button, int action, int mode) {
                final Action stAction = action != GLFW.GLFW_RELEASE ? Action.PRESS : Action.RELEASE;
                final MouseButton stButton = GLFWMouseButton.toStandard(button);
                events.forEach(event -> event.onCall(window, stButton, stAction));
            }
        });
    }
}

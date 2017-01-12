package xyz.upperlevel.graphicengine.api.window.event;

import xyz.upperlevel.graphicengine.api.window.Window;
import xyz.upperlevel.graphicengine.api.window.event.key.Key;
import xyz.upperlevel.graphicengine.api.window.event.action.Action;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

public class GLFWKeyChangeEventHandler extends AbstractGLFWEventHandler<KeyChangeEvent> {

    @Override
    public void apply(Window window) {
        GLFW.glfwSetKeyCallback(window.getId(), new GLFWKeyCallback() {
            @Override
            public void invoke(long id, int key, int scanCode, int action, int mods) {
                events.forEach(event -> event.onCall(window, new Key(key), new Action(action)));
            }
        });
    }
}

package xyz.upperlevel.ulge.window.event;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;
import xyz.upperlevel.ulge.window.Window;
import xyz.upperlevel.ulge.window.event.action.Action;
import xyz.upperlevel.ulge.window.event.key.GLFWKey;
import xyz.upperlevel.ulge.window.event.key.Key;

public class GLFWKeyChangeEventHandler extends AbstractGLFWEventHandler<KeyChangeEvent> {

    @Override
    public void apply(Window window) {
        GLFW.glfwSetKeyCallback(window.getId(), new GLFWKeyCallback() {
            @Override
            public void invoke(long id, int key, int scanCode, int action, int mods) {
                final Key stKey = GLFWKey.toStandard(key);
                final Action stAction = action != GLFW.GLFW_RELEASE ? Action.PRESS : Action.RELEASE;
                events.forEach(event -> event.onCall(window, stKey, stAction));
            }
        });
    }
}

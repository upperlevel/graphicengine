package xyz.upperlevel.graphicengine.api.window.event.action;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.GLFW_REPEAT;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GLFWAction {

    public static final Action
            REPEAT = new Action(GLFW_REPEAT),
            PRESS = new Action(GLFW_PRESS),
            RELEASE = new Action(GLFW_RELEASE);
}
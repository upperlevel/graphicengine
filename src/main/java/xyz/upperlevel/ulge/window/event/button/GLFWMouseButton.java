package xyz.upperlevel.ulge.window.event.button;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.glfw.GLFW.*;
import static xyz.upperlevel.ulge.window.event.button.MouseButton.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GLFWMouseButton {
    public static Map<Integer, MouseButton> toStandardMapping = new HashMap<>(MouseButton.length());
    public static int[] toGlfwMapping = new int[MouseButton.length()];

    static {
        //register(BUTTON_1, GLFW_MOUSE_BUTTON_1); LEFT
        //register(BUTTON_2, GLFW_MOUSE_BUTTON_2); RIGHT
        //register(BUTTON_3, GLFW_MOUSE_BUTTON_3); MIDDLE
        register(BUTTON_4, GLFW_MOUSE_BUTTON_4);
        register(BUTTON_5, GLFW_MOUSE_BUTTON_5);
        register(BUTTON_6, GLFW_MOUSE_BUTTON_6);
        register(BUTTON_7, GLFW_MOUSE_BUTTON_7);
        //register(BUTTON_8, GLFW_MOUSE_BUTTON_8); LAST
        register(LAST, GLFW_MOUSE_BUTTON_LAST);
        register(LEFT, GLFW_MOUSE_BUTTON_LEFT);
        register(RIGHT, GLFW_MOUSE_BUTTON_RIGHT);
        register(MIDDLE, GLFW_MOUSE_BUTTON_MIDDLE);
    }

    private static void register(MouseButton button, int glfwId) {
        if(toStandardMapping.put(glfwId, button) != null)
            throw new IllegalStateException("Cannot set the same key two times!");
        toGlfwMapping[button.id] = glfwId;
    }

    public static MouseButton toStandard(int glfwId) {
        return toStandardMapping.getOrDefault(glfwId, UNKNOWN);
    }

    public static int toGlfw(MouseButton button) {
        if(button.id > 0 && button.id < toGlfwMapping.length)
            return toGlfwMapping[button.id];
        else return -1;
    }
}

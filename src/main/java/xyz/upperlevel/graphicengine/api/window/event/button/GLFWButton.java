package xyz.upperlevel.graphicengine.api.window.event.button;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static org.lwjgl.glfw.GLFW.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GLFWButton {

    public static final MouseButton
            BUTTON_1 = new MouseButton(GLFW_MOUSE_BUTTON_1),
            BUTTON_2 = new MouseButton(GLFW_MOUSE_BUTTON_2),
            BUTTON_3 = new MouseButton(GLFW_MOUSE_BUTTON_3),
            BUTTON_4 = new MouseButton(GLFW_MOUSE_BUTTON_4),
            BUTTON_5 = new MouseButton(GLFW_MOUSE_BUTTON_5),
            BUTTON_6 = new MouseButton(GLFW_MOUSE_BUTTON_6),
            BUTTON_7 = new MouseButton(GLFW_MOUSE_BUTTON_7),
            BUTTON_8 = new MouseButton(GLFW_MOUSE_BUTTON_8),
            LAST = new MouseButton(GLFW_MOUSE_BUTTON_LAST),
            LEFT = new MouseButton(GLFW_MOUSE_BUTTON_LEFT),
            RIGHT = new MouseButton(GLFW_MOUSE_BUTTON_RIGHT),
            MIDDLE = new MouseButton(GLFW_MOUSE_BUTTON_MIDDLE);
}

package xyz.upperlevel.ulge.window.event.key;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.glfw.GLFW.*;
import static xyz.upperlevel.ulge.window.event.key.Key.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GLFWKey {
    public static Map<Integer, Key> toStandardMapping = new HashMap<>(Key.length());
    public static int[] toGlfwMapping = new int[Key.length()];

    static {
        register(UNKNOWN, GLFW_KEY_UNKNOWN);
        register(SPACE, GLFW_KEY_SPACE);
        register(APOSTROPHE, GLFW_KEY_APOSTROPHE);
        register(COMMA, GLFW_KEY_COMMA);
        register(MINUS, GLFW_KEY_MINUS);
        register(PERIOD, GLFW_KEY_PERIOD);
        register(SLASH, GLFW_KEY_SLASH);
        register(KEY_0, GLFW_KEY_0);
        register(KEY_1, GLFW_KEY_1);
        register(KEY_2, GLFW_KEY_2);
        register(KEY_3, GLFW_KEY_3);
        register(KEY_4, GLFW_KEY_4);
        register(KEY_5, GLFW_KEY_5);
        register(KEY_6, GLFW_KEY_6);
        register(KEY_7, GLFW_KEY_7);
        register(KEY_8, GLFW_KEY_8);
        register(KEY_9, GLFW_KEY_9);
        register(SEMICOLON, GLFW_KEY_SEMICOLON);
        register(EQUAL, GLFW_KEY_EQUAL);
        register(A, GLFW_KEY_A);
        register(B, GLFW_KEY_B);
        register(C, GLFW_KEY_C);
        register(D, GLFW_KEY_D);
        register(E, GLFW_KEY_E);
        register(F, GLFW_KEY_F);
        register(G, GLFW_KEY_G);
        register(H, GLFW_KEY_H);
        register(I, GLFW_KEY_I);
        register(J, GLFW_KEY_J);
        register(K, GLFW_KEY_K);
        register(L, GLFW_KEY_L);
        register(M, GLFW_KEY_M);
        register(N, GLFW_KEY_N);
        register(O, GLFW_KEY_O);
        register(P, GLFW_KEY_P);
        register(Q, GLFW_KEY_Q);
        register(R, GLFW_KEY_R);
        register(S, GLFW_KEY_S);
        register(T, GLFW_KEY_T);
        register(U, GLFW_KEY_U);
        register(V, GLFW_KEY_V);
        register(W, GLFW_KEY_W);
        register(X, GLFW_KEY_X);
        register(Y, GLFW_KEY_Y);
        register(Z, GLFW_KEY_Z);
        register(LEFT_BRAKET, GLFW_KEY_LEFT_BRACKET);
        register(BACKSLASH, GLFW_KEY_BACKSLASH);
        register(RIGHT_BRACKET, GLFW_KEY_RIGHT_BRACKET);
        register(GRAVE_ACCENT, GLFW_KEY_GRAVE_ACCENT);
        register(WORLD_1, GLFW_KEY_WORLD_1);
        register(WORLD_2, GLFW_KEY_WORLD_2);
        register(ESCAPE, GLFW_KEY_ESCAPE);
        register(ENTER, GLFW_KEY_ENTER);
        register(TAB, GLFW_KEY_TAB);
        register(BACKSPACE, GLFW_KEY_BACKSPACE);
        register(INSERT, GLFW_KEY_INSERT);
        register(DELETE, GLFW_KEY_DELETE);
        register(RIGHT, GLFW_KEY_RIGHT);
        register(LEFT, GLFW_KEY_LEFT);
        register(DOWN, GLFW_KEY_DOWN);
        register(UP, GLFW_KEY_UP);
        register(PAGE_UP, GLFW_KEY_PAGE_UP);
        register(PAGE_DOWN, GLFW_KEY_PAGE_DOWN);
        register(HOME, GLFW_KEY_HOME);
        register(END, GLFW_KEY_END);
        register(CAPS_LOCK, GLFW_KEY_CAPS_LOCK);
        register(SCROLL_LOCK, GLFW_KEY_SCROLL_LOCK);
        register(NUM_LOCK, GLFW_KEY_NUM_LOCK);
        register(PRINT_SCREEN, GLFW_KEY_PRINT_SCREEN);
        register(PAUSE, GLFW_KEY_PAUSE);
        register(F1, GLFW_KEY_F1);
        register(F2, GLFW_KEY_F2);
        register(F3, GLFW_KEY_F3);
        register(F4, GLFW_KEY_F4);
        register(F5, GLFW_KEY_F5);
        register(F6, GLFW_KEY_F6);
        register(F7, GLFW_KEY_F7);
        register(F8, GLFW_KEY_F8);
        register(F9, GLFW_KEY_F9);
        register(F10, GLFW_KEY_F10);
        register(F11, GLFW_KEY_F11);
        register(F12, GLFW_KEY_F12);
        register(F13, GLFW_KEY_F13);
        register(F14, GLFW_KEY_F14);
        register(F15, GLFW_KEY_F15);
        register(F16, GLFW_KEY_F16);
        register(F17, GLFW_KEY_F17);
        register(F18, GLFW_KEY_F18);
        register(F19, GLFW_KEY_F19);
        register(F20, GLFW_KEY_F20);
        register(F21, GLFW_KEY_F21);
        register(F22, GLFW_KEY_F22);
        register(F23, GLFW_KEY_F23);
        register(F24, GLFW_KEY_F24);
        register(F25, GLFW_KEY_F25);

        register(KP_0, GLFW_KEY_KP_0);
        register(KP_1, GLFW_KEY_KP_1);
        register(KP_2, GLFW_KEY_KP_2);
        register(KP_3, GLFW_KEY_KP_3);
        register(KP_4, GLFW_KEY_KP_4);
        register(KP_5, GLFW_KEY_KP_5);
        register(KP_6, GLFW_KEY_KP_6);
        register(KP_7, GLFW_KEY_KP_7);
        register(KP_8, GLFW_KEY_KP_8);
        register(KP_9, GLFW_KEY_KP_9);
        register(KP_DECIAML, GLFW_KEY_KP_DECIMAL);
        register(KP_DIVIDE, GLFW_KEY_KP_DIVIDE);
        register(KP_MULTIPLY, GLFW_KEY_KP_MULTIPLY);
        register(KP_SUBTRACT, GLFW_KEY_KP_SUBTRACT);
        register(KP_ADD, GLFW_KEY_KP_ADD);
        register(KP_ENTER, GLFW_KEY_KP_ENTER);
        register(KP_EQUAL, GLFW_KEY_KP_EQUAL);

        register(LEFT_SHIFT, GLFW_KEY_LEFT_SHIFT);
        register(LEFT_CONTROL, GLFW_KEY_LEFT_CONTROL);
        register(LEFT_ALT, GLFW_KEY_LEFT_ALT);
        register(LEFT_SUPER, GLFW_KEY_LEFT_SUPER);
        register(RIGHT_SHIFT, GLFW_KEY_RIGHT_SHIFT);
        register(RIGHT_CONTROL, GLFW_KEY_RIGHT_CONTROL);
        register(RIGHT_ALT, GLFW_KEY_RIGHT_ALT);
        register(RIGHT_SUPER, GLFW_KEY_RIGHT_SUPER);
    }

    private static void register(Key key, int glfwId) {
        if(toStandardMapping.put(glfwId, key) != null)
            throw new IllegalStateException("Cannot set the same key two times!");
        toGlfwMapping[key.id] = glfwId;
    }

    public static Key toStandard(int glfwId) {
        return toStandardMapping.getOrDefault(glfwId, UNKNOWN);
    }

    public static int toGlfw(Key key) {
        if(key.id > 0 && key.id < toGlfwMapping.length)
            return toGlfwMapping[key.id];
        else return GLFW_KEY_UNKNOWN;
    }
}
package xyz.upperlevel.graphicengine.api.window;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import xyz.upperlevel.graphicengine.api.window.event.GLFWCursorMoveEventHandler;
import xyz.upperlevel.graphicengine.api.window.event.GLFWMouseButtonChangeEventHandler;
import xyz.upperlevel.graphicengine.api.window.event.GLFWMouseScrollEventHandler;
import xyz.upperlevel.graphicengine.api.window.event.WindowEventHandler;
import xyz.upperlevel.graphicengine.api.window.event.GLFWKeyChangeEventHandler;
import org.lwjgl.glfw.GLFWVidMode;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.lwjgl.glfw.GLFW.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GLFW {

    public static final GLFWVidMode PRIMARY_MONITOR;

    static {
        if (!glfwInit())
            throw new IllegalStateException("GLFW not initialized.");
        PRIMARY_MONITOR = glfwGetVideoMode(glfwGetPrimaryMonitor());
    }

    public static class Event {

        public static final Event INSTANCE = new Event();

        public static class Initializer {

            private final Class<? extends WindowEventHandler> evHndClass;

            private Initializer(Class<? extends WindowEventHandler> evHndClass) {
                this.evHndClass = evHndClass;
            }

            public WindowEventHandler inst() {
                Constructor<? extends WindowEventHandler> ctr;
                try {
                    ctr = evHndClass.getDeclaredConstructor();
                } catch (NoSuchMethodException e) {
                    throw new IllegalStateException("Window event handler constructor cannot have arguments.");
                }
                try {
                    return ctr.newInstance();
                } catch (InstantiationException e) {
                    throw new IllegalStateException("Cannot initializing " + evHndClass.getName() + " class.");
                } catch (IllegalAccessException e) {
                   throw new IllegalStateException("Cannot access " + evHndClass.getName() + " constructor.");
                } catch (InvocationTargetException e) {
                    throw new IllegalStateException(evHndClass.getName() + " constructor thrown an exception.");
                }
            }
        }

        public final Initializer
                CURSOR_MOVE = new Initializer(GLFWCursorMoveEventHandler.class),
                KEY_CHANGE = new Initializer(GLFWKeyChangeEventHandler.class),
                MOUSE_BUTTON_CHANGE = new Initializer(GLFWMouseButtonChangeEventHandler.class),
                MOUSE_SCROLL = new Initializer(GLFWMouseScrollEventHandler.class);
    }

    public static Event events() {
        return Event.INSTANCE;
    }

    public static GLFWWindow createWindow(int width, int height, String title) {
        return new GLFWWindow(width, height, title);
    }

    public static GLFWWindowSettings createWindowSettings() {
        return new GLFWWindowSettings();
    }

    public static void destroy() {
        glfwTerminate();
    }
}
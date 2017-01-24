package xyz.upperlevel.ulge.window;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.lwjgl.glfw.GLFWVidMode;
import xyz.upperlevel.graphicengine.api.window.event.*;
import xyz.upperlevel.ulge.window.event.*;

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

        public static class Initializer<T extends WindowEventHandler> {

            private final Class<T> evHndClass;

            private Initializer(Class<T> evHndClass) {
                this.evHndClass = evHndClass;
            }

            @SuppressWarnings("unchecked")
            public T inst() {
                Constructor<? extends WindowEventHandler> ctr;
                try {
                    ctr = evHndClass.getDeclaredConstructor();
                } catch (NoSuchMethodException e) {
                    throw new IllegalStateException("Window event handler constructor cannot have arguments.");
                }
                try {
                    return (T) ctr.newInstance();
                } catch (InstantiationException e) {
                    throw new IllegalStateException("Cannot initializing " + evHndClass.getName() + " class.");
                } catch (IllegalAccessException e) {
                   throw new IllegalStateException("Cannot access " + evHndClass.getName() + " constructor.");
                } catch (InvocationTargetException e) {
                    throw new IllegalStateException(evHndClass.getName() + " constructor thrown an exception.");
                }
            }
        }

        public final Initializer<GLFWCursorMoveEventHandler>        CURSOR_MOVE         = new Initializer<>(GLFWCursorMoveEventHandler.class);
        public final Initializer<GLFWKeyChangeEventHandler>         KEY_CHANGE          = new Initializer<>(GLFWKeyChangeEventHandler.class);
        public final Initializer<GLFWMouseButtonChangeEventHandler> MOUSE_BUTTON_CHANGE = new Initializer<>(GLFWMouseButtonChangeEventHandler.class);
        public final Initializer<GLFWMouseScrollEventHandler>       MOUSE_SCROLL        = new Initializer<>(GLFWMouseScrollEventHandler.class);
    }

    public static Event events() {
        return Event.INSTANCE;
    }

    public static GLFWWindow createWindow(int width, int height, String title, boolean fullscreen) {
        return new GLFWWindow(width, height, title, fullscreen);
    }

    public static GLFWWindowSettings createWindowSettings() {
        return new GLFWWindowSettings();
    }

    public static void destroy() {
        glfwTerminate();
    }
}
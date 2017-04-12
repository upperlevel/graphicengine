package xyz.upperlevel.ulge.window;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.lwjgl.glfw.GLFWVidMode;
import xyz.upperlevel.ulge.window.event.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.lwjgl.glfw.GLFW.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Glfw {

    public static final GLFWVidMode PRIMARY_MONITOR;

    static {
        if (!glfwInit())
            throw new IllegalStateException("Glfw not initialized.");
        PRIMARY_MONITOR = glfwGetVideoMode(glfwGetPrimaryMonitor());
    }

    public static class Event {

        public static final Event GET = new Event();

        public static class Initializer<T extends WindowEventHandler> {

            private Class<T> handlerClass;

            private Initializer(Class<T> handler_class) {
                this.handlerClass = handler_class;
            }

            @SuppressWarnings("unchecked")
            public T create() {
                Constructor<? extends WindowEventHandler> ctr;
                try {
                    ctr = handlerClass.getDeclaredConstructor();
                } catch (NoSuchMethodException e) {
                    throw new IllegalStateException("window events handler constructor cannot have args", e);
                }
                try {
                    return (T) ctr.newInstance();
                } catch (InstantiationException e) {
                    throw new IllegalStateException("can't init " + handlerClass.getName() + " class", e);
                } catch (IllegalAccessException e) {
                   throw new IllegalStateException("can't access " + handlerClass.getName() + " constructor", e);
                } catch (InvocationTargetException e) {
                    throw new IllegalStateException(handlerClass.getName() + " constructor thrown an exception", e);
                }
            }
        }

        public final Initializer<GlfwCursorMoveEventHandler>        CURSOR_MOVE         = new Initializer<>(GlfwCursorMoveEventHandler.class);
        public final Initializer<GlfwKeyChangeEventHandler>         KEY_CHANGE          = new Initializer<>(GlfwKeyChangeEventHandler.class);
        public final Initializer<GlfwMouseButtonChangeEventHandler> MOUSE_BUTTON_CHANGE = new Initializer<>(GlfwMouseButtonChangeEventHandler.class);
        public final Initializer<GlfwMouseScrollEventHandler>       MOUSE_SCROLL        = new Initializer<>(GlfwMouseScrollEventHandler.class);
    }

    public static Event events() {
        return Event.GET;
    }

    public static GlfwWindow createWindow(int width, int height, String title, boolean fullscreen) {
        return new GlfwWindow(width, height, title, fullscreen);
    }

    public static GlfwWindowSettings createWindowSettings() {
        return new GlfwWindowSettings();
    }

    public static void destroy() {
        glfwTerminate();
    }
}
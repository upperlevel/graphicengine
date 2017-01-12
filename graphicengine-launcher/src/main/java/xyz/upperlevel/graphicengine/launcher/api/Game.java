package xyz.upperlevel.graphicengine.launcher.api;

import lombok.*;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Map;
import java.util.Optional;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

@NoArgsConstructor
public abstract class Game {

    GameIdentity identity;
    JarFile jar;
    ClassLoader classLoader;

    @Getter
    GameLooper looper;

    /**
     * Gets game identity.
     */
    public final GameIdentity getIdentity() {
        return identity;
    }

    /**
     * Gets game jar file.
     */
    public final JarFile getJar() {
        return jar;
    }

    /**
     * Gets game class loader.
     */
    public final ClassLoader getClassLoader() {
        return classLoader;
    }

    /**
     * Called on game looper starting.
     */
    public void start() {
    }

    /**
     * Called on game looper looping.
     */
    public void loop() {
    }

    /**
     * Called on game looper close.
     */
    public void close() {
    }
}
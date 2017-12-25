package xyz.upperlevel.ulge.opengl.shader;

import lombok.Getter;

import java.util.*;

import static org.lwjgl.opengl.GL20.*;

@Getter
public class Program {
    private final int id;
    private Set<Shader> shaders = new HashSet<>();
    private boolean destroyed;

    public Program() {
        id = glCreateProgram();
    }

    public Program(int id) {
        this.id = id;
    }

    private void checkNotDestroyed() {
        if (destroyed) {
            throw new IllegalStateException("The program is destroyed");
        }
    }

    /**
     * Attaches the shader.
     *
     * @param shader the shader
     */
    public void attach(Shader shader) {
        checkNotDestroyed();
        if (shader == null) {
            throw new NullPointerException("Shader cannot be null");
        }
        if (shaders.add(shader)) {
            glAttachShader(id, shader.getId());
        }
    }

    /**
     * Detaches the shader.
     *
     * @param shader the shader
     */
    public void detach(Shader shader) {
        checkNotDestroyed();
        if (shader == null) {
            throw new NullPointerException("Shader cannot be null");
        }
        if (shaders.remove(shader)) {
            glDetachShader(id, shader.getId());
        }
    }

    /**
     * Links the program.
     */
    public void link() {
        checkNotDestroyed();
        glLinkProgram(id);
    }

    /**
     * Binds the program.
     */
    public void use() {
        checkNotDestroyed();
        glUseProgram(id);
    }

    /**
     * Unbinds the program.
     */
    public void unuse() {
        checkNotDestroyed();
        glUseProgram(0);
    }

    /**
     * Destroys the program.
     */
    public void destroy() {
        glDeleteProgram(id);
        shaders = null;
        destroyed = true;
    }

    /**
     * Gets attribute location from its name.
     *
     * @param name the name
     * @return the attribute location
     */
    public int getAttribLocation(String name) {
        return glGetAttribLocation(id, name);
    }

    public boolean hasAttrib(String name) {
        return getAttribLocation(name) >= 0;
    }

    /**
     * Gets uniform location from its name.
     *
     * @param name the name
     * @return the uniform location
     */
    public int getUniformLocation(String name) {
        return glGetUniformLocation(id, name);
    }

    public boolean hasUniform(String name) {
        return getUniformLocation(name) >= 0;
    }

    /**
     * Gets the uniform from its name.
     *
     * @param name the name
     * @return the uniform
     */
    public Uniform getUniform(String name) {
        int uLoc = getUniformLocation(name);
        if (uLoc < 0) {
            return null;
        }
        return new Uniform(this, uLoc);
    }
}

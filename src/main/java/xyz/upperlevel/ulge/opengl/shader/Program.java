package xyz.upperlevel.ulge.opengl.shader;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import static org.lwjgl.opengl.GL20.*;

public class Program {

    private static Program bound;

    @Getter
    private int id;

    @Getter
    private List<Shader> shaders = new ArrayList<>();

    private Uniformer uniformer = new Uniformer(this);

    public Program() {
        id = glCreateProgram();
    }

    public Program attach(Shader shader) {
        Objects.requireNonNull(shader, "Shader cannot be null.");
        glAttachShader(id, shader.getId());
        shaders.add(shader);
        return this;
    }

    private Program strictDetach(Shader shader) {
        Objects.requireNonNull(shader, "Shader cannot be null.");
        glDetachShader(id, shader.getId());
        return this;
    }

    public Program detach(Shader shader) {
        Objects.requireNonNull(shader, "Shader cannot be null.");
        if (shaders.remove(shader))
            strictDetach(shader);
        return this;
    }

    public Program link() {
        glLinkProgram(id);
        return this;
    }

    public Uniformer bind() {
        if (!equals(bound)) {
            glUseProgram(id);
            bound = this;
        }
        return uniformer;
    }

    public Program unbind() {
        if (equals(bound)) {
            glUseProgram(0);
            bound = null;
        }
        return this;
    }

    public Program destroy() {
        for (Iterator<Shader> iterator = shaders.iterator(); iterator.hasNext(); ) {
            Shader shader = iterator.next();
            strictDetach(shader);
            iterator.remove();
            shader.destroy();
        }
        glDeleteProgram(id);
        return this;
    }

    public static Program getBound() {
        return bound;
    }

    @Deprecated
    public static void setBound(Program program) {
        bound = program;
    }
}

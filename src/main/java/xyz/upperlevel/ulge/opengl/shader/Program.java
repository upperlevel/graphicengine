package xyz.upperlevel.ulge.opengl.shader;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import static org.lwjgl.opengl.GL20.*;

public class Program {

    @Getter public final int id;
    @Getter public final List<Shader> shaders = new ArrayList<>();

    public final Uniformer UNIFORMER = new Uniformer(this);

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

    public boolean detach(Shader shader) {
        Objects.requireNonNull(shader, "Shader cannot be null.");
        boolean result = shaders.remove(shader);
        if (result) {
            strictDetach(shader);
            return true;
        }
        return false;
    }

    public Program link() {
        glLinkProgram(id);
        return this;
    }

    public Uniformer bind() {
        glUseProgram(id);
        return UNIFORMER;
    }

    public Program unbind() {
        glUseProgram(0);
        return this;
    }

    public Program destroy() {
        for (Iterator<Shader> iterator = shaders.iterator(); iterator.hasNext();) {
            Shader shader = iterator.next();
            strictDetach(shader);
            iterator.remove();
            shader.destroy();
        }
        glDeleteProgram(id);
        return this;
    }
}

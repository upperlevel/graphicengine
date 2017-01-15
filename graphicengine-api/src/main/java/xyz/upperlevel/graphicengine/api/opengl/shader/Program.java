package xyz.upperlevel.graphicengine.api.opengl.shader;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.function.Consumer;

import static org.lwjgl.opengl.GL20.*;

public class Program {

    @Getter public final int id;
    @Getter public final List<Shader> shaders = new ArrayList<>();

    public final Uniformer UNIFORMER = new Uniformer(this);

    public Program() {
        id = glCreateProgram();
    }

    public void attach(Shader shader) {
        Objects.requireNonNull(shader, "Shader cannot be null.");
        glAttachShader(id, shader.getId());
        shaders.add(shader);
    }

    private void strictDetach(Shader shader) {
        Objects.requireNonNull(shader, "Shader cannot be null.");
        glDetachShader(id, shader.getId());
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

    public void link() {
        glLinkProgram(id);
    }

    public Uniformer bind() {
        glUseProgram(id);
        return UNIFORMER;
    }

    public void unbind() {
        glUseProgram(0);
    }

    public void destroy() {
        for (Iterator<Shader> iterator = shaders.iterator(); iterator.hasNext();) {
            Shader shader = iterator.next();
            strictDetach(shader);
            iterator.remove();
            shader.destroy();
        }
        glDeleteProgram(id);
    }
}

package xyz.upperlevel.ulge.opengl.shader;

import lombok.Getter;

import java.util.*;

import static org.lwjgl.opengl.GL20.*;

public class Program {

    public static Program bound;

    @Getter
    public final int id;
    @Getter
    public final List<Shader> shaders = new ArrayList<>();

    public final Uniformer uniformer = new Uniformer(this);

    public static Queue<Program> programs = new LinkedList<>();

    public Program() {
        id = glCreateProgram();
    }

    public Program(int id) {
        this.id = id;
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
        if(bound == null || bound.id != id) {
            glUseProgram(id);
            bound = this;
        }
        return uniformer;
    }

    public Uniformer forceBind() {
        glUseProgram(id);
        bound = this;
        return uniformer;
    }

    public Uniformer push() {
        programs.add(bound);
        bind();
        return uniformer;
    }

    public Program unbind() {
        if(bound != null) {
            glUseProgram(0);
            bound = null;
        }
        return this;
    }

    public Program forceUnbind() {
        glUseProgram(0);
        bound = null;
        return this;
    }

    public Program pop() {
        if(programs.isEmpty())
            throw new IllegalStateException("Cannot pop: program queue is empty");
        Program last = programs.remove();
        if(last == null)
            unbind();
        else
            last.bind();
        return this;
    }

    public boolean isBound() {
        return bound.id == id;
    }

    public Program destroy() {
        for(Shader shader : shaders) {
            strictDetach(shader);
            shader.destroy();
        }
        shaders.isEmpty();
        glDeleteProgram(id);
        return this;
    }

    public static Program wrap(int id) {
        return new Program(id);
    }
}

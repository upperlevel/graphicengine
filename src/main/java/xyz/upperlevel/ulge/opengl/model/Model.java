package xyz.upperlevel.ulge.opengl.model;

import lombok.Getter;

import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.Optional;

import static org.lwjgl.opengl.GL15.*;

public class Model {

    @Getter public final int id;
    @Getter private int verticesCount = 0;
    @Getter private Drawer drawer;

    private VertexDefiner definer;

    public Model(Drawer drawer) {
        Objects.requireNonNull(drawer, "Drawer cannot be null.");
        id = glGenBuffers();
        this.drawer = drawer;
    }

    public void setDrawer(Drawer drawer) {
        this.drawer = drawer;
    }

    public Optional<VertexDefiner> getDefiner() {
        return Optional.ofNullable(definer);
    }

    public void setDefiner(VertexDefiner definer) {
        if (definer == null)
            throw new IllegalArgumentException("Definer cannot be null.");
        this.definer = definer;
    }

    public void bind() {
        glBindBuffer(GL_ARRAY_BUFFER, id);
        getDefiner().ifPresent(VertexDefiner::setup);
    }

    public void unbind() {
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    public void use(Runnable action) {
        bind();
        action.run();
        unbind();
    }

    public void loadData(ByteBuffer data, int verticesCount) {
        bind();
        glBufferData(GL_ARRAY_BUFFER, data, GL_STATIC_DRAW);
        unbind();
        this.verticesCount = verticesCount;
    }

    public void loadData(Vertices vertices) {
        loadData(vertices.buffer(), vertices.size());
    }

    public void draw() {
        bind();
        drawer.draw(Drawer.Mode.TRIANGLES, this);
        unbind();
    }

    public void destroy() {
        glDeleteBuffers(id);
    }
}
package xyz.upperlevel.graphicengine.api.opengl.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static org.lwjgl.opengl.GL11.*;

public interface Drawer {

    @RequiredArgsConstructor
    class Mode {

        public static final Mode
                POINTS = new Mode(GL_POINTS),
                LINES = new Mode(GL_LINES),
                TRIANGLES = new Mode(GL_TRIANGLES),
                QUADS = new Mode(GL_QUADS),
                POLYGON = new Mode(GL_POLYGON);

        @Getter public final int id;
    }

    void draw(Mode mode, Model model);
}
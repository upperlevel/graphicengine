package xyz.upperlevel.ulge.opengl.shader;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;

@RequiredArgsConstructor
public enum ShaderType {

    VERTEX(GL_VERTEX_SHADER),
    FRAGMENT(GL_FRAGMENT_SHADER);

    @Getter public final int id;
}

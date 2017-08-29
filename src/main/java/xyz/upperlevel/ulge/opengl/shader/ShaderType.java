package xyz.upperlevel.ulge.opengl.shader;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;

@Getter
public enum ShaderType {

    VERTEX(GL_VERTEX_SHADER, "vs", "vertex", "vertexshader"),
    FRAGMENT(GL_FRAGMENT_SHADER, "fs", "fragment", "fragmentshader");

    public final int id;
    private final List<String> extensions;

    ShaderType(int id, String... extensions) {
        this.id = id;
        this.extensions = Arrays.asList(extensions);
    }

    public static ShaderType getFromExtension(String extension) {
        for (ShaderType value : ShaderType.values())
            if (value.extensions.contains(extension.toLowerCase(Locale.ENGLISH)))
                return value;
        return null;
    }
}

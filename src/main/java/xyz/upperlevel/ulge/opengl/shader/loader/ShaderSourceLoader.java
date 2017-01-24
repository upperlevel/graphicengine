package xyz.upperlevel.ulge.opengl.shader.loader;

import java.io.*;

public interface ShaderSourceLoader {

    String load(InputStream stream) throws IOException;

    default String load(File file) throws IOException {
        return load(new FileInputStream(file));
    }
}

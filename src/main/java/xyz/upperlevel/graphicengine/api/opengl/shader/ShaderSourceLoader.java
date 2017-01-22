package xyz.upperlevel.graphicengine.api.opengl.shader;

import java.io.*;

public interface ShaderSourceLoader {

    String load(InputStream stream) throws IOException;

    default String load(File file) throws IOException {
        return load(new FileInputStream(file));
    }
}

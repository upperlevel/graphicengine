package xyz.upperlevel.ulge.opengl.shader.loader;

import java.io.*;

public class SimpleShaderSourceLoader implements ShaderSourceLoader {

    public static final ShaderSourceLoader $ = new SimpleShaderSourceLoader();

    @Override
    public String load(InputStream stream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String result = "";
        String line;
        while ((line = reader.readLine()) != null)
            result += line + "\n";
        return result;
    }

    public static ShaderSourceLoader $() {
        return $;
    }
}
package xyz.upperlevel.graphicengine.api.opengl.shader.loader;

import java.io.*;

public class SimpleShaderSourceLoader implements ShaderSourceLoader {

    public static final ShaderSourceLoader INSTANCE = new SimpleShaderSourceLoader();

    @Override
    public String load(File file) {
        FileReader fr;
        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File not found: " + e);
        }
        BufferedReader reader = new BufferedReader(fr);
        String result = "";
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                result += line + "\n";
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Error while reading file: " + e);
        }
        return result;
    }
}
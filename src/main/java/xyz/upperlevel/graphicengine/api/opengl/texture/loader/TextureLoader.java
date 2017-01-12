package xyz.upperlevel.graphicengine.api.opengl.texture.loader;

import java.io.File;
import java.util.List;

public interface TextureLoader {

    List<TextureFormat> getSupportedExtensions();

    TextureContent load(File file);
}

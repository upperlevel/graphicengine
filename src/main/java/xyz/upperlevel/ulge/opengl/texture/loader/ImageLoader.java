package xyz.upperlevel.ulge.opengl.texture.loader;

import java.io.File;
import java.util.List;

public interface ImageLoader {

    List<ImageFormat> getSupportedExtensions();

    ImageContent load(File file);
}

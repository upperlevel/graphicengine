package xyz.upperlevel.graphicengine.api.opengl.texture.loader;

import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.upperlevel.graphicengine.api.util.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@NoArgsConstructor
public class TextureLoaderManager {

    public static final TextureLoaderManager DEFAULT = new TextureLoaderManager()
            .register(UniversalTextureLoader.INSTANCE);

    @Getter public final List<TextureLoader> loaders = new ArrayList<>();

    public Optional<TextureLoader> getLoader(TextureFormat format) {
        Objects.requireNonNull(format, "Format cannot be null.");
        return loaders.stream()
                .filter(loader -> {
                    for (TextureFormat fmt : loader.getSupportedExtensions())
                        for (String ext1 : fmt.getFormats())
                            for (String ext2 : format.getFormats())
                                if (ext1.equalsIgnoreCase(ext2))
                                    return true;
                    return false;
                }).findAny();
    }

    public TextureContent load(File file) {
        return load(file, new TextureFormat(FileUtil.getExtension(file)));
    }

    public TextureContent load(File file, TextureFormat format) {
        Optional<TextureLoader> optLdr = getLoader(format);
        if (!optLdr.isPresent()) {
            throw new IllegalStateException("Any loader found for that format.");
        }
        return optLdr.get().load(file);
    }

    public TextureLoaderManager register(TextureLoader loader) {
        Objects.requireNonNull(loader, "Loader cannot be null.");
        loaders.add(loader);
        return this;
    }

    public boolean unregister(TextureLoader loader) {
        return loaders.remove(loader);
    }
}
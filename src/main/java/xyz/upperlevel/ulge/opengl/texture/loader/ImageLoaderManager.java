package xyz.upperlevel.ulge.opengl.texture.loader;

import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.upperlevel.ulge.util.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@NoArgsConstructor
public class ImageLoaderManager {

    public static final ImageLoaderManager DEFAULT = new ImageLoaderManager()
            .register(UniversalImageLoader.INSTANCE);

    @Getter
    private final List<ImageLoader> loaders = new ArrayList<>();

    public Optional<ImageLoader> getLoader(ImageFormat format) {
        Objects.requireNonNull(format, "Format cannot be null.");
        return loaders.stream()
                .filter(loader -> {
                    for (ImageFormat fmt : loader.getSupportedExtensions())
                        for (String ext1 : fmt.getFormats())
                            for (String ext2 : format.getFormats())
                                if (ext1.equalsIgnoreCase(ext2))
                                    return true;
                    return false;
                }).findAny();
    }

    public ImageContent load(File file) {
        return load(file, new ImageFormat(FileUtil.getExtension(file)));
    }

    public ImageContent load(File file, ImageFormat format) {
        Optional<ImageLoader> optLdr = getLoader(format);
        if (!optLdr.isPresent()) {
            throw new IllegalStateException("Any loader found for that format.");
        }
        return optLdr.get().load(file);
    }

    public ImageLoaderManager register(ImageLoader loader) {
        Objects.requireNonNull(loader, "Loader cannot be null.");
        loaders.add(loader);
        return this;
    }

    public boolean unregister(ImageLoader loader) {
        return loaders.remove(loader);
    }
}
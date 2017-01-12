package xyz.upperlevel.game.launcher.api.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.IOException;

@RequiredArgsConstructor
public class ResourceLocator implements Resource {

    @Getter
    public final String path;

    public ResourceLocator(File folder) {
        if (folder.exists() && !folder.isDirectory())
            throw new IllegalArgumentException("A resource locator must be a dir");
        path = folder.getPath();
    }

    @Override
    public File getFile() {
        return new File(path);
    }

    @Override
    public void create() throws IOException {
        getFile().mkdirs();
    }
}

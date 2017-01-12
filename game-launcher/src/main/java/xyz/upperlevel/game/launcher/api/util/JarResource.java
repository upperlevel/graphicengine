package xyz.upperlevel.game.launcher.api.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import xyz.upperlevel.core.util.WhoCalled;

import java.io.File;
import java.io.IOException;

@RequiredArgsConstructor
public class JarResource implements Resource {

    @Getter
    public final ResourceLocator base;

    @Getter
    public final String path;

    @Override
    public File getFile() {
        return new File(base.getFile(), path);
    }

    public String getFilePath() {
        return getFile().getPath();
    }

    @Override
    public void create() throws IOException {
        File f = getFile();
        f.getParentFile().mkdirs();
        f.createNewFile();
        FileUtils.copyInputStreamToFile(WhoCalled.getCallingClass().getClassLoader().getResourceAsStream(path), f);
    }
}
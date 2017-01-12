package xyz.upperlevel.game.launcher.api.util;

import java.io.File;
import java.io.IOException;

public interface Resource {

    String getPath();

    File getFile();

    void create() throws IOException;
}

package xyz.upperlevel.game.launcher.api;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.io.FileUtils;
import xyz.upperlevel.game.launcher.api.util.ResourceLocator;

import java.io.File;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GELResource {

    public static final ResourceLocator
            UPPERLEVEL_FOLDER = new ResourceLocator(new File(FileUtils.getUserDirectory(), ".upperlevel")),
            GRAPHICENGINE_FOLDER = new ResourceLocator(new File(UPPERLEVEL_FOLDER.getFile(), "graphicengine")),
            GAMES_FOLDER = new ResourceLocator(new File(GRAPHICENGINE_FOLDER.getFile(), "games"));
}
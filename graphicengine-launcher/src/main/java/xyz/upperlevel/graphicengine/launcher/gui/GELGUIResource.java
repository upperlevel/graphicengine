package xyz.upperlevel.graphicengine.launcher.gui;

import lombok.RequiredArgsConstructor;
import xyz.upperlevel.graphicengine.launcher.api.GELResource;
import xyz.upperlevel.graphicengine.launcher.api.util.JarResource;
import xyz.upperlevel.graphicengine.launcher.api.util.ResourceLocator;

import java.io.File;
import java.io.IOException;

@RequiredArgsConstructor
public final class GELGUIResource {

    public static final ResourceLocator
            PATH = new ResourceLocator(new File(GELResource.GRAPHICENGINE_FOLDER.getFile(), "gui"));

    public static final JarResource
            PRESENTATION_HTML = new JarResource(PATH, "presentation.html"),
            DARK_STYLE        = new JarResource(PATH, "style/darkstyle.css"),
            LIGHT_STYLE       = new JarResource(PATH, "style/lightstyle.css");

    public static void save() throws IOException {
        PRESENTATION_HTML.create();
        DARK_STYLE       .create();
        LIGHT_STYLE      .create();
    }
}
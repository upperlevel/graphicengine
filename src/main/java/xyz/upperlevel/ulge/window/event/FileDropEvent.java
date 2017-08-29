package xyz.upperlevel.ulge.window.event;

import lombok.Getter;
import xyz.upperlevel.ulge.window.Window;

import java.io.File;

@Getter
public class FileDropEvent extends WindowEvent {
    private final File[] files;

    public FileDropEvent(Window window, File[] files) {
        super(window);
        this.files = files;
    }
}

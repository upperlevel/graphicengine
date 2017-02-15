package xyz.upperlevel.ulge.gui;

import java.util.Queue;

public interface GuiManager {
    boolean openGui(Gui gui);

    boolean hasGui();

    boolean closeGui();

    boolean changeGui(Gui gui);

    boolean backGui();

    Queue<Gui> getHistory();

    void render();
}

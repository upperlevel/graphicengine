package xyz.upperlevel.ulge.gui;

public class SingleGuiViewer extends GuiViewer {

    @Override
    public void open(Gui gui) {
        if (!container.isEmpty()) {
            Gui oldGui = get();
            // Trying to open the gui that is already open
            if (gui == oldGui) return;
            close(oldGui);
        }
        super.open(gui);
    }

    public Gui get() {
        return container.isEmpty() ? null : container.getAll().get(0);
    }

    public void close() {
        close(get());
    }
}

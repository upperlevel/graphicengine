package xyz.upperlevel.ulge.gui.impl;

import xyz.upperlevel.ulge.gui.BaseGui;
import xyz.upperlevel.ulge.gui.GuiBackground;
import xyz.upperlevel.ulge.gui.GuiRenderer;
import xyz.upperlevel.utils.event.EventManager;

import java.util.function.Function;
import java.util.function.Supplier;

public class Pane extends BaseGui {
    public static GuiBackground DEF_BACKGROUND = GuiBackground.transparent();

    private final Supplier<GuiBackground> bkg;

    public Pane(EventManager manager, Supplier<GuiBackground> bkg) {
        super(manager);
        this.bkg = bkg;
    }

    public Pane(Supplier<GuiBackground> bkg) {
        this.bkg = bkg;
    }

    public Pane(Function<Pane, GuiBackground> bkg) {
        this.bkg = () -> bkg.apply(this);
    }

    public Pane(GuiBackground bkg){
        this(() -> bkg);
    }

    public Pane() {
        this(() -> DEF_BACKGROUND);
    }

    @Override
    public void draw(GuiRenderer renderer) {
        bkg.get().apply(renderer);
        renderer.fill();
    }
}

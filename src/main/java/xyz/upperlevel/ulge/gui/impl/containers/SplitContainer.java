package xyz.upperlevel.ulge.gui.impl.containers;

import lombok.Getter;
import lombok.Setter;
import xyz.upperlevel.ulge.gui.Bounds;
import xyz.upperlevel.ulge.gui.Gui;
import xyz.upperlevel.utils.event.EventManager;
import xyz.upperlevel.utils.event.impl.SimpleEventManager;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class SplitContainer extends Container {
    public static final Function<SplitContainer, Float> HALF = (c) -> 0.5f;

    @Getter
    @Setter
    private Function<SplitContainer, Float> leftPerc;

    private final List<GuiData> guis;

    public SplitContainer(Gui left, Gui right, Function<SplitContainer, Float> leftPerc, EventManager manager) {
        super(manager);
        this.leftPerc = leftPerc;
        final float perc = leftPerc.apply(this);
        this.guis = Arrays.asList(
                toData(left , leftBound (perc)),
                toData(right, rightBound(1f - perc))
        );
    }

    public SplitContainer(Gui left, Gui right, Function<SplitContainer, Float> leftPerc) {
        this(left, right, leftPerc, new SimpleEventManager());
    }

    public SplitContainer(Gui left, Gui right) {
        this(left, right, HALF, new SimpleEventManager());
    }

    public Gui getLeftGui() {
        return guis.get(0).handle;
    }

    public void setLeftGui(Gui gui) {
        final float perc = leftPerc.apply(this);

        this.guis.get(0).bounds = leftBound(perc);
        this.guis.set(1, toData(gui, rightBound(1f - perc)));
    }


    public Gui getRightGui() {
        return guis.get(1).handle;
    }

    public void setRightGui(Gui gui) {
        final float perc = leftPerc.apply(this);

        this.guis.set(0, toData(gui, leftBound(perc)));
        this.guis.get(1).bounds = rightBound(1f - perc);
    }

    public void reload() {
        final float perc = leftPerc.apply(this);
        guis.get(0).bounds = leftBound (perc);
        guis.get(1).bounds = rightBound(1f - perc);
    }

    public GuiData toData(Gui gui, Bounds bounds) {
        return new GuiData(gui, bounds);
    }


    @Override
    public Iterable<GuiData> guis() {
        return guis;
    }

    public static Bounds leftBound(float perc) {
        return new Bounds(0f, 0f, perc, 1f);
    }

    public static Bounds rightBound(float perc) {
        return new Bounds(perc, 0f, 1f, 1f);
    }
}

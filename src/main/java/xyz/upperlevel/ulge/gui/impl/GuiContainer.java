package xyz.upperlevel.ulge.gui.impl;

import lombok.NonNull;
import lombok.experimental.Accessors;
import org.joml.Matrix4f;
import xyz.upperlevel.ulge.gui.Gui;
import xyz.upperlevel.ulge.gui.GuiRenderer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GuiContainer extends Gui {

    private List<Gui> guis;

    public GuiContainer() {
        this(new ArrayList<>());
    }

    public GuiContainer(Gui... guis) {
        this(Arrays.asList(guis));
    }

    public GuiContainer(@NonNull List<Gui> guis) {
        this.guis = guis;
    }

    public void add(Gui gui) {
        guis.add(gui);
    }

    public void remove(Gui gui) {
        guis.remove(gui);
    }

    public void clear() {
        guis.clear();
    }

    @Override
    public boolean onCursorEnter(double x, double y) {
        if (!super.onCursorEnter(x, y))
            return false;
        for (Gui gui : guis) {
            double relX = x - gui.getX();
            double relY = y - gui.getY();

            gui.onCursorEnter(relX, relY);
        }
        return true;
    }

    @Override
    public boolean onCursorMove(double startX, double startY, double endX, double endY) {
        if (!super.onCursorMove(startX, startY, endX, endY))
            return false;
        for (Gui gui : guis) {
            boolean wasIn = gui.isIn(startX, startY);
            boolean isIn  = gui.isIn(endX, endY);

            // if both positions are in the gui calls move event
            if (isIn && wasIn) {
                double relSx = startX - gui.getX();
                double relSy = startY - gui.getY();

                double relEx = endX - gui.getX();
                double relEy = endY - gui.getY();

                gui.onCursorMove(relSx, relSy, relEx, relEy);

                if (gui.isClicked())
                    gui.onDrag(startX, startY, endX, endY);
            }

            // if enter the gui
            if (!wasIn && isIn)
                gui.onCursorEnter(endX, endY);

            // if exit the gui
            if (wasIn && !isIn)
                gui.onCursorExit(endX, endY);
        }
        return true;
    }

    @Override
    public boolean onCursorExit(double x, double y) {
        if (!super.onCursorExit(x, y))
            return false;
        for (Gui gui : guis) {
            double relX = x - gui.getX();
            double relY = y - gui.getY();

            gui.onCursorExit(relX, relY);
        }
        return true;
    }

    @Override
    public boolean onClickBegin(double x, double y) {
        if (!super.onClickBegin(x, y))
            return false;
        for (Gui gui : guis) {
            if (gui.isIn(x, y)) {
                double relX = x - gui.getX();
                double relY = y - gui.getY();

                gui.onClickBegin(relX, relY);
            }
        }
        return true;
    }

    @Override
    public boolean onClickEnd(double x, double y) {
        if (!super.onClickEnd(x, y))
            return false;
        for (Gui gui : guis) {
            if (gui.isIn(x, y)) {
                double relX = x - gui.getX();
                double relY = y - gui.getY();

                gui.onClickEnd(relX, relY);
            }
        }
        return true;
    }

    @Override
    public boolean onOpen() {
        if (!super.onOpen())
            return false;
        for (Gui gui : guis)
            if (!gui.onOpen())
                return false;
        return true;
    }

    @Override
    public boolean onClose() {
        if (!super.onClose())
            return false;
        for (Gui gui : guis)
            if (!gui.onClose())
                return false;
        return true;
    }

    @Override
    public void render(Matrix4f transformation, GuiRenderer renderer) {
        super.render(transformation, renderer);
        guis.forEach(gui -> gui.render(transformation, renderer));
    }
}

package xyz.upperlevel.ulge.gui.impl.containers;

import org.joml.Vector2f;
import xyz.upperlevel.ulge.gui.BaseGui;
import xyz.upperlevel.ulge.gui.Gui;
import xyz.upperlevel.ulge.gui.GuiRenderer;
import xyz.upperlevel.utils.event.EventManager;
import xyz.upperlevel.utils.event.impl.SimpleEventManager;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UnionContainer extends BaseGui{

    private List<GuiData> guis;

    public UnionContainer(List<Gui> guis, EventManager manager) {
        super(manager);
        this.guis = guis.stream()
                .map(GuiData::new)
                .collect(Collectors.toList());
    }

    public UnionContainer(List<Gui> guis) {
        this(guis, new SimpleEventManager());
    }

    public UnionContainer(Gui... guis) {
        this(Arrays.asList(guis), new SimpleEventManager());
    }


    /**
     * Tries to add a Gui into the container, returns true only if the operation has succeeded
     * @param gui the Gui to add
     * @return <tt>true</tt> only if the operation has succeeded
     */
    public boolean add(Gui gui) {
        throw new UnsupportedOperationException();
    }

    /**
     * Tries to remove a Gui from the container, returns true only if the operation has succeeded
     * @param gui the Gui to remove
     * @return <tt>true</tt> only if the operation has succeeded
     */
    public boolean remove(Gui gui) {
        throw new UnsupportedOperationException();
    }

    public Iterable<GuiData> guis() {
        return guis;
    }

    @Override
    public void init(GuiRenderer r) {
        guis().forEach(g -> g.init(r));
    }

    @Override
    public boolean onMouseMove(Vector2f lastPos, Vector2f pos) {
        if (!super.onMouseMove(lastPos, pos))
            return false;
        for (GuiData gui : guis()) {
            if (!gui.handle.onMouseMove(lastPos, pos))
                return false;
        }
        return true;
    }

    @Override
    public boolean onClickBegin(Vector2f position) {
        if (!super.onClickBegin(position))
            return false;
        for (GuiData gui : guis()) {
            if (!gui.handle.onClickBegin(position))
                return false;
        }
        return true;
    }

    @Override
    public boolean onClickEnd(Vector2f position) {
        if (!super.onClickEnd(position))
            return false;
        for (GuiData gui : guis()) {
            if (!gui.handle.onClickEnd(position))
                return false;
        }
        return true;
    }

    @Override
    public boolean onMouseEnter(Vector2f enterPos) {
        if (!super.onMouseEnter(enterPos))
            return false;
        for (GuiData gui : guis()) {
            if (!gui.handle.onMouseEnter(enterPos))
                return false;
        }
        return true;
    }

    @Override
    public boolean onMouseExit(Vector2f lastPos) {
        if(!super.onMouseExit(lastPos))
            return false;
        for(GuiData gui : guis()) {
            if (!gui.handle.onMouseExit(lastPos))
                return false;
        }
        return true;
    }

    @Override
    public boolean onOpen() {
        if(!super.onOpen())
            return false;
        for(GuiData gui : guis())
            if (!gui.handle.onOpen())
                return false;
        return true;
    }

    @Override
    public boolean onClose() {
        if(!super.onClose())
            return false;
        for(GuiData gui : guis())
            if (!gui.handle.onClose())
                return false;
        return true;
    }

    @Override
    public boolean onChange(Gui other) {
        if(!super.onChange(other))
            return false;
        for(GuiData gui : guis())
            if (!gui.handle.onChange(other))
                return false;
        return true;
    }

    @Override
    public void draw(GuiRenderer renderer) {
        for(GuiData gui : guis())
            gui.draw(renderer);
    }

    public static class GuiData {
        public Gui handle;
        public boolean init = false;

        public GuiData(Gui handle) {
            this.handle = handle;
        }

        public void init(GuiRenderer r) {
            init = true;
            handle.init(r);
        }

        public void draw(GuiRenderer r) {
            if(!init) init(r);
            handle.draw(r);
        }
    }
}

package xyz.upperlevel.ulge.gui.impl.containers;

import org.joml.Vector2f;
import xyz.upperlevel.ulge.gui.BaseGui;
import xyz.upperlevel.ulge.gui.Bounds;
import xyz.upperlevel.ulge.gui.Gui;
import xyz.upperlevel.ulge.gui.GuiRenderer;
import xyz.upperlevel.utils.event.EventManager;

import static xyz.upperlevel.ulge.gui.Bounds.isNormal;

public abstract class Container extends BaseGui {

    public Container() {
        super();
    }

    public Container(EventManager manager) {
        super(manager);
    }


    /**
     * Tries to add a Gui into the container, returns true only if the operation has succeeded
     * @param gui the Gui to add
     * @param bounds the Bounds in which this Gui will be limited
     * @return <tt>true</tt> only if the operation has succeeded
     */
    public boolean add(Gui gui, Bounds bounds) {
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

    public abstract Iterable<GuiData> guis();


    @Override
    public void init(GuiRenderer r) {
        guis().forEach(g -> g.init(r));
    }

    @Override
    public boolean onMouseMove(Vector2f lastPos, Vector2f pos) {
        if(!super.onMouseMove(lastPos, pos))
            return false;
        for(GuiData gui : guis()) {
            Vector2f rel = gui.rel(pos);
            if(isNormal(rel)) {
                if(gui.lasPos == null)
                    if(!gui.handle.onMouseEnter(rel))
                        return false;
                if(!gui.handle.onMouseMove(gui.rel(lastPos), rel))
                    return false;
                gui.lasPos = rel;
            } else if(gui.lasPos != null) {
                if(!gui.handle.onMouseExit(gui.lasPos))
                    return false;
                gui.lasPos = null;
            }
        }
        return true;
    }

    @Override
    public boolean onClickBegin(Vector2f position) {
        if(!super.onClickBegin(position))
            return false;
        for(GuiData gui : guis()) {
            Vector2f rel = gui.rel(position);
            if(isNormal(rel)) {
                if (!gui.handle.onClickBegin(rel))
                    return false;
                gui.lastClick = rel;
            }
        }
        return true;
    }

    @Override
    public boolean onClickEnd(Vector2f position) {
        if(!super.onClickEnd(position))
            return false;
        for(GuiData gui : guis()) {
            Vector2f rel = gui.rel(position);
            if(isNormal(rel)) {
                if (!gui.handle.onClickEnd(rel))
                    return false;
                gui.lastClick = null;
            }
        }
        return true;
    }

    @Override
    public boolean onMouseEnter(Vector2f enterPos) {
        if(!super.onMouseEnter(enterPos))
            return false;
        for(GuiData gui : guis()) {
            Vector2f rel = gui.rel(enterPos);
            if(isNormal(rel)) {
                if (!gui.handle.onMouseEnter(rel))
                    return false;
                gui.lasPos = rel;
            }
        }
        return true;
    }

    @Override
    public boolean onMouseExit(Vector2f lastPos) {
        if(!super.onMouseExit(lastPos))
            return false;
        for(GuiData gui : guis()) {
            Vector2f rel = gui.rel(lastPos);
            if(isNormal(rel)) {
                if (!gui.handle.onMouseExit(rel))
                    return false;
                gui.lasPos = null;
            }
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

        public Bounds bounds;
        public Vector2f lasPos = null;
        public Vector2f lastClick = null;
        public boolean init = false;

        public GuiData(Gui handle, Bounds bounds) {
            this.handle = handle;
            this.bounds = bounds;
        }

        public Vector2f rel(Vector2f in) {
            return bounds.relative(in, new Vector2f());
        }

        public void init(GuiRenderer r) {
            init = true;
            handle.init(r);
        }

        public void draw(GuiRenderer r) {
            if(!init) init(r);
            r.pushAndSetBounds(bounds);
            handle.draw(r);
            r.popBounds();
        }
    }
}

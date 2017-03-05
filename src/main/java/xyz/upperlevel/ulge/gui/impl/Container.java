package xyz.upperlevel.ulge.gui.impl;

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

    public Container(Bounds bounds) {
        super(bounds);
    }

    public Container(Bounds bounds, EventManager manager) {
        super(bounds, manager);
    }


    public void add(Gui gui) {
        throw new UnsupportedOperationException();
    }

    public void remove(Gui gui) {
        throw new UnsupportedOperationException();
    }

    public abstract Iterable<GuiData> guis();


    @Override
    public void init(GuiRenderer r) {
        guis().forEach(g -> g.handle.init(r));
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
                if(!gui.handle.onMouseMove(lastPos, pos))
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
    public void render(GuiRenderer renderer) {
        for(GuiData gui : guis())
            gui.handle.draw(renderer);
    }

    public static class GuiData {
        public final Gui handle;

        public Vector2f lasPos = null;
        public Vector2f lastClick = null;

        public GuiData(Gui handle) {
            this.handle = handle;
        }

        public Vector2f rel(Vector2f in) {
            return handle.getBounds().relative(in, new Vector2f());
        }
    }
}

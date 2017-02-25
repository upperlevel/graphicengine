package xyz.upperlevel.ulge.gui;

import org.joml.Vector2f;

import static xyz.upperlevel.ulge.gui.Bounds.isNormal;

public class SimpleGuiWrapper {
    private final Gui gui;

    private boolean clicked = false;
    private Vector2f lastPos = null;

    public SimpleGuiWrapper(Gui gui) {
        this.gui = gui;
    }

    public void open() {
        gui.onOpen();
    }

    public void close() {
        gui.onClose();
        //reset
        clicked = false;
        lastPos = null;
    }


    public void clickBegin(Vector2f pos) {
        lastPos = rel(pos);
        clicked = true;
        gui.onClickBegin(new Vector2f(lastPos));
    }

    public void clickEnd(Vector2f pos) {
        lastPos = rel(pos);
        clicked = false;
        gui.onClickEnd(new Vector2f(lastPos));
    }

    public void move(Vector2f newPos) {
        newPos = rel(newPos);

        boolean isInside = isNormal(newPos);
        if(lastPos == null && !isInside)
            return;

        if(lastPos != null && lastPos.equals(newPos))
            return;

        Vector2f pos = lastPos;
        lastPos = new Vector2f(newPos);

        if(pos == null)
            gui.onMouseEnter(newPos);
        else
            gui.onMouseMove(pos, lastPos);

        if(clicked)
            gui.onDrag(pos, lastPos);

        if(!isInside) {
            gui.onMouseExit(pos);
            lastPos = null;
        }
    }

    private Vector2f rel(Vector2f abs) {
        return gui.getBounds().relative(abs, new Vector2f());
    }
}

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
        clicked = true;
        gui.onClickBegin(pos);
    }

    public void clickEnd(Vector2f pos) {
        clicked = false;
        gui.onClickEnd(pos);
    }

    public void move(Vector2f newPos) {
        boolean isInside = isNormal(newPos);
        if(lastPos == null && !isInside)
            return;

        if(lastPos != null && lastPos.equals(newPos))
            return;

        Vector2f pos = lastPos;
        lastPos = new Vector2f(newPos);

        if(pos == null)
            gui.onMouseEnter(newPos);
        else {
            gui.onMouseMove(pos, lastPos);

            if (clicked)
                gui.onDrag(pos, lastPos);
        }

        if(!isInside) {
            gui.onMouseExit(pos);
            lastPos = null;
        }
    }
}

package xyz.upperlevel.ulge.gui;

import org.joml.Vector2f;
import xyz.upperlevel.ulge.opengl.shader.Uniformer;

public interface GuiContainer {
    public void init(Uniformer u);

    public void draw(Uniformer u);

    /**
     * Called when the player's mouse is hovering the GUI
     * @param pos the position of the hover, from 0,0 (bottom left) to 1,1 (upper right) relative to the GUI
     */
    public void hover(Vector2f pos);

    /**
     * Called when the player clicks the GUI
     * @param pos the position of the click, from 0,0 (bottom left) to 1,1 (upper right) relative to the GUI
     */
    public void click(Vector2f pos);


    public void onOpen();

    public void onClose();
}

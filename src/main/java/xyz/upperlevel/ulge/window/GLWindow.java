package xyz.upperlevel.ulge.window;

import org.lwjgl.opengl.GL;

@SuppressWarnings("deprecation")
public abstract class GLWindow implements Window {

    protected abstract void makeContext();

    @Override
    public void contextualize() {
        makeContext();
        GL.createCapabilities();
    }
}
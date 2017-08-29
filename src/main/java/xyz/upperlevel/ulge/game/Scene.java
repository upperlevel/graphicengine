package xyz.upperlevel.ulge.game;

public interface Scene {
    void onEnable(Scene previous);

    void onDisable(Scene next);

    void onTick();

    void onFps();

    void onRender();
}

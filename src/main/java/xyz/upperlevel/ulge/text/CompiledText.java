package xyz.upperlevel.ulge.text;

import lombok.AllArgsConstructor;
import org.joml.Vector2f;

@AllArgsConstructor
public abstract class CompiledText<R extends TextRenderer> {
    public final SuperText text;
    private final R renderer;

    public abstract void render(Vector2f pos, float distance);
}

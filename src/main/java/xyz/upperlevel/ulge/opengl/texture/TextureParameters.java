package xyz.upperlevel.ulge.opengl.texture;


import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glTexParameteri;

public class TextureParameters {

    @Getter
    private Map<TextureParameter.Type, TextureParameter.Value> parameters = new HashMap<>();

    public TextureParameters() {
    }

    public TextureParameters(Map<TextureParameter.Type, TextureParameter.Value> initialParameters) {
        parameters.putAll(initialParameters);
    }

    public TextureParameters addParameter(TextureParameter.Type type, TextureParameter.Value value) {
        parameters.put(type, value);
        return this;
    }

    public boolean removeParameter(TextureParameter.Type type) {
        return parameters.remove(type) != null;
    }

    public TextureParameters setSWrapping(TextureParameter.Value sWrappingValue) {
        return addParameter(TextureParameter.Type.Wrapping.S, sWrappingValue);
    }

    public TextureParameters setTWrapping(TextureParameter.Value tWrappingValue) {
        return addParameter(TextureParameter.Type.Wrapping.T, tWrappingValue);
    }

    public TextureParameters setMinFilter(TextureParameter.Value minFilterValue) {
        return addParameter(TextureParameter.Type.Filter.MIN, minFilterValue);
    }

    public TextureParameters setMagFilter(TextureParameter.Value magFilterValue) {
        return addParameter(TextureParameter.Type.Filter.MAG, magFilterValue);
    }

    public void setup() {
        parameters.forEach((parType, parVal) -> glTexParameteri(GL_TEXTURE_2D, parType.getId(), parVal.getId()));
    }

    public static TextureParameters getEmpty() {
        return new TextureParameters();
    }

    public static TextureParameters getDefault() {
        return new TextureParameters(new HashMap<TextureParameter.Type, TextureParameter.Value>() {
            {
                put(TextureParameter.Type.Wrapping.S, TextureParameter.Value.Wrapping.CLAMP_TO_EDGE);
                put(TextureParameter.Type.Wrapping.T, TextureParameter.Value.Wrapping.CLAMP_TO_EDGE);
                put(TextureParameter.Type.Filter.MIN, TextureParameter.Value.Filter.LINEAR);
                put(TextureParameter.Type.Filter.MAG, TextureParameter.Value.Filter.LINEAR);
            }
        });
    }
}

package xyz.upperlevel.graphicengine.api.opengl.texture;


import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glTexParameteri;

@NoArgsConstructor
public class TextureParameters {

    public static final TextureParameters DEFAULTS = new TextureParameters(Collections.emptyList());

    @Getter public final List<TextureParameter> parameters = new ArrayList<>();

    private void initDefPar() {
        parameters.add(new TextureParameter(TextureParameter.Type.Wrapping.S, TextureParameter.Value.Wrapping.CLAMP_TO_EDGE));
        parameters.add(new TextureParameter(TextureParameter.Type.Wrapping.T, TextureParameter.Value.Wrapping.CLAMP_TO_EDGE));
        parameters.add(new TextureParameter(TextureParameter.Type.Filter.MIN, TextureParameter.Value.Filter.LINEAR));
        parameters.add(new TextureParameter(TextureParameter.Type.Filter.MAG, TextureParameter.Value.Filter.LINEAR));
    }

    private void addParSafely(TextureParameter toAdd) {
        parameters.removeIf(parameter -> parameter.getType() == toAdd.getType());
        parameters.add(toAdd);
    }

    public TextureParameters(List<TextureParameter> parameters) {
        initDefPar();
        parameters.forEach(this::addParSafely);
    }

    public TextureParameters setSWrapping(TextureParameter.Value wrapping) {
        return add(new TextureParameter(TextureParameter.Type.Wrapping.S, wrapping));
    }

    public TextureParameters setTWrapping(TextureParameter.Value wrapping) {
        return add(new TextureParameter(TextureParameter.Type.Wrapping.T, wrapping));
    }

    public TextureParameters setMinFilter(TextureParameter.Value filter) {
        return add(new TextureParameter(TextureParameter.Type.Filter.MIN, filter));
    }

    public TextureParameters setMagFilter(TextureParameter.Value filter) {
        return add(new TextureParameter(TextureParameter.Type.Filter.MAG, filter));
    }

    public TextureParameters add(TextureParameter parameter) {
        addParSafely(parameter);
        return this;
    }

    public void setup() {
        parameters.forEach(parameter -> glTexParameteri(GL_TEXTURE_2D, parameter.getType().getId(), parameter.getValue().getId()));
    }
}

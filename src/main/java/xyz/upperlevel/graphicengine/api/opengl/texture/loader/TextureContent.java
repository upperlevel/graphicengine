package xyz.upperlevel.graphicengine.api.opengl.texture.loader;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.nio.ByteBuffer;
import java.util.Objects;

public class TextureContent {

    @Getter private int width, height;
    @Getter private ByteBuffer data;

    public TextureContent(int width, int height, ByteBuffer data) {
        setWidth(width);
        setHeight(height);
        setData(data);
    }
    
    public void setWidth(int width) {
        if (width < 0)
            throw new IllegalArgumentException("Width cannot be negative.");
        this.width = width;
    }

    public void setHeight(int height) {
        if (height < 0)
            throw new IllegalArgumentException("Height cannot be negative.");
        this.height = height;
    }

    public void setData(ByteBuffer data) {
        Objects.requireNonNull(data, "Data cannot be null.");
        this.data = data;
    }
}

package xyz.upperlevel.ulge.text.impl.bitmap;

import lombok.Data;

@Data
public class CharData {
    public final float x, y, w, h;
    public float rx, ry, rw, rh;

    public final float ratio;

    public CharData(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.ratio = w/h;
    }

    public void setup(float width, float height) {
        rx = x/width;
        ry = y/height;
        rw = w/width;
        rh = h/height;
    }
}

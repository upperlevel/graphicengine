package xyz.upperlevel.graphicengine.api.simple;

import static java.lang.Integer.max;
import static java.lang.Integer.min;

public class SimpleColor {
    public static SimpleColor WHITE     = rgb(255, 255, 255);
    public static SimpleColor BLACK     = rgb(0  , 0  , 0  );
    public static SimpleColor RED       = rgb(255, 0  , 0  );
    public static SimpleColor GREEN     = rgb(0  , 255, 0  );
    public static SimpleColor BLUE      = rgb(0  , 0  , 255);


    public final int r, g, b, a;

    private byte[] data;

    public SimpleColor(int r, int g, int b, int a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public SimpleColor(int r, int g, int b) {
        this(r, g, b, 255);
    }

    public SimpleColor(float r, float g, float b, float a) {
        this((int)r*255, (int)g*255, (int)b*255, (int)a*255);
    }

    public SimpleColor(float r, float g, float b) {
        this(r, g, b, 1.0f);
    }

    public SimpleColor(double r, double g, double b, double a) {
        this((float)r, (float)g, (float)b, (float)a);
    }

    public SimpleColor(double r, double g, double b) {
        this((float)r, (float)g, (float)b);
    }

    public byte[] getData() {
        if(data == null) {
            data =  new byte[]{
                    (byte) r,
                    (byte) g,
                    (byte) b,
                    (byte) a
            };
        }
        return data;
    }

    public int getRed() {
        return r;
    }

    public int getGreen() {
        return g;
    }

    public int getBlue() {
        return b;
    }

    public int getOpacity() {
        return a;
    }

    public boolean isOpaque() {
        return a >= 255;
    }

    public SimpleColor add(SimpleColor other) {
        return new SimpleColor(
                min(255, this.r - other.r),
                min(255, this.g - other.g),
                min(255, this.b - other.b),
                min(255, this.a - other.a)
        );
    }

    public SimpleColor sub(SimpleColor other) {
        return new SimpleColor(
                max(0, this.r - other.r),
                max(0, this.g - other.g),
                max(0, this.b - other.b),
                max(0, this.a - other.a)
        );
    }

    public SimpleColor mul(SimpleColor other) {
        return new SimpleColor(
                this.r * other.r,
                this.g * other.g,
                this.b * other.b,
                this.a * other.a
        );
    }

    public SimpleColor div(SimpleColor other) {
        return new SimpleColor(
                min(255, this.r / other.r),
                min(255, this.g / other.g),
                min(255, this.b / other.b),
                min(255, this.a / other.a)
        );
    }

    public static SimpleColor rgb(int r, int g, int b) {
        return new SimpleColor(r, g, b);
    }

    public static SimpleColor rgba(int r, int g, int b, int a) {
        return new SimpleColor(r, g, b, a);
    }
}

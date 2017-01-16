package xyz.upperlevel.graphicengine.api.simple;

import static java.lang.Integer.max;
import static java.lang.Integer.min;

public class Color {
    public static Color WHITE = new Color(255, 255, 255);
    public static Color BLACK = new Color(0, 0, 0);



    public final int r, g, b, a;

    private byte[] data;

    public Color(int r, int g, int b, int a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public Color(int r, int g, int b) {
        this(r, g, b, 255);
    }

    public Color(float r, float g, float b, float a) {
        this((int)r*255, (int)g*255, (int)b*255, (int)a*255);
    }

    public Color(float r, float g, float b) {
        this(r, g, b, 1.0f);
    }

    public Color(double r, double g, double b, double a) {
        this((float)r, (float)g, (float)b, (float)a);
    }

    public Color(double r, double g, double b) {
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

    public Color add(Color other) {
        return new Color(
                min(255, this.r - other.r),
                min(255, this.g - other.g),
                min(255, this.b - other.b),
                min(255, this.a - other.a)
        );
    }

    public Color sub(Color other) {
        return new Color(
                max(0, this.r - other.r),
                max(0, this.g - other.g),
                max(0, this.b - other.b),
                max(0, this.a - other.a)
        );
    }

    public Color mul(Color other) {
        return new Color(
                this.r * other.r,
                this.g * other.g,
                this.b * other.b,
                this.a * other.a
        );
    }

    public Color div(Color other) {
        return new Color(
                min(255, this.r / other.r),
                min(255, this.g / other.g),
                min(255, this.b / other.b),
                min(255, this.a / other.a)
        );
    }
}

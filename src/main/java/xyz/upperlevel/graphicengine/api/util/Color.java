package xyz.upperlevel.graphicengine.api.util;

import static java.lang.Integer.max;
import static java.lang.Integer.min;

public class Color {

    public final int r, g, b, a;

    private byte[] data;

    private Color(int r, int g, int b, int a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
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

    public static Color rgb(int r, int g, int b) {
        return new Color(r, g, b, 255);
    }

    public static Color rgba(int r, int g, int b, int a) {
        return new Color(r, g, b, a);
    }

    public static Color rgb(float r, float g, float b) {
        return new Color((int)r*255, (int)g*255, (int)b*255, 255);
    }

    public static Color rgba(float r, float g, float b, float a) {
        return new Color((int)r*255, (int)g*255,(int)b*255, (int)a*255);
    }

    public static Color color(javafx.scene.paint.Color color) {
        return new Color((int)color.getRed()*255, (int)color.getGreen()*255,(int)color.getBlue()*255, (int)color.getOpacity()*255);
    }

    public static Color color(java.awt.Color color) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }

    public static Color web(String web) {
        return color(javafx.scene.paint.Color.web(web));
    }

    public static Color hex(String hex) {
        byte r, g, b, a = (byte) 255;
        if(hex.length() < 6)
            throw new IllegalArgumentException("Bad hex length!");
        char[] str = hex.toCharArray();

        r = fromHex(str, 0);
        g = fromHex(str, 2);
        b = fromHex(str, 4);

        if(hex.length() >= 8)
            a = fromHex(str, 6);
        return new Color(r, g, b, a);
    }

    private static byte fromHex(char[] in, int index) {
        return (byte) ((Character.digit(in[index], 16) << 4) + Character.digit(in[index + 1], 16));
    }
}

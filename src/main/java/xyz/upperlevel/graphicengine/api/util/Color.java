package xyz.upperlevel.graphicengine.api.util;

public class Color {

    public final float r, g, b, a;

    private Color(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    private byte[] data = null;

    public byte[] getData() {
        if (data == null) {
            data = new byte[]{
                    (byte) getRedAsInt(),
                    (byte) getBlueAsInt(),
                    (byte) getGreenAsInt(),
                    (byte) getOpacityAsInt()
            };
        }
        return data;
    }

    public float getRed() {
        return r;
    }

    public int getRedAsInt() {
        return (int) (r * 255);
    }

    public float getGreen() {
        return g;
    }

    public int getGreenAsInt() {
        return (int) (g * 255);
    }

    public float getBlue() {
        return b;
    }

    public int getBlueAsInt() {
        return (int) (b * 255);
    }

    public float getOpacity() {
        return a;
    }

    public int getOpacityAsInt() {
        return (int) (a * 255);
    }

    public boolean isOpaque() {
        return a >= 255;
    }

    public Color add(Color other) {
        return rgba(
                r + other.r,
                g + other.g,
                b + other.b,
                a + other.a
        );
    }

    public Color sub(Color other) {
        return rgba(
                r - other.r,
                g - other.g,
                b - other.b,
                a - other.a
        );
    }

    public Color mul(Color other) {
        return rgba(
                r * other.r,
                g * other.g,
                b * other.b,
                a * other.a
        );
    }

    public Color div(Color other) {
        return rgba(
                r / other.r,
                g / other.g,
                b / other.b,
                a / other.a
        );
    }

    public static Color rgb(int r, int g, int b) {
        return rgba(r, g, b, 255);
    }

    public static Color rgba(int r, int g, int b, int a) {
        return new Color(r / 255f, g / 255f, b / 255f, a / 255f);
    }

    public static Color rgb(float r, float g, float b) {
        return rgba(r, g, b, 1f);
    }

    private static float normalizeColorValue(float v) {
        return v < 0 ? 0 : (v > 1f ? 1f : v);
    }

    public static Color rgba(float r, float g, float b, float a) {
        return new Color(normalizeColorValue(r), normalizeColorValue(g), normalizeColorValue(b), normalizeColorValue(a));
    }

    public static Color color(javafx.scene.paint.Color color) {
        return new Color((float) color.getRed(), (float) color.getGreen(), (float) color.getBlue(), (float) color.getOpacity());
    }

    public static Color color(java.awt.Color color) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }

    public static Color web(String web) {
        return color(javafx.scene.paint.Color.web(web));
    }

    private static byte fromHex(char[] in, int index) {
        return (byte) ((Character.digit(in[index], 16) << 4) + Character.digit(in[index + 1], 16));
    }

    public static Color hex(String hex) {
        byte r, g, b, a = (byte) 255;
        if (hex.length() < 6)
            throw new IllegalArgumentException("Bad hex length!");
        char[] str = hex.toCharArray();

        r = fromHex(str, 0);
        g = fromHex(str, 2);
        b = fromHex(str, 4);

        if (hex.length() >= 8)
            a = fromHex(str, 6);
        return rgba((int) r, (int) g, (int) b, (int) a);
    }
}

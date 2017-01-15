package xyz.upperlevel.graphicengine.api.util;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Color {

    public static final Color BLACK = new Color(0f, 0f, 0f, 1f);
    public static final Color WHITE = new Color(1f, 1f, 1f, 1f);
    public static final Color RED = new Color(1f, 0f, 0f, 1f);
    public static final Color GREEN = new Color(0f, 1f, 0f, 1f);
    public static final Color BLUE = new Color(0f, 0f, 1f, 1f);

    public static final int INT_MIN_VAL = 0;
    public static final int INT_MAX_VAL = 255;

    public static int normIntColorVal(int val) {
        if (val < INT_MIN_VAL)
            return INT_MIN_VAL;
        else if (val > INT_MAX_VAL)
            return INT_MAX_VAL;
        return val;
    }

    public static final float FLOAT_MIN_VAL = 0f;
    public static final float FLOAT_MAX_VAL = 1f;

    public static float normFloatColorVal(float val) {
        if (val < FLOAT_MIN_VAL)
            return FLOAT_MIN_VAL;
        else if (val > FLOAT_MAX_VAL)
            return FLOAT_MAX_VAL;
        return val;
    }

    @Getter private float
            redF = 0f,
            greenF = 0f,
            blueF = 0f,
            alphaF = 1f;

    public Color(float red, float green, float blue, float alpha) {
        setRed(red);
        setGreen(green);
        setBlue(blue);
        setAlpha(alpha);
    }

    public Color(int red, int green, int blue, int alpha) {
        setRed(red);
        setGreen(green);
        setBlue(blue);
        setAlpha(alpha);
    }

    public void setRed(float red) {
        redF = normFloatColorVal(red);
    }

    public void setGreen(float green) {
        greenF = normFloatColorVal(green);
    }

    public void setBlue(float blue) {
        blueF = normFloatColorVal(blue);
    }

    public void setAlpha(float alpha) {
        alphaF = normFloatColorVal(alpha);
    }

    public void setRed(int red) {
        redF = ((float) normIntColorVal(red)) / FLOAT_MAX_VAL;
    }

    public void setGreen(int green) {
        greenF = ((float) normIntColorVal(green)) / FLOAT_MAX_VAL;
    }

    public void setBlue(int blue) {
        blueF = ((float) normIntColorVal(blue)) / FLOAT_MAX_VAL;
    }

    public void setAlpha(int alpha) {
        alphaF = ((float) normIntColorVal(alpha)) / FLOAT_MAX_VAL;
    }
}
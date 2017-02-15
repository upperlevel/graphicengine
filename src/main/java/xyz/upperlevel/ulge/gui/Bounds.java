package xyz.upperlevel.ulge.gui;

import org.joml.Vector2f;

public class Bounds {
    public static final Bounds FULL = new Bounds(0.0f, 0.0f, 1.0f, 1.0f);

    public final float minX, maxX, minY, maxY;

    private final int hashCode;

    private String str = null;

    public Bounds(float minX, float minY, float maxX, float maxY) {
        if(minX > maxX) {
            float t = minX;
            minX = maxX;
            maxX = t;
        }
        if(minY > maxY) {
            float t = minY;
            minY = maxY;
            maxY = t;
        }
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;

        //Some test lead to take those part of the float as the most significant for low precision calculations
        //For the values of the float between 0 and 1 (with intervals of 0.1) the first 5 bits didn't change, so this is wat remains
        this.hashCode =
                (Float.floatToRawIntBits(minX) >> 19) & 0x000000FF |
                (Float.floatToRawIntBits(maxX) >> 11) & 0x0000FF00 |
                (Float.floatToRawIntBits(minY) >>  3) & 0x00FF0000 |
                (Float.floatToRawIntBits(maxY) <<  5) & 0xFF000000;
    }

    public Bounds(Bounds original) {
        this.minX = original.minX;
        this.maxX = original.maxX;
        this.minY = original.minY;
        this.maxY = original.maxY;
        this.hashCode = original.hashCode;
        this.str = original.str;
    }

    public boolean isInside(float x, float y) {//AABB
        return  x >= minX && x <= maxX &&
                y >= minY && y <= maxY;
    }

    public boolean isInside(Vector2f point) {
        return isInside(point.x, point.y);
    }

    public boolean collides(Bounds o) {
        return
                        (o.maxX >= this.minX && o.minX <= this.maxX) &&
                        (o.maxY >= this.minY && o.minY <= this.minY);
    }

    public Bounds translate(float x, float y) {
        return new Bounds(
                minX + x,
                minY + y,
                maxX + x,
                maxY + y
        );
    }

    public Vector2f relative(Vector2f pos, Vector2f dest) {
        dest.x = (pos.x - minX)/(maxX - minX);
        dest.y = (pos.y - minY)/(maxY - minY);
        return dest;
    }

    public Vector2f relative(Vector2f pos) {
        pos.x = (pos.x - minX)/(maxX - minX);
        pos.y = (pos.y - minY)/(maxY - minY);
        return pos;
    }

    public Bounds shrink(Bounds other) {
        float dx = maxX - minX;
        float dy = maxY - minY;

        return new Bounds(
                minX + dx * other.minX,
                minY + dy * other.minY,
                minX + dx * other.maxX,
                minY + dy * other.maxY
        );
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

    @Override
    public boolean equals(Object other) {
        if(other == this)
            return true;
        if(other instanceof Bounds) {
            Bounds o = (Bounds) other;
            return  this.minX == o.minX &&
                    this.maxX == o.maxX &&
                    this.minY == o.minY &&
                    this.maxY == o.maxY;
        } else return false;
    }

    @Override
    public String toString() {
        if(str == null)
            str = "[" + minX + "," + minY + "]->[" + maxX + "," + maxY + "]";
        return str;
    }

    public static boolean isNormal(Vector2f vec) {
        return vec.x >= 0f && vec.y >= 0f && vec.x <= 1f && vec.y <= 1f;
    }
}

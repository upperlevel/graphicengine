package xyz.upperlevel.ulge.gui;

import org.joml.Vector2f;

public class Bounds {
    public static final Bounds FULL = new Bounds(0.0f, 0.0f, 1.0f, 1.0f);


    public final float minX, maxX, minY, maxY;

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
    }

    public Bounds(Bounds original) {
        this.minX = original.minX;
        this.maxX = original.maxX;
        this.minY = original.minY;
        this.maxY = original.maxY;
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
}

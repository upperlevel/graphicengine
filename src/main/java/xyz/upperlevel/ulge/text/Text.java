package xyz.upperlevel.ulge.text;

import lombok.Getter;
import lombok.Setter;

public class Text {

    @Getter
    @Setter
    private double originX, originY;

    @Getter
    @Setter
    private double x, y, width, height;

    @Getter
    @Setter
    private SuperText text = new SuperText();

    public Text() {
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setSize(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public void setOrigin(double originX, double originY) {
        this.originX = originX;
        this.originY = originY;
    }

    public void setOrigin(TextOrigin origin) {
        setOrigin(origin.getX(), origin.getY());
    }
}

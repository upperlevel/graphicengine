package xyz.upperlevel.ulge.text;

import lombok.Getter;
import lombok.NonNull;

import java.awt.image.BufferedImage;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

public class CharData {

    public static final CharData NULL = new CharData(
            '\0',
            0,
            0,
            new BufferedImage(1, 1, TYPE_INT_ARGB)
    );

    @Getter
    private char character;

    @Getter
    private int width, height; // char size in pixels

    @Getter
    private BufferedImage image;

    public CharData(char character, int width, int height, @NonNull BufferedImage image) {
        this.character = character;
        this.width     = width;
        this.height    = height;
        this.image     = image;
    }
}

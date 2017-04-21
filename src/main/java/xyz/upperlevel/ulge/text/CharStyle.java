package xyz.upperlevel.ulge.text;

import lombok.Getter;

import java.awt.*;

public enum CharStyle {

    PLAIN (Font.PLAIN),
    BOLD  (Font.BOLD),
    ITALIC(Font.ITALIC);

    @Getter
    private int awtStyle;

    CharStyle(int awtStyle) {
        this.awtStyle = awtStyle;
    }
}

package xyz.upperlevel.ulge.text;

import lombok.Getter;
import lombok.Setter;
import xyz.upperlevel.ulge.util.Color;

public class TextPiece {

    @Getter
    @Setter
    private String text = "";

    @Getter
    @Setter
    private Color color = Color.WHITE;

    @Getter
    @Setter
    private boolean
            bold          = false,
            italic        = false,
            strikethrough = false;

    public TextPiece() {
    }

    public TextPiece(String text) {
        this.text = text;
    }

    public TextPiece(String text, Color color) {
        this.text = text;
        this.color = color;
    }

    public TextPiece(String text, Color color, boolean bold, boolean italic, boolean strikethrough) {
        this.text = text;
        this.color = color;

        this.bold = bold;
        this.italic = italic;
        this.strikethrough = strikethrough;
    }
}
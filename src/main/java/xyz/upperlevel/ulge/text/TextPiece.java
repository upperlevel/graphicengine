package xyz.upperlevel.ulge.text;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import xyz.upperlevel.ulge.util.Color;

@Data
@Accessors(fluent = true)
@Builder
public class TextPiece {
    public final String text;
    public final Color color;
    public final boolean italic, bold, strikeThrough;

    public TextPiece(String text, Color color, boolean italic, boolean bold, boolean strikeThrough) {
        this.text = text;
        this.color = color;
        this.italic = italic;
        this.bold = bold;
        this.strikeThrough = strikeThrough;
    }

    public TextPiece(String text, Color color) {
        this(text, color, false, false, false);
    }

    public TextPiece(String text) {
        this(text, Color.WHITE, false, false, false);
    }

    public static TextPiece of(String text) {
        return new TextPiece(text);
    }

    public static TextPiece of(String text, Color color) {
        return new TextPiece(text, color);
    }

    public static TextPiece text(String text) {
        return new TextPiece(text);
    }

    public static class TextPieceBuilder {
        public Color color = Color.WHITE;
        public String text = "";
        //The boolean vars are false by default
    }
}

package xyz.upperlevel.ulge.text;

import lombok.Data;
import lombok.experimental.Accessors;
import xyz.upperlevel.ulge.util.Color;

@Data
@Accessors(fluent = true)
public class TextPiece {
    public String text;
    public boolean italic, bold, strikeThrough;
    public Color color = Color.WHITE;

    public TextPiece() {
    }

    public TextPiece(String text) {
        this.text = text;
    }
}

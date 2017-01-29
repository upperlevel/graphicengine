package xyz.upperlevel.ulge.text;

import java.util.List;

public interface SuperText {
    public List<TextPiece> asList();

    public static ArraySuperText of(TextPiece... piece) {
        return new ArraySuperText(piece);
    }

    public static ArraySuperText of(String str) {
        return new ArraySuperText(new TextPiece().text(str));
    }
}

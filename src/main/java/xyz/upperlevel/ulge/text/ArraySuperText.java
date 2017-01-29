package xyz.upperlevel.ulge.text;

import java.util.Arrays;
import java.util.List;

public class ArraySuperText implements SuperText {
    public TextPiece[] pieces;

    public ArraySuperText(TextPiece... pieces) {
        this.pieces = pieces;
    }

    @Override
    public List<TextPiece> asList() {
        return Arrays.asList(pieces);
    }
}

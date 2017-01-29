package xyz.upperlevel.ulge.text;

import java.util.Arrays;
import java.util.List;

public class ListSuperText implements SuperText {
    public List<TextPiece> pieces;


    public ListSuperText(List<TextPiece> pieces) {
        this.pieces = pieces;
    }

    public ListSuperText(TextPiece... pieces) {
        this.pieces = Arrays.asList(pieces);
    }

    @Override
    public List<TextPiece> asList() {
        return pieces;
    }
}

package xyz.upperlevel.ulge.text;

import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class SuperText {

    @Getter
    private List<TextPiece> pieces = new ArrayList<>();

    public SuperText() {
    }

    public void add(@NonNull TextPiece piece) {
        pieces.add(piece);
    }

    public void remove(@NonNull TextPiece piece) {
        pieces.remove(piece);
    }
}

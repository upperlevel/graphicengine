package xyz.upperlevel.ulge.text;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class CompiledText {

    @Getter
    private List<TextPiece> lines = new ArrayList<>();

    public CompiledText() {
    }

    public void add(TextPiece piece) {
        lines.add(piece);
    }

    public void remove(int line) {
        lines.remove(line);
    }
}

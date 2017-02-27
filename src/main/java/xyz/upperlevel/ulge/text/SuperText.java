package xyz.upperlevel.ulge.text;

import xyz.upperlevel.ulge.util.ArrayUnmodifiableIterator;

import java.util.*;
import java.util.function.Consumer;

public class SuperText implements Iterable<TextPiece>{
    private final TextPiece[] pieces;
    private List<TextPiece> view;

    public SuperText(TextPiece... pieces) {
        this.pieces = pieces.clone();

    }

    public SuperText(List<TextPiece> pieces) {
        this(pieces.toArray(new TextPiece[0]));
    }


    public List<TextPiece> asList() {
        if (view == null)
            view = Collections.unmodifiableList(Arrays.asList(pieces));
        return view;
    }

    @Override
    public Iterator<TextPiece> iterator() {
        return new ArrayUnmodifiableIterator<>(pieces);
    }

    @Override
    public void forEach(Consumer<? super TextPiece> action) {
        for(TextPiece piece : pieces)
            action.accept(piece);
    }

    @Override
    public Spliterator<TextPiece> spliterator() {
        return asList().spliterator();
    }



    public static SuperText of(TextPiece... piece) {
        return new SuperText(piece);
    }

    public static SuperText of(String str) {
        return new SuperText(TextPiece.of(str));
    }
}

package xyz.upperlevel.ulge.text.impl.bitmap.chardata;

import xyz.upperlevel.ulge.text.impl.bitmap.CharData;
import xyz.upperlevel.ulge.text.impl.bitmap.CharDataManager;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

public class StandardCharDataManager implements CharDataManager {
    public final CharData[] array;
    private final List<Character> chars;

    public StandardCharDataManager(int length) {
        this.array = new CharData[length];
        chars = Arrays.asList(IntStream.range(0, length).mapToObj(i -> (char)i).toArray(Character[]::new));
    }

    public StandardCharDataManager() {
        this(128);
    }

    @Override
    public CharData get(char c) {
        return array[c];
    }

    @Override
    public void set(char c, CharData data) {
        array[c] = data;
    }

    @Override
    public Iterator<Character> iterator() {
        return chars.iterator();
    }

    @Override
    public Iterator<CharData> datas() {
        return Arrays.asList(array).iterator();
    }

    @Override
    public int size() {
        return array.length;
    }
}

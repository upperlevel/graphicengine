package xyz.upperlevel.ulge.text.impl.truetype;

import java.util.Iterator;
import java.util.stream.StreamSupport;

public interface CharDataManager extends Iterable<Character>{

    public CharData get(char c);

    public void set(char c, CharData data);

    @Override
    public Iterator<Character> iterator();

    public default Iterator<CharData> datas() {
        return StreamSupport.stream(this.spliterator(), false)
                .map(this::get)
                .iterator();
    }

    int size();
}

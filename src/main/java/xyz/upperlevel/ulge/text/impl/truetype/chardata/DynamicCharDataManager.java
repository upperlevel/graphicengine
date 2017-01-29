package xyz.upperlevel.ulge.text.impl.truetype.chardata;

import xyz.upperlevel.ulge.text.impl.truetype.CharData;
import xyz.upperlevel.ulge.text.impl.truetype.CharDataManager;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DynamicCharDataManager implements CharDataManager {

    private Map<Character, CharData> map;
    private final Collection<Character> chars;

    public DynamicCharDataManager(Collection<Character> chars) {
        this.chars = chars;
        map = new HashMap<>();
    }


    @Override
    public CharData get(char c) {
        return map.get(c);
    }

    @Override
    public void set(char c, CharData data) {
        map.put(c, data);
    }

    @Override
    public Iterator<Character> iterator() {
        return chars.iterator();
    }

    @Override
    public Iterator<CharData> datas() {
        return map.values().iterator();
    }

    @Override
    public int size() {
        return map.size();
    }
}

package xyz.upperlevel.ulge.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MapBuilder<M extends Map<K, V>, K, V> {
    private final M map;

    public MapBuilder(M map) {
        this.map = map;
    }

    public MapBuilder<M, K, V> put(K key, V v) {
        map.put(key, v);
        return this;
    }

    public M build() {
        return map;
    }

    public static <M extends Map<K, V>, K, V> MapBuilder<M, K, V> of(M map) {
        return new MapBuilder<>(map);
    }

    public static <K, V> MapBuilder<HashMap<K, V>, K, V> hashmap() {
        return new MapBuilder<>(new HashMap<K, V>());
    }

    public static <K, V> MapBuilder<Map<K, V>, K, V> immutable() {
        return new MapBuilder<Map<K, V>, K, V>(new HashMap<>()) {
            public Map<K, V> build() {
                return Collections.unmodifiableMap(super.build());
            }
        };
    }

}

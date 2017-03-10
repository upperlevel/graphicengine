package xyz.upperlevel.ulge.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MapBuilder<K, V> {
    private final Map<K, V> map;

    public MapBuilder(Map<K, V> map) {
        this.map = map;
    }

    public MapBuilder<K, V> put(K key, V v) {
        map.put(key, v);
        return this;
    }

    public Map<K, V> build() {
        return map;
    }

    public static <M extends Map<K, V>, K, V> MapBuilder<K, V> of(M map) {
        return new MapBuilder<>(map);
    }

    public static <K, V> MapBuilder<K, V> hashmap() {
        return new MapBuilder<>(new HashMap<K, V>());
    }

    public static <K, V> MapBuilder<K, V> immutable(Class<K> a, Class<V> b) {
        return new MapBuilder<K, V>(new HashMap<>()) {
            public Map<K, V> build() {
                return Collections.unmodifiableMap(super.build());
            }
        };
    }

    public static <K, V> MapBuilder<K, V> immutable() {
        return new MapBuilder<K, V>(new HashMap<>()) {
            public Map<K, V> build() {
                return Collections.unmodifiableMap(super.build());
            }
        };
    }

}

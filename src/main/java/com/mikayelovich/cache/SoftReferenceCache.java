package com.mikayelovich.cache;
import org.springframework.stereotype.Component;
import java.lang.ref.SoftReference;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SoftReferenceCache<K, V> {

    private final Map<K, SoftReference<V>> cache = new ConcurrentHashMap<>();
  //private final Map<K, SoftReference<V>> cache = Collections.synchronizedMap(new HashMap<>());

    public Optional<V> get(K key) {
        SoftReference<V> ref = cache.get(key);
        return (ref != null) ? Optional.ofNullable(ref.get()) : Optional.empty();
    }

    public void put(K key, V value) {
        cache.put(key, new SoftReference<>(value));
    }

    public void remove(K key) {
        cache.remove(key);
    }

    // You might also want to provide a method to clear the cache
    public void clear() {
        cache.clear();
    }
}

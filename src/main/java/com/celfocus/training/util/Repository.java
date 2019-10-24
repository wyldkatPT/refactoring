package com.celfocus.training.util;

import java.util.Optional;

public interface Repository<K, V> {

    Optional<V> findById(K id);

    void deleteById(K id);

    void add(V value);

    int count();

}

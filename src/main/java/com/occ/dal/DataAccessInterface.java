package com.occ.dal;

import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

/**
 * Created by bg on 2016.04.07..
 */
public interface DataAccessInterface<T> {
    Optional<T> get(String id);

    void put(T t);

    public void put(Set<T> listOfCacheables, Function<T, Void> relatedWeakPuts);

    T putIfAbsent(T t);

    T putWeak(String id);

    void delete(String id);

    void update(T t);
}

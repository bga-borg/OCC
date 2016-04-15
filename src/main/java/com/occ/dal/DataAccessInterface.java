package com.occ.dal;

import java.util.Optional;

/**
 * Created by bg on 2016.04.07..
 */
public interface DataAccessInterface<T> {
    Optional<T> get(String id);

    void put(T t);

    T putIfAbsent(T t);

    T putWeak(String id);

    void delete(String id);

    void update(T t);
}

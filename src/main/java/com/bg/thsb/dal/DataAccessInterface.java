package com.bg.thsb.dal;

import java.util.Optional;

/**
 * Created by bg on 2016.04.07..
 */
public interface DataAccessInterface<T> {
    Optional<T> get(String id);

    void put(T t);

    void putIfAbsent(T t);

    void putWeak(String id);
}

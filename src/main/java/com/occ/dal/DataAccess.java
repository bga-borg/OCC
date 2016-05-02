package com.occ.dal;

import org.apache.lucene.search.Query;
import org.hibernate.search.query.dsl.QueryBuilder;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

public interface DataAccess<T> {
    Optional<T> get(String id);

    void put(T t);

    void put(Set<T> setOfCacheables, Function<T, Void> relatedWeakPuts);

    T putIfAbsent(T t);

    T putWeak(String id);

    void delete(String id);

    void update(T t);

    List<T> searchByAttribute(String attributeName, String value);
}

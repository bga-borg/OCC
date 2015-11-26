package com.bg.thsb.eagercollection;

import com.bg.thsb.plainmodel.ResourceEntity;
import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import org.infinispan.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.annotation.Nullable;
import java.util.*;

public class EagerList<E> implements List<E> {

    @Autowired
    Cache<String, Object> infinispanCache;

    List<String> storedKeys = new ArrayList<>();

    @Override
    public int size() {
        return storedKeys.size();
    }

    @Override
    public boolean isEmpty() {
        return storedKeys.isEmpty();
    }

    @Override
    public boolean contains(final Object o) {
        // todo optimize
        return FluentIterable.from(storedKeys).filter(key -> {
            return infinispanCache.get(key).equals(o);
        }).first().isPresent();
    }

    @Override
    public Iterator<E> iterator() {
        throw new NotImplementedException();
    }

    @Override
    public Object[] toArray() {
        return FluentIterable.from(storedKeys).transform(new Function<String, Object>() {
            @Nullable
            @Override
            public Object apply(String key) {
                return infinispanCache.get(key);
            }
        }).toArray(Object.class);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        T[] arr = a = (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), storedKeys.size());

        // todo not that easy
        throw new NotImplementedException();
    }

    @Override
    public boolean add(E e) {
        String key = ((ResourceEntity) e).getId();
        infinispanCache.put(key, e);
        // todo what to return
        return true;
    }

    @Override
    public boolean remove(Object o) {
        String key = ((ResourceEntity) o).getId();
        // here comes the interesting part
        // remove the item id from the list but not from the cache
        // todo when to remove from the cache?
        return storedKeys.remove(key);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new NotImplementedException();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new NotImplementedException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new NotImplementedException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new NotImplementedException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new NotImplementedException();
    }

    @Override
    public void clear() {
        throw new NotImplementedException();
    }

    @Override
    public E get(int index) {
        throw new NotImplementedException();
    }

    @Override
    public E set(int index, E element) {
        throw new NotImplementedException();
    }

    @Override
    public void add(int index, E element) {
        throw new NotImplementedException();
    }

    @Override
    public E remove(int index) {
        throw new NotImplementedException();
    }

    @Override
    public int indexOf(Object o) {
        throw new NotImplementedException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new NotImplementedException();
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new NotImplementedException();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new NotImplementedException();
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new NotImplementedException();
    }
}

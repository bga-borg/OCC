package com.occ.lazycollection;

import com.occ.infinispan.InfinispanCacheWrapper;
import com.occ.openstack.model.entities.CachedResource;
import com.occ.openstack.model.entities.ResourceEntity;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.FluentIterable;
import org.infinispan.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.annotation.Nullable;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class LazyList<E> implements List<E> {

    InfinispanCacheWrapper cacheWrapper;

    private static Logger logger = LoggerFactory.getLogger(LazyList.class);
    List<String> storedKeys = new ArrayList<>();
    private Cache<String, Object> infinispanCache = cacheWrapper.getCache();

    @Override
    public int size() {
        return storedKeys.size();
    }

    @Override
    public boolean isEmpty() {
        cleanup();
        return storedKeys.isEmpty();
    }

    @Override
    public boolean contains(final Object o) {
        Optional<String> id = FluentIterable.from(storedKeys).filter(key -> {
            return infinispanCache.get(key).equals(o);
        }).first();

        if (id.isPresent()) {
            if (infinispanCache.get(id.get()) != null) {
                return true;
            }
        }

        return false;
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
        T[] arr = (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), storedKeys.size());
        for (int i = 0; i < storedKeys.size(); i++) {
            arr[i] = (T) infinispanCache.get(storedKeys.get(i));
        }
        return arr;
    }

    @Override
    public boolean add(E e) {
        String key = ((ResourceEntity) e).getId();
        storedKeys.add(key);
        if (e instanceof CachedResource && ((CachedResource) e).isOnlyReference()) {
            infinispanCache.put(key, e);
        } else {
            infinispanCache.put(key, e, -1, TimeUnit.DAYS);
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        String key = ((ResourceEntity) o).getId();
        return storedKeys.remove(key);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        storedKeys.containsAll(FluentIterable.from(c).transform(new Function<Object, String>() {
            @Nullable
            @Override
            public String apply(@Nullable Object input) {
                return ((ResourceEntity) input).getId();
            }
        }).toList());
        return false;
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
        storedKeys.clear();
    }

    @Override
    public E get(int index) {
        String id = storedKeys.get(index);
        E element = (E) infinispanCache.get(id);
        return element;
    }

    @Override
    public E set(int index, E element) {
        String oldEntityId = storedKeys.get(index);
        ResourceEntity resourceEntity = (ResourceEntity) element;
        storedKeys.set(index, resourceEntity.getId());
        infinispanCache.put(resourceEntity.getId(), resourceEntity);
        return (E) infinispanCache.get(oldEntityId);
    }

    @Override
    public void add(int index, E element) {
        ResourceEntity resourceEntity = (ResourceEntity) element;
        infinispanCache.put(resourceEntity.getId(), element);
        storedKeys.add(index, resourceEntity.getId());
    }

    @Override
    public E remove(int index) {
        E toReturn = (E) infinispanCache.get(storedKeys.get(index));
        storedKeys.remove(index);
        return toReturn;
    }

    @Override
    public int indexOf(Object o) {
        ResourceEntity resourceEntity = (ResourceEntity) o;
        return storedKeys.indexOf(resourceEntity.getId());
    }

    @Override
    public int lastIndexOf(Object o) {
        ResourceEntity resourceEntity = (ResourceEntity) o;
        return storedKeys.lastIndexOf(resourceEntity.getId());
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

    @Override
    public String toString() {
        return currentState();
    }

    public String currentState() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Stored keys: " + storedKeys.toString());
        stringBuffer.append("\n");
        stringBuffer.append("Keys in list: " + storedKeys.size());
        stringBuffer.append("\n");
        return stringBuffer.toString();
    }


    void cleanup() {
        for (ListIterator<String> it = storedKeys.listIterator(); it.hasNext(); ) {
            if (infinispanCache.get(it.next()) == null) {
                it.remove();
            }
        }
    }

    public List<String> getStoredKeys() {
        return storedKeys;
    }
}

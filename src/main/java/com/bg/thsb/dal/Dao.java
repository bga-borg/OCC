package com.bg.thsb.dal;

import com.bg.thsb.infinispan.InfinispanCacheWrapper;
import com.bg.thsb.openstack.model.entities.CachedResource;
import com.bg.thsb.openstack.model.entities.ResourceEntity;
import org.infinispan.Cache;

import java.util.Optional;

public class Dao<T> implements DataAccessInterface<T> {

    Cache<String, Object> cache = InfinispanCacheWrapper.getCache();
    private Class<T> clazz;

    @Override
    public Optional<T> get(String id) {
        return Optional.ofNullable((T) cache.get(id));
    }

    @Override
    public void put(T t) {
        cache.put(((ResourceEntity) t).getId(), t);
    }

    @Override
    public T putIfAbsent(T t) {
        return (T) cache.putIfAbsent(((ResourceEntity) t).getId(), t);
    }

    @Override
    public T putWeak(String id) {
        T t = getInstanceOfT();
        ((CachedResource) t).setOnlyReference(true);
        ((CachedResource) t).setId(id);
        ((CachedResource) t).setName(id);
        return putIfAbsent(t);
    }

    @Override
    public void delete(String id) {
        cache.remove(id);
    }

    @Override
    public void update(T t) {
        put(t);
    }

    public static <T> Dao<T> of(Class<T> clazz) {
        Dao<T> dao = new Dao<>();
        dao.clazz = clazz;
        return dao;
    }


    private T getInstanceOfT() {
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Can not instantiate class with default constructor: " + clazz.getName());
    }
}

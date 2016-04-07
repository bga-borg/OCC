package com.bg.thsb.dal;

import com.bg.thsb.openstack.model.entities.CachedResource;
import com.bg.thsb.openstack.model.entities.ResourceEntity;
import org.infinispan.Cache;

import java.util.Optional;

public class Dao<T> implements DataAccessInterface<T> {

    Cache<String, Object> cache;
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
    public void putIfAbsent(T t) {
        cache.putIfAbsent(((ResourceEntity) t).getId(), t);
    }

    @Override
    public void putWeak(String id) {
        T t = getInstanceOfT();
        ((CachedResource) t).setOnlyReference(true);
        ((CachedResource) t).setId(id);
        ((CachedResource) t).setName(id);
        putIfAbsent(t);
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

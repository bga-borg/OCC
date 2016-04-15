package com.occ.dal;

import com.occ.infinispan.InfinispanCacheWrapper;
import com.occ.openstack.OpenCloudCacheConfiguration;
import com.occ.openstack.model.entities.CachedResource;
import com.occ.openstack.model.entities.ResourceEntity;
import org.apache.log4j.Logger;
import org.infinispan.Cache;

import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Dao<T> implements DataAccessInterface<T> {

    private static final Logger logger = Logger.getLogger(Dao.class);

    Cache<String, Object> cache = InfinispanCacheWrapper.getCache();
    OpenCloudCacheConfiguration occConfig = new OpenCloudCacheConfiguration();
    InstanceSynchronizer iSync = new InstanceSynchronizer();
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
    public void put(Set<T> listOfCachedResources, Function<T, Void> relatedWeakPuts) {
        try {
            if (occConfig.isInstanceSynchronizerEnabled()) {
                iSync.removeIfMissing(
                        listOfCachedResources.stream().map(t -> ((CachedResource) t).getId()).collect(Collectors.toSet()),
                        clazz);
            }

            if(relatedWeakPuts!= null)
                listOfCachedResources.forEach(relatedWeakPuts::apply);

            listOfCachedResources.forEach(this::put);
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public T putIfAbsent(T t) {
        return (T) cache.putIfAbsent(((CachedResource) t).getId(), t);
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

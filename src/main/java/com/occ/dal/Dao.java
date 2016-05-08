package com.occ.dal;

import com.occ.infinispan.InfinispanCacheWrapper;
import com.occ.openstack.OpenCloudCacheConfiguration;
import com.occ.openstack.model.entities.CachedResource;
import com.occ.openstack.model.entities.ResourceEntity;
import org.apache.log4j.Logger;
import org.infinispan.Cache;
import org.infinispan.CacheStream;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Dao<T> implements DataAccess<T> {

    protected static final Logger logger = Logger.getLogger(Dao.class);
    public static Boolean INSTANCE_SYNCHRONIZE_ENABLED = true;
    public static Boolean WEAK_RELATIONS_REFERENCE_CREATION_ENABLED = true;
    public static Boolean STREAM_PARALLEL = true;
    protected Class<T> clazz;
    OpenCloudCacheConfiguration occConfig = new OpenCloudCacheConfiguration();
    Cache<String, Object> cache = InfinispanCacheWrapper.getCache();
    InstanceSynchronizer iSync = new InstanceSynchronizer();

    public static <T> Dao<T> of(Class<T> clazz) {
        Dao<T> dao = new Dao<>();
        dao.clazz = clazz;
        return dao;
    }

    @Override
    public Optional<T> get(String id) {
        return Optional.ofNullable((T) cache.get(id));
    }

    @Override
    public void put(T t) {
        cache.put(((ResourceEntity) t).getId(), t);
    }

    @Override
    public void put(Set<T> setOfCacheables, Function<T, Void> relatedWeakPuts) {
        try {
            if (INSTANCE_SYNCHRONIZE_ENABLED) {
                iSync.removeIfMissing(
                        setOfCacheables.stream().map(t -> ((CachedResource) t).getId()).collect(Collectors.toSet()),
                        clazz);
            }

            if (WEAK_RELATIONS_REFERENCE_CREATION_ENABLED && relatedWeakPuts != null)
                setOfCacheables.forEach(relatedWeakPuts::apply);

            setOfCacheables.forEach(this::put);
        } catch (Exception e) {
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
        int dalWeakPutLifetimeSec = occConfig.getDalWeakPutLifetimeSec();

        T t = getInstanceOfT();
        ((CachedResource) t).setOnlyReference(true);
        ((CachedResource) t).setId(id);
        ((CachedResource) t).setName(id);
        return (T) cache.putIfAbsent(((CachedResource) t).getId(), t, dalWeakPutLifetimeSec, TimeUnit.SECONDS);
    }

    @Override
    public void delete(String id) {
        cache.remove(id);
    }

    @Override
    public void update(T t) {
        put(t);
    }

    /**
     * Providing linear search
     *
     * @param attributeName
     * @param searchValue
     * @return
     */
    @Override
    public List<T> searchByAttribute(String attributeName, String searchValue) {

        CacheStream<Map.Entry<String, Object>> cacheStream =
                STREAM_PARALLEL ? cache.entrySet().parallelStream() : cache.entrySet().stream();

        return cacheStream
                .map(stringObjectEntry -> stringObjectEntry.getValue())
                .filter(value -> value.getClass().equals(clazz))
                .filter(value -> isMatch(attributeName, searchValue, value))
                .map(value -> (T) value)
                .collect(Collectors.toList());
    }

    private boolean isMatch(String attributeName, String searchValue, Object value) {
        try {
            Field field = value.getClass().getDeclaredField(attributeName);
            field.setAccessible(true);
            Object valueOfField = field.get(value);
            if (valueOfField != null && valueOfField.toString().equals(searchValue)) {
                return true;
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();

        } catch (NoSuchFieldException e) {
            logger.error(e.getMessage());
        }
        return false;
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

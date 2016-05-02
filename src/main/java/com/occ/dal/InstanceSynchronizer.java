package com.occ.dal;


import com.occ.infinispan.InfinispanCacheWrapper;
import org.apache.log4j.Logger;
import org.infinispan.Cache;

import java.util.Set;

public class InstanceSynchronizer {

    private static final Logger logger = Logger.getLogger(InstanceSynchronizer.class);

    public static final String ID_LIST_KEY_PREFIX = "____";
    public static final String ID_LIST_KEY_POSTFIX = "___id_list";
    Cache<String, Object> cache = InfinispanCacheWrapper.getCache();

    public void removeIfMissing(Set<String> currentSet, Class clazz) {
        String idListKey = ID_LIST_KEY_PREFIX + clazz.getCanonicalName() + ID_LIST_KEY_POSTFIX;
        // null not necessarily means that the elem in not set
        // but it means that in the current context
        Object oldSetObject = cache.get(idListKey);

        // there is no list of instance ids, so add the current one, nothing to remove
        if (oldSetObject == null) {
            logger.info("Record created for " + clazz.getSimpleName() + " with KEY: " + idListKey);
            cache.put(idListKey, currentSet);
            return;
        }

        // type checking
        if (!(oldSetObject instanceof Set)) {
            throw new RuntimeException("On KEY: " + idListKey + " cache contains a " + currentSet.getClass().getCanonicalName() +
                    " can't use " + this.getClass().getSimpleName());
        }

        Set<String> oldSet = (Set<String>) oldSetObject;
        // there is a list already, so delete all the elements from the subtraction
        oldSet.removeAll(currentSet);
        if (oldSet.size() > 0) {
            logger.info("There is " + oldSet.size() + " of " + clazz.getCanonicalName() + " to remove from db");
            oldSet.forEach(k -> cache.remove(k));
            logger.info("Remove complete");
        }
        cache.put(idListKey, currentSet);
    }
}

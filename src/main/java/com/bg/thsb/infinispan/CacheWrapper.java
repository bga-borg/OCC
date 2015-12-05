package com.bg.thsb.infinispan;

import org.infinispan.Cache;
import org.infinispan.manager.DefaultCacheManager;
import org.jvnet.hk2.annotations.Service;

import java.io.IOException;

@Service
public class CacheWrapper {
    public static final String OBJECT_IN_MEM_CACHE = "objectInMem";

    static DefaultCacheManager infinispanDefaultCacheManager;

    static {
        try {
            infinispanDefaultCacheManager = new DefaultCacheManager("infinispan-configuration.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Cache<String, Object> getCache() {
        return infinispanDefaultCacheManager.getCache(OBJECT_IN_MEM_CACHE);
    }
}

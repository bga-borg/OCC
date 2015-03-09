package com.bg.thsb;

import org.infinispan.Cache;
import org.infinispan.manager.DefaultCacheManager;

import java.io.IOException;

public class InfinispanConfigurator {

    Cache<String, Object> stringToObjectCache = null;
    DefaultCacheManager defaultCacheManager = null;


    public InfinispanConfigurator(){

        try {
            defaultCacheManager = new DefaultCacheManager("infinispan-configuration.xml");
            stringToObjectCache = defaultCacheManager.getCache("writeThroughToFile");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    Cache<String, Object> getStringToObjectCache() {
        return stringToObjectCache;
    }
}

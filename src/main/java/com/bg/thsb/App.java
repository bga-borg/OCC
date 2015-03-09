package com.bg.thsb;

import org.infinispan.Cache;
import org.infinispan.manager.DefaultCacheManager;

class App {
    public static void main(String... args) {
        InfinispanConfigurator infinispanConfigurator = new InfinispanConfigurator();

        Cache<String, Object> stringToObjectCache = infinispanConfigurator.getStringToObjectCache();
        stringToObjectCache.put("name", "thsb");

        System.out.println("The name of the project is " + stringToObjectCache.get("name"));
    }
}
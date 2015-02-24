package com.bg.thsb;

import org.infinispan.Cache;
import org.infinispan.manager.DefaultCacheManager;

class App {
    public static void main(String... args) {
        DefaultCacheManager m = new DefaultCacheManager();
        Cache<String, String> cache = m.getCache();
        cache.put("name", "thsb");

        System.out.println("The name of the project is " + cache.get("name"));
    }
}
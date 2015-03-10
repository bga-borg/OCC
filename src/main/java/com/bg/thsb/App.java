package com.bg.thsb;

import org.infinispan.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

class App {
    private static Logger logger = LoggerFactory.getLogger(App.class);
    InfinispanConfigurator infinispanConfigurator = new InfinispanConfigurator(false);

    public static void main(String... args) {
        App app = new App();
//        long testOneMillInMemory = app.testOneMillionInMemory();
//        logger.info("One million in memory kv add: " + testOneMillInMemory / 1000.0 + " s");

        long oneMillionOnDisk = app.testOneMillionOnDisk();
        logger.info("One million on disk kv add: " + oneMillionOnDisk / 1000.0 + " s");

    }

    /**
     * Add one million string-string key-value with forced disk persist
     *
     * @return process end time
     */
    public long testOneMillionOnDisk() {
        Cache<String, Object> stringToObjectCache = infinispanConfigurator.getStringObjectCacheOnDisk();

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            stringToObjectCache.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        }
        long endTime = System.currentTimeMillis();
        long length = endTime - startTime;

        //stringToObjectCache.clear();

        return length;
    }

    /**
     * Add one million string-string key-value in memory
     *
     * @return process end time
     */
    public long testOneMillionInMemory() {
        Cache<String, Object> stringToObjectCache = infinispanConfigurator.getStringToObjectCacheInMem();

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            stringToObjectCache.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        }
        long endTime = System.currentTimeMillis();
        long length = endTime - startTime;

        stringToObjectCache.clear();

        return length;
    }
}
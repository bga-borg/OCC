package com.bg.thsb;

import org.infinispan.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

class App {
    private final static int NUM_RECORDS = 100000;

    private static Logger logger = LoggerFactory.getLogger(App.class);
    InfinispanConfigurator infinispanConfigurator = new InfinispanConfigurator(false);

    public static void main(String... args) {
        App app = new App();
        long testOneMillInMemory = app.testOneMillionInMemory();
        logger.info(NUM_RECORDS + " in-memory kv add: " + testOneMillInMemory / 1000.0 + " s");

        long oneMillionOnDisk = app.testOneMillionOnDisk();
        logger.info(NUM_RECORDS + " on disk kv add: " + oneMillionOnDisk / 1000.0 + " s");

    }

    /**
     * Add one million string-string key-value with forced disk persist
     *
     * @return process end time
     */
    public long testOneMillionOnDisk() {
        Cache<String, Object> stringToObjectCache = infinispanConfigurator.getStringObjectCacheOnDisk();

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < NUM_RECORDS; i++) {
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
        for (int i = 0; i < NUM_RECORDS; i++) {
            stringToObjectCache.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        }
        long endTime = System.currentTimeMillis();
        long length = endTime - startTime;

        stringToObjectCache.clear();

        return length;
    }
}
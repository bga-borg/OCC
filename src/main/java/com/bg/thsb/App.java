package com.bg.thsb;

import org.infinispan.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.UUID;

class App {
    private final static int NUM_RECORDS = 1000000;

    private static Logger logger = LoggerFactory.getLogger(App.class);
    InfinispanConfigurator infinispanConfigurator = new InfinispanConfigurator(false);

    public static void main(String... args) {
        App app = new App();

        //app.testInMemory();
        app.testOnDisk();
    }

    /**
     * Add key-value with forced disk persist
     *
     * @return process end time
     */
    public long testOnDisk() {
        System.out.println("on disk test start");
        waitForEnter();

        Cache<String, Object> stringToObjectCache = infinispanConfigurator.getStringObjectCacheOnDisk();

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < NUM_RECORDS; i++) {
            stringToObjectCache.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        }
        long endTime = System.currentTimeMillis();
        long length = endTime - startTime;

        System.out.println(NUM_RECORDS + " on disk kv add: " + length / 1000.0 + " s");

        System.out.println("on disk test end (before cleanup)");
        waitForEnter();
        stringToObjectCache.clear();

        return length;
    }

    /**
     * Add key-value in memory
     *
     * @return process end time
     */
    public long testInMemory() {
        System.out.println("in-memory test start");
        waitForEnter();

        Cache<String, Object> stringToObjectCache = infinispanConfigurator.getStringToObjectCacheInMem();

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < NUM_RECORDS; i++) {
            stringToObjectCache.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        }
        long endTime = System.currentTimeMillis();
        long length = endTime - startTime;

        System.out.println(NUM_RECORDS + " in-memory kv add: " + length / 1000.0 + " s");

        System.out.println("in memory test end (before cleanup)");
        waitForEnter();
        stringToObjectCache.clear();

        return length;
    }

    public static void waitForEnter(){
        try {
            System.out.print("Press enter to continue...");
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
package com.bg.thsb.infinispan;

import com.bg.thsb.Constants;
import com.bg.thsb.jclouds.openstack.nova.v2_0.domain.Server;
import org.infinispan.Cache;

import java.util.UUID;

import static com.bg.thsb.Helpers.waitForEnter;

/**
 * Created by bg on 2015.05.17..
 */
public class InfinispanMeasurements {
    InfinispanConfigurator infinispanConfigurator = new InfinispanConfigurator(false);

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
        for (int i = 0; i < Constants.NUM_RECORDS; i++) {
            UUID randomUUID = UUID.randomUUID();
            Server server = Server.builder()
                    .uuid(randomUUID.toString())
                    .id(randomUUID.toString())
                    .keyName(randomUUID.toString())
                    .build();

            stringToObjectCache.put(randomUUID.toString(), server);
        }
        long endTime = System.currentTimeMillis();
        long length = endTime - startTime;

        System.out.println(Constants.NUM_RECORDS + " on disk kv add: " + length / 1000.0 + " s");

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
        for (int i = 0; i < Constants.NUM_RECORDS; i++) {
            stringToObjectCache.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        }
        long endTime = System.currentTimeMillis();
        long length = endTime - startTime;

        System.out.println(Constants.NUM_RECORDS + " in-memory kv add: " + length / 1000.0 + " s");

        System.out.println("in memory test end (before cleanup)");
        waitForEnter();
        stringToObjectCache.clear();

        return length;
    }
}

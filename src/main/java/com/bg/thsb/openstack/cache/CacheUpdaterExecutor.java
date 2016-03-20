package com.bg.thsb.openstack.cache;

import com.bg.thsb.openstack.OpenStackConfiguration;
import com.bg.thsb.openstack.cache.updaters.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * UpdaterRunner
 */

@Component
public class CacheUpdaterExecutor implements CommandLineRunner {
    ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;
    private static Logger logger = LoggerFactory.getLogger(CacheUpdaterExecutor.class);


    @Autowired
    ImageCacheUpdater imageCacheUpdater;
    @Autowired
    ServerCacheUpdater serverCacheUpdater;
    @Autowired
    VolumeCacheUpdater volumeCacheUpdater;
    @Autowired
    TenantCacheUpdater tenantCacheUpdater;
    @Autowired
    ImportStatusFromSerializedCacheUpdater importStatusFromSerializedCacheUpdater;
    @Autowired
    ExportCacheStatusToJson exportCacheStatusToJson;

    OpenStackConfiguration openStackConfiguration = new OpenStackConfiguration();

    @Override
    public void run(String... args) throws Exception {
        logger.info("CacheUpdaterExecutor started as CommandLineRunner.");
        scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(10);

        if (openStackConfiguration.isOffline()) {
            logger.warn("Working in offline mode. (based on configuration)");
            logger.info("Loading data to cache every 30 seconds from " + openStackConfiguration.getCacheImportFilename());
            scheduledThreadPoolExecutor.scheduleWithFixedDelay(
                    importStatusFromSerializedCacheUpdater, 0, 30, TimeUnit.SECONDS);
        } else {
            scheduledThreadPoolExecutor.scheduleWithFixedDelay(
                    imageCacheUpdater, 0, 10, TimeUnit.SECONDS);
            scheduledThreadPoolExecutor.scheduleWithFixedDelay(
                    serverCacheUpdater, 3, 16, TimeUnit.SECONDS);
            scheduledThreadPoolExecutor.scheduleWithFixedDelay(
                    volumeCacheUpdater, 5, 10, TimeUnit.SECONDS);
            scheduledThreadPoolExecutor.scheduleWithFixedDelay(
                    tenantCacheUpdater, 5, 10, TimeUnit.SECONDS);
        }

        scheduledThreadPoolExecutor.scheduleWithFixedDelay(exportCacheStatusToJson, 20, 60, TimeUnit.SECONDS);
    }
}

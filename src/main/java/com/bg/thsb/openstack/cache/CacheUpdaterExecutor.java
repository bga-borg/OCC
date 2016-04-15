package com.bg.thsb.openstack.cache;

import com.bg.thsb.openstack.OpenCloudCacheConfiguration;
import com.bg.thsb.openstack.cache.updaters.*;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * UpdaterRunner
 */

@Component
public class CacheUpdaterExecutor implements CommandLineRunner {
    ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(10);

    private static Logger logger = LoggerFactory.getLogger(CacheUpdaterExecutor.class);

    ImageCacheUpdater imageCacheUpdater = new ImageCacheUpdater();
    ServerCacheUpdater serverCacheUpdater = new ServerCacheUpdater();
    VolumeCacheUpdater volumeCacheUpdater = new VolumeCacheUpdater();
    TenantCacheUpdater tenantCacheUpdater = new TenantCacheUpdater();
    ImportStatusFromSerializedCacheUpdater importStatusFromSerializedCacheUpdater = new ImportStatusFromSerializedCacheUpdater();
    ExportCacheStatusToJson exportCacheStatusToJson = new ExportCacheStatusToJson();

    OpenCloudCacheConfiguration openCloudCacheConfiguration = new OpenCloudCacheConfiguration();
    Map<String, ScheduledExecutorCacheUpdaterWrapper> scheduledFutureMap = Maps.newHashMap();

    @Override
    public void run(String... args) throws Exception {

        logger.info("CacheUpdaterExecutor started as CommandLineRunner.");

        if (openCloudCacheConfiguration.isOffline()) {
            logger.warn("Working in offline mode. (based on configuration)");
            logger.info("Loading data to cache every 30 seconds from " + openCloudCacheConfiguration.getCacheImportFilename());
            scheduledThreadPoolExecutor.scheduleWithFixedDelay(
                    importStatusFromSerializedCacheUpdater, 0, 30, TimeUnit.SECONDS);
        } else {
            addScheduledCacheExecutor(imageCacheUpdater, 0, 10);
            addScheduledCacheExecutor(serverCacheUpdater, 2, 10);
            addScheduledCacheExecutor(volumeCacheUpdater, 4, 10);
            addScheduledCacheExecutor(tenantCacheUpdater, 6, 10);

            if (openCloudCacheConfiguration.getCacheSerializationInterval() > 0) {
                scheduledThreadPoolExecutor.scheduleWithFixedDelay(
                        new ExportCacheStatusSerialized(),
                        openCloudCacheConfiguration.getCacheSerializationInterval(),
                        openCloudCacheConfiguration.getCacheSerializationInterval(),
                        TimeUnit.SECONDS);
            }
        }

        if (openCloudCacheConfiguration.getExportStatusToJSONInterval() > 0) {
            scheduledThreadPoolExecutor.scheduleWithFixedDelay(
                    exportCacheStatusToJson,
                    openCloudCacheConfiguration.getExportStatusToJSONInterval(),
                    openCloudCacheConfiguration.getExportStatusToJSONInterval(),
                    TimeUnit.SECONDS);
        }
    }

    public void addScheduledCacheExecutor(CacheUpdater cacheUpdater, int initDelaySec, int delaySec){
        ScheduledExecutorCacheUpdaterWrapper updaterWrapper = new ScheduledExecutorCacheUpdaterWrapper();
        updaterWrapper.cacheUpdater = cacheUpdater;
        updaterWrapper.initDelaySec = initDelaySec;
        updaterWrapper.delaySec = delaySec;
        updaterWrapper.executorScheduledFuture =
                scheduledThreadPoolExecutor.scheduleWithFixedDelay(cacheUpdater, initDelaySec, delaySec, TimeUnit.SECONDS);

        scheduledFutureMap.put(cacheUpdater.getClass().getSimpleName(), updaterWrapper);
    }

    public static class ScheduledExecutorCacheUpdaterWrapper{
        public CacheUpdater cacheUpdater;
        public int initDelaySec;
        public int delaySec;
        public ScheduledFuture<?> executorScheduledFuture;
    }
}

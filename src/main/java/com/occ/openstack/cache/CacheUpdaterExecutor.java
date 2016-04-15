package com.occ.openstack.cache;

import com.google.common.collect.Lists;
import com.occ.openstack.OpenCloudCacheConfiguration;
import com.google.common.collect.Maps;
import com.occ.openstack.cache.updaters.CacheUpdater;
import com.occ.openstack.cache.updaters.ExportCacheStatusSerialized;
import com.occ.openstack.cache.updaters.ExportCacheStatusToJson;
import com.occ.openstack.cache.updaters.ImportStatusFromSerializedCacheUpdater;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * CacheUpdaterExecutor
 */

@Component
public class CacheUpdaterExecutor implements CommandLineRunner {
    ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(10);
    List<CacheUpdater> cacheUpdaters = Lists.newArrayList();

    private static Logger logger = LoggerFactory.getLogger(CacheUpdaterExecutor.class);

    ImportStatusFromSerializedCacheUpdater importStatusFromSerializedCacheUpdater = new ImportStatusFromSerializedCacheUpdater();
    ExportCacheStatusToJson exportCacheStatusToJson = new ExportCacheStatusToJson();

    OpenCloudCacheConfiguration openCloudCacheConfiguration = new OpenCloudCacheConfiguration();
    Map<String, ScheduledExecutorCacheUpdaterWrapper> scheduledFutureMap = Maps.newHashMap();

    @Override
    public void run(String... args) throws Exception {
        instantiateCacheUpdaters();

        logger.info("CacheUpdaterExecutor started as CommandLineRunner.");

        if (openCloudCacheConfiguration.isOffline()) {
            logger.warn("Working in offline mode. (based on configuration)");
            logger.info("Loading data to cache every 30 seconds from " + openCloudCacheConfiguration.getCacheImportFilename());
            scheduledThreadPoolExecutor.scheduleWithFixedDelay(
                    importStatusFromSerializedCacheUpdater, 0, 30, TimeUnit.SECONDS);
        } else {
            cacheUpdaters.forEach(cacheUpdater -> addScheduledCacheExecutor(cacheUpdater, 0, 10));

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

    private void instantiateCacheUpdaters() {
        String aPackage = openCloudCacheConfiguration.getActiveCacheUpdatersPackage();
        Arrays.asList(openCloudCacheConfiguration.getListOfActiveCacheUpdaters()).forEach(classname -> {
            try {
                CacheUpdater updater = (CacheUpdater) Class.forName(aPackage + "." + classname).newInstance();
                cacheUpdaters.add(updater);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
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

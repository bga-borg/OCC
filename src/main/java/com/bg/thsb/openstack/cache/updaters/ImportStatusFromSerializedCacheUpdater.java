package com.bg.thsb.openstack.cache.updaters;

import com.bg.thsb.helper.SerializeHelper;
import com.bg.thsb.infinispan.InfinispanCacheWrapper;
import com.bg.thsb.openstack.OpenCloudCacheConfiguration;
import org.apache.log4j.Logger;
import org.infinispan.Cache;

import java.util.Map;

public class ImportStatusFromSerializedCacheUpdater extends CacheUpdater {
    private static final Logger logger = Logger.getLogger(ImportStatusFromSerializedCacheUpdater.class);

    OpenCloudCacheConfiguration openCloudCacheConfiguration = new OpenCloudCacheConfiguration();

    @Override
    public void run() {
        final Cache<String, Object> cache = InfinispanCacheWrapper.getCache();
        SerializeHelper<Map<String, Object>> mapSerializeHelper = new SerializeHelper<>();
        Map<String, Object> stringObjectMap = mapSerializeHelper.readObject(openCloudCacheConfiguration.getCacheImportFilename());
        stringObjectMap.forEach((k, v) -> cache.put(k, v));
        logger.info(this.getClass().getName() + " refreshed");
    }
}

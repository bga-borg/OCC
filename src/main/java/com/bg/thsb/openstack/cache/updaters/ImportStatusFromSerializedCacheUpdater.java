package com.bg.thsb.openstack.cache.updaters;

import com.bg.thsb.helper.SerializeHelper;
import com.bg.thsb.infinispan.InfinispanCacheWrapper;
import com.bg.thsb.openstack.OpenStackConfiguration;
import org.apache.log4j.Logger;
import org.infinispan.Cache;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ImportStatusFromSerializedCacheUpdater extends CacheUpdater {
    private static final Logger logger = Logger.getLogger(ImportStatusFromSerializedCacheUpdater.class);

    OpenStackConfiguration openStackConfiguration = new OpenStackConfiguration();

    @Override
    public void run() {
        final Cache<String, Object> cache = InfinispanCacheWrapper.getCache();
        SerializeHelper<Map<String, Object>> mapSerializeHelper = new SerializeHelper<>();
        Map<String, Object> stringObjectMap = mapSerializeHelper.readObject(openStackConfiguration.getCacheImportFilename());
        stringObjectMap.forEach((k, v) -> cache.put(k, v));
        logger.info(this.getClass().getName() + " refreshed");
    }
}

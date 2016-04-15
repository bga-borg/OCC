package com.bg.thsb.openstack.cache.updaters;

import com.bg.thsb.helper.JsonConverter;
import com.bg.thsb.infinispan.InfinispanCacheWrapper;
import org.apache.log4j.Logger;
import org.infinispan.Cache;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * ConsoleUpdater
 */
public class ExportCacheStatusToJson extends CacheUpdater {
    private static final Logger logger = Logger.getLogger(ExportCacheStatusToJson.class);


    @Override
    public void run() {

        BufferedWriter writer = null;
        try {
            Cache<String, Object> cache = InfinispanCacheWrapper.getCache();
            //create a temporary file
            String timeLog = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            File logFile = new File(timeLog);

            // This will output the full path where the file will be written to...
            System.out.println(logFile.getCanonicalPath());
            logger.info("Writing cache content to " + logFile.getCanonicalPath());
            writer = new BufferedWriter(new FileWriter(logFile));

            Map<String, Object> stringObjectMap = new HashMap<>();
            for (String key : cache.keySet()) {
                stringObjectMap.put(key, cache.get(key));
            }
            writer.write(JsonConverter.getObjectMapper().writeValueAsString(stringObjectMap));
            logger.info(this.getClass().getName() + " refreshed");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Close the writer regardless of what happens...
                writer.close();
            } catch (Exception e) {
            }
        }
    }
}

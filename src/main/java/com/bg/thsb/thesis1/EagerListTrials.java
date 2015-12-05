package com.bg.thsb.thesis1;

import com.bg.thsb.plainmodel.Server;
import com.bg.thsb.plainmodel.Volume;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by bg on 2015. 12. 04..
 */
@Component
public class EagerListTrials {
    public static final Logger logger = LoggerFactory.getLogger(EagerListTrials.class);


    public void testCacheMiss() throws InterruptedException {
        Server server = new Server.ServerBuilder().setName("Dizzy").build();

        // create 100 volumes
        List<Volume> volumes = Lists.newArrayList();
        for (int i = 0; i < 100; i++) {
            volumes.add(new Volume.VolumeBuilder().setName("Volume " + i).build());
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                server.getVolumes().add(volumes.get(10 * i + j));
                logger.info("Volumes added: " + (10 * i + j));
            }
            Thread.sleep(1000);
        }

        // list all volumes from server
        logger.info(server.getVolumes().toString());

        logger.info("cache miss: " + server.getVolumes().get(1));
        server.getVolumes().size();
        logger.info("after cleanup: " + server.getVolumes().get(1));
    }

}

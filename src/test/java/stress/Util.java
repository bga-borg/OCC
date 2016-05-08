package stress;


import com.google.common.collect.Lists;
import com.occ.dal.Dao;
import com.occ.infinispan.InfinispanCacheWrapper;
import com.occ.openstack.model.entities.Image;
import com.occ.openstack.model.entities.Server;
import com.occ.openstack.model.entities.Volume;
import org.apache.log4j.Logger;
import org.infinispan.Cache;

import java.util.*;
import java.util.stream.Collectors;

public class Util {

    protected static final Logger logger = Logger.getLogger(Dao.class);
    Cache<String, Object> cache = InfinispanCacheWrapper.getCache();

    List<Image> getRandomImages(int n, String prefix) {
        List<Image> imageList = Lists.newArrayList();
        for (int i = 0; i < n; i++) {
            Image image = new Image();
            image.setId(UUID.randomUUID().toString());
            image.setName(prefix + i);
            image.setCreated(new Date());
            imageList.add(image);
        }
        return imageList;
    }

    List<Volume> getRandomVolumes(int n, String prefix) {
        List<Volume> volumes = Lists.newArrayList();
        for (int i = 0; i < n; i++) {
            Volume volume = new Volume();
            volume.setCreated(new Date());
            volume.setId(UUID.randomUUID().toString());
            volume.setName(prefix + i);
            volumes.add(volume);
        }
        return volumes;
    }

    /**
     * @param volumes all volumes will be attached
     * @param images  it will get an image from the list
     * @param name    the name of the server
     * @return server instance created
     */
    Server getServerConnected(List<Volume> volumes, List<Image> images, String name) {
        Server server = new Server();
        server.setId(UUID.randomUUID().toString());

        if (volumes != null)
            server.setAttachedVolumeIds(volumes.parallelStream().map(volume -> volume.getId()).collect(Collectors.toList()));
        if (images != null)
            server.setImageId(images.get(new Random().nextInt(images.size())).getId());
        server.setName(name);

        return server;
    }

    void clearCache() {
        cache.clear();
    }

    long measure(Runnable runnable) {
        long startTime = System.nanoTime();
        runnable.run();
        long endTime = System.nanoTime();
        return (endTime - startTime);
    }

    void threadSleep(int forTime) {
        try {
            Thread.sleep(forTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    Util.ModelContainer composeModel(int elemsMax) {
        Util.ModelContainer modelContainer = new Util.ModelContainer();
        modelContainer.images = getRandomImages(10, "cirros-");

        if (elemsMax < 10) {
            modelContainer.servers.add(getServerConnected(new ArrayList<>(), modelContainer.images, "server-1"));
            return modelContainer;
        }

        int numServs = (int) (Math.log10(elemsMax)) * 2;
        int numberOfVolumesPerServer = ((elemsMax - numServs) / numServs);

        for (int j = 0; j < numServs; j++) {
            modelContainer.volumes = getRandomVolumes(numberOfVolumesPerServer, "volume");
            modelContainer.servers.add(getServerConnected(modelContainer.volumes, modelContainer.images, "server-" + j));
        }

//        logger.info("N: " + elemsMax + " VolumesPerServer: " + numberOfVolumesPerServer + " Servers: " + numServs);
        logger.info("Total # " + (modelContainer.servers.size() + modelContainer.volumes.size() + modelContainer.images.size()));

        return modelContainer;
    }

    public static class ModelContainer {
        List<Image> images = Lists.newArrayList();
        List<Volume> volumes = Lists.newArrayList();
        List<Server> servers = Lists.newArrayList();
    }
}

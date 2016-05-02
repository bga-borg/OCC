package com.occ.openstack.cache.updaters;

import com.occ.dal.Dao;
import com.occ.dal.DataAccess;
import com.occ.openstack.OSClientWrapper;
import com.occ.openstack.model.entities.Image;
import com.occ.openstack.model.entities.Server;
import com.occ.openstack.model.entities.Volume;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.openstack4j.openstack.compute.domain.NovaServer;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * ServerCacheUpdater
 */
public class ServerCacheUpdater extends CacheUpdater {
    private static final Logger logger = Logger.getLogger(ServerCacheUpdater.class);

    final DataAccess<Server> serverDao = Dao.of(Server.class);
    final DataAccess<Volume> volumeDao = Dao.of(Volume.class);
    final DataAccess<Image> imageDao = Dao.of(Image.class);

    @Override
    public void run() {
        final List<? extends org.openstack4j.model.compute.Server> list = OSClientWrapper.getOs().compute().servers().list();

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new ServerMap());

        Set<Server> serverSet = list.stream().map(o -> modelMapper.map(o, Server.class)).collect(Collectors.toSet());

        serverDao.put(serverSet,
                server -> {
                    server.getAttachedVolumeIds().forEach(id -> volumeDao.putWeak(id));
                    imageDao.putWeak(server.getImageId());
                    return null;
                });

        logger.info(this.getClass().getName() + " refreshed");
    }

    public static class ServerMap extends PropertyMap<NovaServer, Server> {

        @Override
        protected void configure() {
            map().setImageId(source.getImageId());
            map().setFlavorId(source.getFlavorId());
            map().setAttachedVolumeIds(source.getOsExtendedVolumesAttached());
        }
    }
}

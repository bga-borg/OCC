package com.bg.thsb.openstack.cache.updaters;

import com.bg.thsb.dal.Dao;
import com.bg.thsb.dal.DataAccessInterface;
import com.bg.thsb.openstack.OSClientWrapper;
import com.bg.thsb.openstack.model.entities.Server;
import com.bg.thsb.openstack.model.entities.Volume;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.openstack4j.openstack.compute.domain.NovaServer;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ServerCacheUpdater
 *
 */
@Service
public class ServerCacheUpdater extends CacheUpdater {
	private static final Logger logger = Logger.getLogger(ServerCacheUpdater.class);

	DataAccessInterface<Server> serverDao = Dao.of(Server.class);
	DataAccessInterface<Volume> volumeDao = Dao.of(Volume.class);

	@Override
	public void run() {
		try {
			final List<? extends org.openstack4j.model.compute.Server> list = OSClientWrapper.getOs().compute().servers().list();

			ModelMapper modelMapper = new ModelMapper();
			modelMapper.addMappings(new ServerMap());

			list.forEach(sourceServer -> {
				final com.bg.thsb.openstack.model.entities.Server destServer = modelMapper.map(sourceServer, Server.class);
				serverDao.put(destServer);

				// add all volume as reference-only in the list if it's not available already in the db

				/*
				* If the volume is not in the cache, then the volume should be added to the cache with
				* onlyReference = true. This setting means the entity is only referenced weakly.
				*
				* There should be another layer on the cache with the following methods:
				* 	- addIfNotPresent(entity)
				* 	- add(entity)
				*	- getOrAbsent(id, class)
				*	- getOrNull(id, class)
				* */

				destServer.getAttachedVolumeIds().forEach(id -> volumeDao.putWeak(id));
			});

			logger.info(this.getClass().getName() + " refreshed");
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public static class ServerMap extends PropertyMap<NovaServer, com.bg.thsb.openstack.model.entities.Server> {

		@Override
		protected void configure() {
			map().setImageId(source.getImageId());
			map().setFlavorId(source.getFlavorId());
			map().setAttachedVolumeIds(source.getOsExtendedVolumesAttached());
		}
	}
}

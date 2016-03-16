package com.bg.thsb.openstack.cache.updaters;

import com.bg.thsb.openstack.OSClientWrapper;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.openstack4j.model.compute.Server;
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

	@Override
	public void run() {
		final List<? extends Server> list = OSClientWrapper.getOs().compute().servers().list();

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addMappings(new ServerMap());

		list.forEach(o -> {
			com.bg.thsb.openstack.model.entities.Server iServer = modelMapper.map(o, com.bg.thsb.openstack.model.entities.Server.class);
			cache.put(iServer.getId(), iServer);
		});
		logger.info(this.getClass().getName() + " refreshed");
	}

	public static class ServerMap extends PropertyMap<NovaServer, com.bg.thsb.openstack.model.entities.Server> {

		@Override
		protected void configure() {
			map().setImageId(source.getImageId());
			map().setFlavorId(source.getFlavorId());
		}
	}
}

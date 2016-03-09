package com.bg.thsb.openstack.cache.updaters;

import com.bg.thsb.openstack.OSClientWrapper;
import org.apache.log4j.Logger;
import org.openstack4j.model.compute.Server;
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
		list.forEach(o -> cache.put(o.getId(), o));
		logger.info(this.getClass().getName() + " refreshed");
	}
}

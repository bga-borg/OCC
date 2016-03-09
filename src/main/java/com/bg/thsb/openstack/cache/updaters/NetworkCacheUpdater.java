package com.bg.thsb.openstack.cache.updaters;

import com.bg.thsb.openstack.OSClientWrapper;
import org.apache.log4j.Logger;
import org.openstack4j.model.network.Network;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * NetworkCacheUpdater
 *
 */
@Service
public class NetworkCacheUpdater extends CacheUpdater {
	private static final Logger logger = Logger.getLogger(NetworkCacheUpdater.class);

	@Override
	public void run() {
		final List<? extends Network> list = OSClientWrapper.getOs().networking().network().list();
		list.forEach(o -> cache.put(o.getId(), o));
		logger.info(this.getClass().getName() + " refreshed");
	}
}

package com.bg.thsb.openstack.cache.updaters;

import com.bg.thsb.openstack.OSClientWrapper;
import com.bg.thsb.openstack.model.entities.Network;
import org.apache.log4j.Logger;
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
		final List<? extends org.openstack4j.model.network.Network> list = OSClientWrapper.getOs().networking().network().list();

		list.forEach(o -> {
			Network iNetwork = new Network();
			iNetwork.setId(o.getId());
			iNetwork.setName(o.getName());
			cache.put(o.getId(), iNetwork);
		});
		logger.info(this.getClass().getName() + " refreshed");
	}
}

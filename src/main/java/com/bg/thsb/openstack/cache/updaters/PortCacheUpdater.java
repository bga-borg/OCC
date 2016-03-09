package com.bg.thsb.openstack.cache.updaters;

import com.bg.thsb.openstack.OSClientWrapper;
import org.apache.log4j.Logger;
import org.openstack4j.model.network.Port;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * PortCacheUpdater
 *
 */
@Service
public class PortCacheUpdater extends CacheUpdater {
	private static final Logger logger = Logger.getLogger(PortCacheUpdater.class);

	@Override
	public void run() {
		final List<? extends Port> list = OSClientWrapper.getOs().networking().port().list();
		list.forEach(o -> cache.put(o.getId(), o));
		logger.info(this.getClass().getName() + " refreshed");
	}
}

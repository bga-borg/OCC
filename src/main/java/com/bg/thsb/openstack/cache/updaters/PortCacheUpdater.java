package com.bg.thsb.openstack.cache.updaters;

import com.bg.thsb.openstack.OSClientWrapper;
import com.bg.thsb.openstack.model.entities.Port;
import org.apache.log4j.Logger;
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
		final List<? extends org.openstack4j.model.network.Port> list = OSClientWrapper.getOs().networking().port().list();
		list.forEach(o -> {
			Port iPort = new Port();
			iPort.setId(o.getId());
			iPort.setName(o.getName());
			cache.put(o.getId(), iPort);
		});
		logger.info(this.getClass().getName() + " refreshed");
	}
}

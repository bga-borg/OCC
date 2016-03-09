package com.bg.thsb.openstack.cache.updaters;

import com.bg.thsb.openstack.OSClientWrapper;
import org.openstack4j.model.network.Port;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * PortCacheUpdater
 *
 */
@Service
public class PortCacheUpdater extends CacheUpdater {
	@Override
	public void run() {
		final List<? extends Port> list = OSClientWrapper.getOs().networking().port().list();
		System.out.println(list);
	}
}

package com.bg.thsb.openstack.cache.updaters;

import com.bg.thsb.openstack.OSClientWrapper;
import org.openstack4j.model.compute.Server;
import org.openstack4j.model.network.Port;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ServerCacheUpdater
 *
 */
@Service
public class ServerCacheUpdater extends CacheUpdater {
	@Override
	public void run() {
		final List<? extends Server> list = OSClientWrapper.getOs().compute().servers().list();
		System.out.println(list);
	}
}

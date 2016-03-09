package com.bg.thsb.openstack.cache.updaters;

import com.bg.thsb.openstack.OSClientWrapper;
import org.openstack4j.model.compute.Image;
import org.openstack4j.model.network.Network;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * NetworkCacheUpdater
 *
 */
@Service
public class NetworkCacheUpdater extends CacheUpdater {
	@Override
	public void run() {
		final List<? extends Network> list = OSClientWrapper.getOs().networking().network().list();
		System.out.println(list);
	}
}

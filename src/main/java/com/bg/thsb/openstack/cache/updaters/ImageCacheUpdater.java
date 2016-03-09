package com.bg.thsb.openstack.cache.updaters;

import com.bg.thsb.openstack.OSClientWrapper;
import org.openstack4j.model.compute.Image;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ImageCacheUpdater
 *
 */
@Service
public class ImageCacheUpdater extends CacheUpdater {

	@Override
	public void run() {
		final List<? extends Image> list = OSClientWrapper.getOs().compute().images().list();
		System.out.println(list);
	}
}

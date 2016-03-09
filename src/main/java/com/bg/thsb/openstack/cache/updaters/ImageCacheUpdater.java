package com.bg.thsb.openstack.cache.updaters;

import com.bg.thsb.openstack.OSClientWrapper;
import org.apache.log4j.Logger;
import org.openstack4j.model.compute.Image;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ImageCacheUpdater
 *
 */
@Service
public class ImageCacheUpdater extends CacheUpdater {
	private static final Logger logger = Logger.getLogger(ImageCacheUpdater.class);


	@Override
	public void run() {
		final List<? extends Image> list = OSClientWrapper.getOs().compute().images().list();
		list.forEach(o -> cache.put(o.getId(), o));
		logger.info(this.getClass().getName() + " refreshed");
	}
}

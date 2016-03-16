package com.bg.thsb.openstack.cache.updaters;

import com.bg.thsb.openstack.OSClientWrapper;
import com.bg.thsb.openstack.model.entities.Image;
import org.apache.log4j.Logger;
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
		final List<? extends org.openstack4j.model.compute.Image> list = OSClientWrapper.getOs().compute().images().list();
		list.forEach(o -> {
			Image iImage = new Image();
			iImage.setId(o.getId());
			iImage.setName(o.getName());
			cache.put(o.getId(), iImage);
		});
		logger.info(this.getClass().getName() + " refreshed");
	}
}

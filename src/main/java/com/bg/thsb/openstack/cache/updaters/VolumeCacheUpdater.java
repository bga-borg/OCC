package com.bg.thsb.openstack.cache.updaters;


import com.bg.thsb.openstack.OSClientWrapper;
import org.apache.log4j.Logger;
import org.openstack4j.model.storage.block.Volume;

import org.springframework.stereotype.Service;

import java.util.List;


/**
 * VolumeCacheUpdater
 *
 */
@Service
public class VolumeCacheUpdater extends CacheUpdater {
	private static final Logger logger = Logger.getLogger(VolumeCacheUpdater.class);

	@Override
	public void run() {
		try {
			final List<? extends Volume> list = OSClientWrapper.getOs().blockStorage().volumes().list();
			list.forEach(sourceVolume -> {
				final com.bg.thsb.openstack.model.entities.Volume destVolume = new com.bg.thsb.openstack.model.entities.Volume();
				modelMapper.map(sourceVolume, destVolume);
				cache.put(destVolume.getId(), destVolume);
			});
			logger.info(this.getClass().getName() + " refreshed");
		} catch (NullPointerException ex){
			ex.printStackTrace();
		}

	}
}

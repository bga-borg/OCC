package com.occ.openstack.cache.updaters;


import com.occ.dal.Dao;
import com.occ.openstack.OSClientWrapper;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.openstack4j.model.storage.block.Volume;

import java.util.List;


/**
 * VolumeCacheUpdater
 *
 */
public class VolumeCacheUpdater extends CacheUpdater {
	private static final Logger logger = Logger.getLogger(VolumeCacheUpdater.class);

	Dao<com.occ.openstack.model.entities.Volume> volumeDao = Dao.of(com.occ.openstack.model.entities.Volume.class);

	@Override
	public void run() {
		if(false) {
			try {
				ModelMapper modelMapper = new ModelMapper();
				final List<? extends Volume> list = OSClientWrapper.getOs().blockStorage().volumes().list();
				list.forEach(sourceVolume -> {
					final com.occ.openstack.model.entities.Volume destVolume = new com.occ.openstack.model.entities.Volume();
					modelMapper.map(sourceVolume, destVolume);
					volumeDao.put(destVolume);
				});
				logger.info(this.getClass().getName() + " refreshed");
			} catch (NullPointerException ex) {
				ex.printStackTrace();
			}
		}

	}
}

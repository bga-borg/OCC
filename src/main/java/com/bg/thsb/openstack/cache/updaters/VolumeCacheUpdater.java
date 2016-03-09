package com.bg.thsb.openstack.cache.updaters;

import com.bg.thsb.openstack.OSClientWrapper;
import org.openstack4j.model.storage.block.Volume;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * VolumeCacheUpdater
 *
 */
@Service
public class VolumeCacheUpdater extends CacheUpdater {
	@Override
	public void run() {
		try {
			final List<? extends Volume> list = OSClientWrapper.getOs().blockStorage().volumes().list();
			System.out.println(list);
		} catch (NullPointerException ex){
			ex.printStackTrace();
		}

	}
}

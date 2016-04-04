package com.bg.thsb.openstack.cache.updaters;


import com.bg.thsb.openstack.OSClientWrapper;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.openstack4j.model.identity.Tenant;
import org.openstack4j.model.storage.block.Volume;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * VolumeCacheUpdater
 *
 */
@Service
public class TenantCacheUpdater extends CacheUpdater {
	private static final Logger logger = Logger.getLogger(TenantCacheUpdater.class);

	@Override
	public void run() {
		try {
			ModelMapper modelMapper = new ModelMapper();
			final List<? extends Tenant> tenants = OSClientWrapper.getOs().identity().tenants().list();
			tenants.forEach(source -> {
				final com.bg.thsb.openstack.model.entities.KeystoneTenant dest = new com.bg.thsb.openstack.model.entities.KeystoneTenant();
				modelMapper.map(source, dest);
				cache.put(dest.getId(), dest);
			});
			logger.info(this.getClass().getName() + " refreshed");
		} catch (NullPointerException ex){
			ex.printStackTrace();
		}

	}
}

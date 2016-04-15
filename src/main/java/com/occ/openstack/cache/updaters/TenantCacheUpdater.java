package com.occ.openstack.cache.updaters;


import com.occ.dal.Dao;
import com.occ.dal.DataAccessInterface;
import com.occ.openstack.OSClientWrapper;
import com.occ.openstack.model.entities.KeystoneTenant;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.openstack4j.model.identity.Tenant;

import java.util.List;


/**
 * VolumeCacheUpdater
 */
public class TenantCacheUpdater extends CacheUpdater {
    private static final Logger logger = Logger.getLogger(TenantCacheUpdater.class);

    DataAccessInterface<KeystoneTenant> tenantDao = Dao.of(KeystoneTenant.class);

    @Override
    public void run() {
        try {
            ModelMapper modelMapper = new ModelMapper();
            final List<? extends Tenant> tenants = OSClientWrapper.getOs().identity().tenants().list();
            tenants.forEach(source -> {
                final KeystoneTenant dest = new KeystoneTenant();
                modelMapper.map(source, dest);
                tenantDao.put(dest);
            });
            logger.info(this.getClass().getName() + " refreshed");
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }

    }
}

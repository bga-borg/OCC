package com.bg.thsb.openstack.cache.updaters;


import com.bg.thsb.dal.Dao;
import com.bg.thsb.dal.DataAccessInterface;
import com.bg.thsb.openstack.OSClientWrapper;
import com.bg.thsb.openstack.model.entities.KeystoneTenant;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.openstack4j.model.identity.Tenant;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * VolumeCacheUpdater
 */
@Service
public class TenantCacheUpdater extends CacheUpdater {
    private static final Logger logger = Logger.getLogger(TenantCacheUpdater.class);

    DataAccessInterface<KeystoneTenant> tenantDao = Dao.of(KeystoneTenant.class);

    @Override
    public void run() {
        try {
            ModelMapper modelMapper = new ModelMapper();
            final List<? extends Tenant> tenants = OSClientWrapper.getOs().identity().tenants().list();
            tenants.forEach(source -> {
                final KeystoneTenant dest = new com.bg.thsb.openstack.model.entities.KeystoneTenant();
                modelMapper.map(source, dest);
                tenantDao.put(dest);
            });
            logger.info(this.getClass().getName() + " refreshed");
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }

    }
}

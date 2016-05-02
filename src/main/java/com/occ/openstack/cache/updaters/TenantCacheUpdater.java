package com.occ.openstack.cache.updaters;


import com.occ.dal.Dao;
import com.occ.dal.DataAccess;
import com.occ.openstack.OSClientWrapper;
import com.occ.openstack.model.entities.KeystoneTenant;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.openstack4j.model.identity.Tenant;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * VolumeCacheUpdater
 */
public class TenantCacheUpdater extends CacheUpdater {
    private static final Logger logger = Logger.getLogger(TenantCacheUpdater.class);

    DataAccess<KeystoneTenant> tenantDao = Dao.of(KeystoneTenant.class);

    @Override
    public void run() {
        try {
            ModelMapper modelMapper = new ModelMapper();
            final List<? extends Tenant> tenants = OSClientWrapper.getOs().identity().tenants().list();
            Set<KeystoneTenant> tenantSet = tenants.parallelStream().map(o -> modelMapper.map(o, KeystoneTenant.class)).collect(Collectors.toSet());
            tenantDao.put(tenantSet, null);
            logger.info(this.getClass().getName() + " refreshed");
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }

    }
}

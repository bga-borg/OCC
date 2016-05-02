package com.occ.openstack.cache.updaters;


import com.occ.dal.Dao;
import com.occ.openstack.OSClientWrapper;
import com.occ.openstack.model.entities.Volume;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * VolumeCacheUpdater
 */
public class VolumeCacheUpdater extends CacheUpdater {
    private static final Logger logger = Logger.getLogger(VolumeCacheUpdater.class);

    Dao<com.occ.openstack.model.entities.Volume> volumeDao = Dao.of(com.occ.openstack.model.entities.Volume.class);

    @Override
    public void run() {

        try {
            ModelMapper modelMapper = new ModelMapper();
            final List<? extends org.openstack4j.model.storage.block.Volume> list = OSClientWrapper.getOs().blockStorage().volumes().list();
            Set<Volume> volumeSet = list.parallelStream().map(o -> modelMapper.map(o, Volume.class)).collect(Collectors.toSet());
            volumeDao.put(volumeSet, null);
            logger.info(this.getClass().getName() + " refreshed");
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }

    }
}

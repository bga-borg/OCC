package com.bg.thsb.openstack.cache.updaters;

import com.bg.thsb.infinispan.InfinispanCacheWrapper;
import com.bg.thsb.openstack.OSClientWrapper;
import org.infinispan.Cache;
import org.openstack4j.api.OSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * CacheUpdater
 * Use ScheduledThreadPoolExecutor for scheduling threads
 * Run all updaters simultaneously
 *
 */
public abstract class CacheUpdater implements Runnable {

	Cache<String, Object> cache;

	CacheUpdater() {
		this.cache = InfinispanCacheWrapper.getCache();
	}

	@Override
	public abstract void run();
}

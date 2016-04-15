package com.occ.openstack.cache.updaters;

/**
 * CacheUpdater
 * Use ScheduledThreadPoolExecutor for scheduling threads
 * Run all updaters simultaneously
 *
 */
public abstract class CacheUpdater implements Runnable {

	@Override
	public abstract void run();
}

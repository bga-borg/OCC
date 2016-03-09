package com.bg.thsb.openstack.cache;

import com.bg.thsb.openstack.cache.updaters.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * UpdaterRunner
 *
 */

@Component
public class CacheUpdaterExecutor implements CommandLineRunner {
	ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;

	@Autowired
	ImageCacheUpdater imageCacheUpdater;
	@Autowired
	NetworkCacheUpdater networkCacheUpdater;
	@Autowired
	PortCacheUpdater portCacheUpdater;
	@Autowired
	ServerCacheUpdater serverCacheUpdater;
	@Autowired
	VolumeCacheUpdater volumeCacheUpdater;

	@Override
	public void run(String... args) throws Exception {
		scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(10);
		scheduledThreadPoolExecutor.scheduleWithFixedDelay(
			imageCacheUpdater, 0, 10, TimeUnit.SECONDS);
		scheduledThreadPoolExecutor.scheduleWithFixedDelay(
			networkCacheUpdater, 1, 12, TimeUnit.SECONDS);
		scheduledThreadPoolExecutor.scheduleWithFixedDelay(
			portCacheUpdater, 2, 14, TimeUnit.SECONDS);
		scheduledThreadPoolExecutor.scheduleWithFixedDelay(
			serverCacheUpdater, 3, 16, TimeUnit.SECONDS);
		scheduledThreadPoolExecutor.scheduleWithFixedDelay(
			volumeCacheUpdater, 5, 10, TimeUnit.SECONDS);
		scheduledThreadPoolExecutor.scheduleWithFixedDelay(
			new ExportCacheStatusToFile(), 10, 60, TimeUnit.SECONDS);
	}
}

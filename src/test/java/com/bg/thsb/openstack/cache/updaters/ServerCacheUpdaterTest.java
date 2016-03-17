package com.bg.thsb.openstack.cache.updaters;

import com.bg.thsb.infinispan.InfinispanCacheWrapper;
import org.junit.Test;

/**
 * ServerCacheUpdater
 *
 */
public class ServerCacheUpdaterTest {

	@Test
	public void testBuildWithCloud() throws Exception {
		//Build
		ServerCacheUpdater serverCacheUpdater = new ServerCacheUpdater();

		//Operate
		serverCacheUpdater.run();

		//Check
		InfinispanCacheWrapper.getCache().keySet().forEach(key -> {
				System.out.println(InfinispanCacheWrapper.getCache().get(key));
			}
		);

	}
}
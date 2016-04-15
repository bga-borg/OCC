package com.occ.openstack.cache.updaters;

import com.occ.infinispan.InfinispanCacheWrapper;
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
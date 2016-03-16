package updater;

import com.bg.thsb.infinispan.InfinispanCacheWrapper;
import com.bg.thsb.openstack.cache.updaters.ServerCacheUpdater;
import org.junit.Test;

/**
 * ServerCacheUpdater
 *
 */
public class TestServerCacheUpdater {

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
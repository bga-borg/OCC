package com.bg.thsb.infinispan;

import org.infinispan.Cache;
import org.infinispan.manager.DefaultCacheManager;

import java.io.IOException;


public class InfinispanCacheWrapper {
	public static final String OBJECT_IN_MEM_CACHE = "objectInMem";
	private static DefaultCacheManager infinispanDefaultCacheManager = null;

	public static Cache<String, Object> getCache() {
		if (infinispanDefaultCacheManager == null) {
			try {
				infinispanDefaultCacheManager = new DefaultCacheManager("infinispan-configuration.xml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return infinispanDefaultCacheManager.getCache(OBJECT_IN_MEM_CACHE);
	}
}

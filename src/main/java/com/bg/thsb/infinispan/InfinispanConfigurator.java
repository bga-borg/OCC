package com.bg.thsb.infinispan;

import org.infinispan.Cache;
import org.infinispan.manager.DefaultCacheManager;

import java.io.IOException;

public class InfinispanConfigurator {

	Cache<String, Object> stringToObjectCacheInMem = null;
	DefaultCacheManager defaultCacheManager = null;
	private Cache<String, Object> stringToObjectCacheOnDisk = null;

	public InfinispanConfigurator(Boolean log) {

		try {
			defaultCacheManager = new DefaultCacheManager("infinispan-configuration.xml");
			stringToObjectCacheInMem = defaultCacheManager.getCache("objectInMem");
			stringToObjectCacheOnDisk = defaultCacheManager.getCache("objectOnDisk");


			if (log) {
				stringToObjectCacheInMem.addListener(new InfinispanEventListener());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Cache<String, Object> getStringToObjectCacheInMem() {
		return stringToObjectCacheInMem;
	}

	public Cache<String, Object> getStringObjectCacheOnDisk() {
		return stringToObjectCacheOnDisk;
	}
}

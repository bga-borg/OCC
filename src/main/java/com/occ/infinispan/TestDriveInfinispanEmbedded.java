package com.occ.infinispan;

import com.occ.testdrive.TestDrive;
import com.occ.testdrive.TestResult;
import org.infinispan.Cache;
import org.infinispan.manager.DefaultCacheManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class TestDriveInfinispanEmbedded implements TestDrive {

	private Cache<Object, Object> cache;

	@Override
	public void connect() throws Exception {
		cache = new DefaultCacheManager("infinispan-configuration.xml")
			.getCache("objectInMem");

	}

	@Override
	public void disconnect() throws Exception {

	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public ConnectionStatus getConnectionStatus() {
		return null;
	}

	@Override
	public List<Callable<TestResult>> getTests() {
		List<Callable<TestResult>> callableList = new ArrayList<>();
//
//		callableList.add(new LifeSpanTest(cache));

		return callableList;
	}
}

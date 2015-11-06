package com.bg.thsb.infinispan;

import com.bg.thsb.testdrive.TestDrive;
import com.bg.thsb.testdrive.TestResult;
import org.infinispan.Cache;
import org.infinispan.manager.DefaultCacheManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/*
	TODO Complete Infinispan connect/disconnect
	TODO Basic tests
  */
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

		callableList.add(new LifeSpanTest(cache));

		return callableList;
	}
}

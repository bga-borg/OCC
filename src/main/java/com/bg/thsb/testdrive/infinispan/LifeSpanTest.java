package com.bg.thsb.testdrive.infinispan;

import com.bg.thsb.plainmodel.PlainModelFactory;
import com.bg.thsb.plainmodel.Server;
import com.bg.thsb.testdrive.TestResult;
import org.infinispan.Cache;

import java.util.concurrent.Callable;

/**
 * LifeSpanTest
 *
 */
public class LifeSpanTest implements Callable<TestResult> {
	Cache<Object, Object> cache;

	public LifeSpanTest(Cache<Object, Object> c) {
		cache = c;
	}

	@Override
	public TestResult call() throws Exception {

		Server s = PlainModelFactory.getPlainServerWithVolumes("Server1");

		cache.put("Server1", s);

		Server server1 = (Server) cache.get("Server1");

		return new TestResult.Builder()
			.setLog("")
			.setTestName(LifeSpanTest.class.getSimpleName())
			.build();
	}
}

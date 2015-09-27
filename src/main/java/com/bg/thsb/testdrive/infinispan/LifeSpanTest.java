package com.bg.thsb.testdrive.infinispan;

import com.bg.thsb.testdrive.TestResult;
import org.infinispan.Cache;

import java.util.Random;
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

		for (int i = 0; i < 100; i++) {
			final int nextInt = new Random(i).nextInt(500);
			cache.put(i, nextInt);
			Thread.sleep(nextInt);
			System.out.print("Added " + i + ". " + cache.get(i) + "; ");
			System.out.println("Size: " + cache.size());
		}

		return new TestResult.Builder()
			.setLog("")
			.setTestName(LifeSpanTest.class.getSimpleName())
			.build();
	}
}

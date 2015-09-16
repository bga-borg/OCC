package com.bg.thsb.testdrive.redis;

import com.bg.thsb.testdrive.TestDrive;
import com.bg.thsb.testdrive.TestDriveConfig;
import com.bg.thsb.testdrive.TestResult;
import redis.clients.jedis.Jedis;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

public class TestDriveRedis implements TestDrive {
	ConnectionStatus connectionStatus = ConnectionStatus.DISCONNECTED;

	private Jedis jedis;
	Callable<TestResult> simpleSetTest = new Callable<TestResult>() {
		@Override
		public TestResult call() throws Exception {

			final String set = jedis.set("a", "foo");

			return new TestResult.Builder()
				.setTestName("Redis simpleSetTest")
				.setLog(set)
				.setTimeInMs(0)
				.build();
		}
	};

	@Override
	public void connect() {
		try {
			jedis = new Jedis(TestDriveConfig.REDIS_SERVER_URI);
		} catch (Exception e) {
			e.printStackTrace();
			this.connectionStatus = ConnectionStatus.ERROR;
		}
		this.connectionStatus = ConnectionStatus.CONNECTED;
	}

	@Override
	public void disconnect() {
		try {
			jedis.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
			this.connectionStatus = ConnectionStatus.ERROR;
		}
		this.connectionStatus = ConnectionStatus.CONNECTED;
	}

	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}

	@Override
	public ConnectionStatus getConnectionStatus() {
		return null;
	}

	@Override
	public List<Callable<TestResult>> getTests() {
		return Arrays.asList(simpleSetTest);
	}
}

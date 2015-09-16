package com.bg.thsb.testdrive.cassandra;

import com.bg.thsb.testdrive.TestDrive;
import com.bg.thsb.testdrive.TestResult;

import java.util.List;
import java.util.concurrent.Callable;

public class TestDriveCassandraAsync implements TestDrive {

	@Override
	public void connect() {
	}

	@Override
	public void disconnect() {
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
		return null;
	}
}

package com.bg.thsb.testdrive.couchdb;

import com.bg.thsb.testdrive.TestDrive;
import com.bg.thsb.testdrive.TestResult;

import java.util.List;
import java.util.concurrent.Callable;

public class TestDriveCouchDb implements TestDrive {
	@Override
	public void connect() throws Exception {
	}

	@Override
	public void disconnect() throws Exception {

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
		return null;
	}
}

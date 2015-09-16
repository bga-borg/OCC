package com.bg.thsb.testdrive.infinispan;

import com.bg.thsb.testdrive.TestDrive;
import com.bg.thsb.testdrive.TestResult;

import java.util.List;
import java.util.concurrent.Callable;

/*

	TODO Complete Infinispan connect/disconnect
	TODO Basic tests
  */
public class TestDriveInfinispan implements TestDrive {
	@Override
	public void connect() throws Exception {

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
		return null;
	}
}

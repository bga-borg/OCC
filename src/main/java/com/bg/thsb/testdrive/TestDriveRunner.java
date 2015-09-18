package com.bg.thsb.testdrive;

import com.bg.thsb.testdrive.couchdb.TestDriveCouchDb;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static java.util.Arrays.asList;



public class TestDriveRunner implements Runnable {
	final static List<Class<TestDriveCouchDb>> testClasses = asList(
		//		TestDriveMongoDb.class,
		//		TestDriveRedis.class
		//		TestDriveCassandraSync.class
		TestDriveCouchDb.class
	);

	Logger logger = LoggerFactory.getLogger(TestDriveRunner.class);
	ExecutorService executor = Executors.newFixedThreadPool(3);
	private List<TestDrive> testDrives;

	TestDriveRunner() {
		testDrives = Lists.newArrayList();
		for (Class testClass : testClasses) {
			try {
				testDrives.add((TestDrive) testClass.newInstance());
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new TestDriveRunner().run();
		System.exit(0);
	}

	@Override
	public void run() {
		for (TestDrive testDrive : testDrives) {
			try {
				testDrive.connect();
			} catch (Exception e) {
				logger.error(testDrive.getName() + " failed to connect!!!");
				e.printStackTrace();
				return;
			}

			logger.info(testDrive.getName() + " connected succesfully.");

			try {
				List<Future<TestResult>> testResultFutures = executor.invokeAll(testDrive.getTests());

				for (Future<TestResult> testResultFuture : testResultFutures) {
					TestResult testResult = testResultFuture.get();
					if (testResult != null) {
						logger.info("\n\n+---------------------------------------------------------------------+\n" +
							testResult.testName + "\n\n" + testResult.log + "\n" +
							"Finished in: " + testResult.timeInMs + "ms\n " +
							"+---------------------------------------------------------------------+\n\n\n");
					} else {
						throw new NullPointerException("TestResult was null in " + testDrive.getName());
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// try to disconnect
				try {
					testDrive.disconnect();
				} catch (Exception e) {
					logger.error(testDrive.getName() + " failed to disconnect!!!");
					e.printStackTrace();
					return;
				}
			}
		}
	}
}

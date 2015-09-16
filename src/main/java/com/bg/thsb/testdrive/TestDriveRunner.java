package com.bg.thsb.testdrive;

import com.bg.thsb.testdrive.cassandra.TestDriveCassandraSync;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.*;

import static java.util.Arrays.asList;



public class TestDriveRunner implements Runnable {
	final static List<Class<TestDriveCassandraSync>> testClasses = asList(
		//		TestDriveMongoDb.class,
		//		TestDriveRedis.class
		TestDriveCassandraSync.class
	);

	Logger logger = LoggerFactory.getLogger(TestDriveRunner.class);
	ExecutorService executor = Executors.newFixedThreadPool(3);
	private List<TestDrive> testDrives;

	public static void main(String[] args) {
		new TestDriveRunner().run();
		System.exit(0);
	}

	@Override
	public void run() {
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

		for (TestDrive testDrive : testDrives) {
			try {
				testDrive.connect();
			} catch (Exception e) {
				logger.error(testDrive.getName() + " failed to connect!!!");
				e.printStackTrace();
				return;
			}

			logger.info(testDrive.getName() + " connected succesfully.");

			List<Callable<TestResult>> tests = testDrive.getTests();
			List<Future<TestResult>> testResultFutures = Lists.newArrayList();
			try {
				testResultFutures = executor.invokeAll(tests);

				for (Future<TestResult> testResultFuture : testResultFutures) {
					TestResult testResult = null;

					testResult = testResultFuture.get();


					if (testResult != null) {
						logger.info("\n\n+---------------------------------------------------------------------+\n" +
							testResult.testName + "\n\n" + testResult.log + "\n" +
							"Finished in: " + testResult.timeInMs + "ms\n " +
							"+---------------------------------------------------------------------+\n\n\n");
					} else {
						logger.error("TestResult was null in " + testDrive.getName());
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}

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

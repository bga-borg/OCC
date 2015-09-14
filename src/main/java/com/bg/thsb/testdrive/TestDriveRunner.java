package com.bg.thsb.testdrive;

import com.bg.thsb.testdrive.mongodb.TestDriveMongoDb;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.*;

import static java.util.Arrays.asList;


public class TestDriveRunner implements Runnable {

    public static List<Class<TestDriveMongoDb>> testClasses = asList(
        TestDriveMongoDb.class
    );
    Logger logger = LoggerFactory.getLogger(TestDriveRunner.class);
    ExecutorService executor = Executors.newFixedThreadPool(3);
    private List<TestDrive> testDrives;

    public static void main(String[] args) {
        new TestDriveRunner().run();
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

        ForAllTest:
        for (TestDrive testDrive : testDrives) {
            TestDrive.ConnectionStatus connectionStatus = testDrive.connect();
            if (connectionStatus != TestDrive.ConnectionStatus.CONNECTED) {
                logger.error(testDrive.getName() + " failed to connect!!!");
                continue ForAllTest;
            }
            logger.info(testDrive.getName() + " connected succesfully.");

            List<Callable<TestResult>> tests = testDrive.getTests();

            List<Future> futureTestResults = Lists.newArrayList();
            for (Callable<TestResult> task : tests) {
                futureTestResults.add(executor.submit(task));
            }

            for (Future<TestResult> testResultFuture : futureTestResults) {
                TestResult testResult = null;
                try {
                    testResult = testResultFuture.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                if (testResult != null) {
                    logger.info("\n\n+---------------------------------------------------------------------+\n" +
                            testResult.testName + "\n" + testResult.log + "\n" +
                            "Finished in: " + testResult.timeInMs + "ms\n " +
                            "+---------------------------------------------------------------------+\n\n\n");
                } else {
                    logger.error("TestResult was null in " + testDrive.getName());
                }
            }

            TestDrive.ConnectionStatus disconnectStatus = testDrive.disconnect();
            if (disconnectStatus != TestDrive.ConnectionStatus.DISCONNECTED) {
                logger.error(testDrive.getName() + " failed to disconnect!!!");
                continue ForAllTest;
            }
        }

    }
}

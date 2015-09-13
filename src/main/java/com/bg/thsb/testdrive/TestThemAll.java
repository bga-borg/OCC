package com.bg.thsb.testdrive;

import com.bg.thsb.testdrive.mongodb.TestDriveMongoDb;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.*;

import static java.util.Arrays.asList;


public class TestThemAll implements Runnable {

    Logger logger = LoggerFactory.getLogger(TestThemAll.class);
    ExecutorService executor = Executors.newFixedThreadPool(3);

    public static List<Class> testClasses = asList(
            TestDriveMongoDb.class
    );

    private List<TestDrive> testDrives;

    public static void main(String[] args) {
        new TestThemAll().run();
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
            testDrive.connect();

            List<FutureTask<TestResult>> tests = testDrive.getTests();

            List<Future<TestResult>> futureTestResults = Lists.newArrayList();
            for (FutureTask<TestResult> task : tests) {
                futureTestResults.add((Future<TestResult>) executor.submit(task));
            }

            for(Future<TestResult> testResultFuture : futureTestResults){
                TestResult testResult = null;
                try {
                    testResult = testResultFuture.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                if(testResult != null){
                    System.out.println("+---------------------------------------------------------------------+");
                    System.out.println(testResult.testName);
                    System.out.println(testResult.log);
                    System.out.println("Finished in: " + testResult.timeInMs + "ms");
                    System.out.println("+---------------------------------------------------------------------+");
                    System.out.println();
                    System.out.println();
                }
            }

            testDrive.disconnect();
        }


    }
}

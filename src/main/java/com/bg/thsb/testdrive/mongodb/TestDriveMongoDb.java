package com.bg.thsb.testdrive.mongodb;

import com.bg.thsb.testdrive.TestDrive;
import com.bg.thsb.testdrive.TestResult;
import com.mongodb.async.client.MongoClient;
import com.mongodb.async.client.MongoClients;
import com.mongodb.async.client.MongoDatabase;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;


public class TestDriveMongoDb implements TestDrive {
    @Override
    public ConnectionStatus connect() {
        // Use a Connection String
        MongoClient mongoClient = MongoClients.create("mongodb://192.168.1.187");

        /* provide custom MongoClientSettings
        ClusterSettings clusterSettings = ClusterSettings.builder().hosts(asList(new ServerAddress("localhost"))).build();
        MongoClientSettings settings = MongoClientSettings.builder().clusterSettings(clusterSettings).build();
        MongoClient mongoClient = MongoClients.create(settings);
        */

        MongoDatabase database = mongoClient.getDatabase("mydb");


        String name = database.getName();

        return ConnectionStatus.CONNECTED;
    }

    @Override
    public ConnectionStatus disconnect() {
        return ConnectionStatus.DISCONNECTED;
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public List<FutureTask<TestResult>> getTests() {
        return Arrays.asList(
                new FutureTask<>(new Test1())
        );
    }

    public static class Test1 implements Callable<TestResult> {

        @Override
        public TestResult call() throws Exception {
            return new TestResult.Builder()
                    .setTestName("Test1")
                    .setLog("This is the log of Test1")
                    .setTimeInMs(500)
                    .build();
        }
    }

}

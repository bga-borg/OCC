package com.bg.thsb.testdrive.mongodb;

import com.bg.thsb.testdrive.Configuration;
import com.bg.thsb.testdrive.TestDrive;
import com.bg.thsb.testdrive.TestResult;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;


public class TestDriveMongoDb implements TestDrive {

    private MongoClient mongoClient;

    @Override
    public ConnectionStatus connect() {
        try {
            mongoClient = new MongoClient(Configuration.MONGO_CLIENT_URI);
        } catch (Exception e) {
            e.printStackTrace();
            return ConnectionStatus.ERROR;
        }

        return ConnectionStatus.CONNECTED;
    }

    @Override
    public ConnectionStatus disconnect() {
        try {
            mongoClient.close();
        } catch (Exception e) {
            e.printStackTrace();
            return ConnectionStatus.ERROR;
        }
        return ConnectionStatus.DISCONNECTED;
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public List<FutureTask<TestResult>> getTests() {
        return Arrays.asList(new FutureTask<>(
                        new Test1()
                )
        );
    }

    public class Test1 implements Callable<TestResult> {

        @Override
        public TestResult call() throws Exception {
            // get handle to "mydb" database
            MongoDatabase database = mongoClient.getDatabase("test");


            // get a handle to the "test" collection
            MongoCollection<Document> collection = database.getCollection("test");

            // drop all the data in it
            collection.drop();


            // make a document and insert it
            Document doc = new Document("name", "MongoDB")
                    .append("type", "database")
                    .append("count", 1)
                    .append("info", new Document("x", 203).append("y", 102));

            collection.insertOne(doc);

            // get it (since it's the only one in there since we dropped the rest earlier on)
            Document myDoc = collection.find().first();
            System.out.println(myDoc.toJson());

            return new TestResult.Builder()
                    .setTestName("Test1")
                    .setLog("This is the log of Test1")
                    .setTimeInMs(500)
                    .build();
        }
    }

}

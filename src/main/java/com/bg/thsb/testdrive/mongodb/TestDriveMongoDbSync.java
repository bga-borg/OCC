package com.bg.thsb.testdrive.mongodb;

import com.bg.thsb.testdrive.TestDrive;
import com.bg.thsb.testdrive.TestDriveConfig;
import com.bg.thsb.testdrive.TestResult;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;


public class TestDriveMongoDbSync implements TestDrive {
	ConnectionStatus connectionStatus = ConnectionStatus.DISCONNECTED;


	private MongoClient mongoClient;
	Callable<TestResult> test1 = new Callable<TestResult>() {

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

			final TestResult testResult = new TestResult.Builder()
				.setTestName("Test1")
				.setLog("This is the log of Test1")
				.setTimeInMs(500)
				.build();

			return testResult;
		}
	};

	@Override
	public void connect() {
		try {
			mongoClient = new MongoClient(TestDriveConfig.MONGO_SERVER_URI);
		} catch (Exception e) {
			e.printStackTrace();
			this.connectionStatus = ConnectionStatus.ERROR;
		}

		this.connectionStatus = ConnectionStatus.CONNECTED;
	}

	@Override
	public void disconnect() {
		try {
			mongoClient.close();
		} catch (Exception e) {
			e.printStackTrace();
			this.connectionStatus = ConnectionStatus.ERROR;
		}
		this.connectionStatus = ConnectionStatus.DISCONNECTED;
	}

	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}

	@Override
	public ConnectionStatus getConnectionStatus() {
		return this.connectionStatus;
	}

	@Override
	public List<Callable<TestResult>> getTests() {
		return Arrays.asList(test1);
	}
}

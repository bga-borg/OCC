package com.bg.thsb.testdrive.couchdb;

import com.bg.thsb.testdrive.TestDrive;
import com.bg.thsb.testdrive.TestDriveConfig;
import com.bg.thsb.testdrive.TestResult;
import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;
import org.ektorp.support.CouchDbRepositorySupport;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

public class TestDriveCouchDb implements TestDrive {

	ConnectionStatus connectionStatus = ConnectionStatus.DISCONNECTED;
	private HttpClient httpClient;
	Callable<TestResult> simpleInsertAndRetrieveTest = new Callable<TestResult>() {
		@Override
		public TestResult call() throws Exception {
			CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
			CouchDbConnector db = new StdCouchDbConnector("mydatabase", dbInstance);

			db.createDatabaseIfNotExists();

			Sofa sofa = db.get(Sofa.class, "ektorp");
			sofa.setColor("blue");

			return null;
		}
	};

	@Override
	public void connect() throws Exception {
		httpClient = new StdHttpClient.Builder()
			.url(TestDriveConfig.COUCHDB_SERVER_URI)
			.build();
		this.connectionStatus = ConnectionStatus.CONNECTED;
	}

	@Override
	public void disconnect() throws Exception {
		httpClient.shutdown();
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
		return Arrays.asList(simpleInsertAndRetrieveTest);
	}


	public static class Sofa extends CouchDbRepositorySupport<Sofa> {
		String color = "none";

		protected Sofa(Class<Sofa> type, CouchDbConnector db) {
			super(type, db);
		}

		public String getColor() {
			return color;
		}

		public void setColor(String color) {
			this.color = color;
		}
	}
}

package com.bg.thsb.testdrive.cassandra;

import com.bg.thsb.testdrive.TestDrive;
import com.bg.thsb.testdrive.TestDriveConfig;
import com.bg.thsb.testdrive.TestResult;
import com.datastax.driver.core.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

public class TestDriveCassandraSync implements TestDrive {
	private final String KEYSPACE_NAME = "demo";
	Cluster cluster;
	Session session;
	ConnectionStatus connectionStatus = ConnectionStatus.DISCONNECTED;

	Logger logger = LoggerFactory.getLogger(TestDriveCassandraSync.class);
	Callable<TestResult> testInsert = new Callable<TestResult>() {
		@Override
		public TestResult call() throws Exception {
			final long start = new Date().getTime();

			session.execute(
				"CREATE TABLE " + KEYSPACE_NAME + ".songs (" +
					"id uuid PRIMARY KEY," +
					"title text," +
					"album text," +
					"artist text," +
					"tags set<text>," +
					"data blob" +
					");");
			session.execute(
				"CREATE TABLE " + KEYSPACE_NAME + ".playlists (" +
					"id uuid," +
					"title text," +
					"album text, " +
					"artist text," +
					"song_id uuid," +
					"PRIMARY KEY (id, title, album, artist)" +
					");");

			session.execute(
				"INSERT INTO " + KEYSPACE_NAME + ".songs (id, title, album, artist, tags) " +
					"VALUES (" +
					"756716f7-2e54-4715-9f00-91dcbea6cf50," +
					"'La Petite Tonkinoise'," +
					"'Bye Bye Blackbird'," +
					"'Joséphine Baker'," +
					"{'jazz', '2013'})" +
					";");
			session.execute(
				"INSERT INTO " + KEYSPACE_NAME + ".playlists (id, song_id, title, album, artist) " +
					"VALUES (" +
					"2cc9ccb7-6221-4ccb-8387-f22b6a1b354d," +
					"756716f7-2e54-4715-9f00-91dcbea6cf50," +
					"'La Petite Tonkinoise'," +
					"'Bye Bye Blackbird'," +
					"'Joséphine Baker'" +
					");");

			ResultSet results = session.execute("SELECT * FROM " + KEYSPACE_NAME + ".playlists " +
				"WHERE id = 2cc9ccb7-6221-4ccb-8387-f22b6a1b354d;");

			StringBuilder sb = new StringBuilder();

			sb.append(String.format("%-30s\t%-20s\t%-20s\n%s", "title", "album", "artist",
				"-------------------------------+-----------------------+--------------------\n"));
			for (Row row : results) {
				sb.append(String.format("%-30s\t%-20s\t%-20s\n", row.getString("title"),
					row.getString("album"), row.getString("artist")));
			}
			sb.append("\n");

			final long end = new Date().getTime();

			return new TestResult.Builder()
				.setTestName("testInsert")
				.setLog(sb.toString())
				.setTimeInMs(end - start)
				.build();
		}
	};

	@Override
	public void connect() throws Exception {
		// Connect to the cluster and keyspace "KEYSPACE_NAME"
		try {
			cluster = Cluster.builder().addContactPoint(TestDriveConfig.CASSANDRA_SERVER_URI).build();
			session = cluster.connect();

			logger.debug("Trying to create '" + KEYSPACE_NAME + "' keyspace");

			final List<KeyspaceMetadata> keyspaces = cluster.getMetadata().getKeyspaces();
			for (KeyspaceMetadata keyspaceMetadata : keyspaces) {
				if (keyspaceMetadata.getName().equals(KEYSPACE_NAME)) {
					logger.debug("Keyspace '" + KEYSPACE_NAME + "' already exists.");
					return;
				}
			}

			session.execute("CREATE KEYSPACE " + KEYSPACE_NAME + " WITH replication " +
				"= {'class':'SimpleStrategy', 'replication_factor':3};");

			logger.debug("Keyspace '" + KEYSPACE_NAME + "' created.");

		} catch (Exception e) {
			e.printStackTrace();
			this.connectionStatus = ConnectionStatus.ERROR;
			throw e;
		}
		this.connectionStatus = ConnectionStatus.CONNECTED;
	}

	@Override
	public void disconnect() {
		try {
			session.execute("DROP KEYSPACE " + KEYSPACE_NAME + ";");
			logger.debug(KEYSPACE_NAME + " keyspace cleaned up");

			session.close();
			cluster.close();
		} catch (Exception e) {
			e.printStackTrace();
			this.connectionStatus = ConnectionStatus.ERROR;
			throw e;
		}
		this.connectionStatus = ConnectionStatus.DISCONNECTED;
	}

	@Override
	public String getName() {
		return getClass().getSimpleName();
	}

	@Override
	public ConnectionStatus getConnectionStatus() {
		return connectionStatus;
	}

	@Override
	public List<Callable<TestResult>> getTests() {
		return Arrays.asList(testInsert);
	}
}

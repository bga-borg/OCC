package com.bg.thsb;

import static java.util.concurrent.TimeUnit.SECONDS;


public interface Constants {
	int NUM_RECORDS = 1000000;

	// The provider configures jclouds To use the Rackspace Cloud (US)
	// To use the Rackspace Cloud (UK) set the system property or default value to "rackspace-cloudblockstorage-uk"
	String PROVIDER = System.getProperty("provider.cbs", "rackspace-cloudblockstorage-uk");
	String REGION = System.getProperty("region", "IAD");


	String NAME = "jclouds-example";
	String POLL_PERIOD_TWENTY_SECONDS = String.valueOf(SECONDS.toMillis(20));
	String ROOT = "root";
	String PASSWORD = "sbmFPqaw5d43";
	String DEVICE = "/dev/xvdd";
}

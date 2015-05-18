package com.bg.thsb;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by bg on 2015.05.17..
 */
public class Constants {
    public final static int NUM_RECORDS = 1000000;

    // The provider configures jclouds To use the Rackspace Cloud (US)
    // To use the Rackspace Cloud (UK) set the system property or default value to "rackspace-cloudblockstorage-uk"
    public static final String PROVIDER = System.getProperty("provider.cbs", "rackspace-cloudblockstorage-uk");
    public static final String REGION = System.getProperty("region", "IAD");


    public static final String NAME = "jclouds-example";
    public static final String POLL_PERIOD_TWENTY_SECONDS = String.valueOf(SECONDS.toMillis(20));
    public static final String ROOT = "root";
    public static final String PASSWORD = "sbmFPqaw5d43";
    public static final String DEVICE = "/dev/xvdd";
}

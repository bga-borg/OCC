package com.bg.thsb.testdrive;


import java.util.List;
import java.util.concurrent.FutureTask;

public interface TestDrive {

    ConnectionStatus connect();
    ConnectionStatus disconnect();

    String getName();

    List<FutureTask<TestResult>> getTests();

    public static enum ConnectionStatus {
        DISCONNECTED,
        CONNECTED,
        ERROR
    }

}

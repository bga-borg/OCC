package com.bg.thsb.testdrive;


import java.util.List;
import java.util.concurrent.Callable;

public interface TestDrive {

    ConnectionStatus connect();

    ConnectionStatus disconnect();

    String getName();

    List<Callable<TestResult>> getTests();

    enum ConnectionStatus {
        DISCONNECTED,
        CONNECTED,
        ERROR
    }

}

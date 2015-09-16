package com.bg.thsb.testdrive;


import java.util.List;
import java.util.concurrent.Callable;

public interface TestDrive {

    void connect() throws Exception;

    void disconnect() throws Exception;

    String getName();

    ConnectionStatus getConnectionStatus();

    List<Callable<TestResult>> getTests();

    enum ConnectionStatus {
        DISCONNECTED,
        CONNECTED,
        ERROR
    }

}

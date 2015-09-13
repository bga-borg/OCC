package com.bg.thsb.testdrive;

public class TestResult {
    public static class Builder {
        private String testName;
        private String log;
        private int timeInMs;

        public Builder setTestName(String testName) {
            this.testName = testName;
            return this;
        }

        public Builder setLog(String log) {
            this.log = log;
            return this;
        }

        public Builder setTimeInMs(int timeInMs) {
            this.timeInMs = timeInMs;
            return this;
        }

        public TestResult build() {
            return new TestResult(testName, log, timeInMs);
        }
    }

    private TestResult(String testName, String log, int timeInMs) {
        this.testName = testName;
        this.log = log;
        this.timeInMs = timeInMs;
    }

    String testName;
    String log;
    int timeInMs;
}

package com.occ.testdrive;

public class TestResult {
    String testName;
    String log;
    long timeInMs;

    private TestResult(String testName, String log, long timeInMs) {
        this.testName = testName;
        this.log = log;
        this.timeInMs = timeInMs;
    }


    public static class Builder {
        private String testName;
        private String log;
        private long timeInMs;

        public Builder setTestName(String testName) {
            this.testName = testName;
            return this;
        }

        public Builder setLog(String log) {
            this.log = log;
            return this;
        }

        public Builder setTimeInMs(long timeInMs) {
            this.timeInMs = timeInMs;
            return this;
        }

        public TestResult build() {
            return new TestResult(testName, log, timeInMs);
        }
    }
}

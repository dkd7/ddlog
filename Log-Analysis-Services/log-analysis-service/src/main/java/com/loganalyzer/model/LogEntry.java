package com.loganalyzer.model;

import java.time.LocalDateTime;

public class LogEntry {
    private LocalDateTime timestamp;
    private String logLevel;
    private String threadId;
    private String loggerName;
    private String message;

    public LogEntry(LocalDateTime timestamp, String logLevel, String threadId, String loggerName, String message) {
        this.timestamp = timestamp;
        this.logLevel = logLevel;
        this.threadId = threadId;
        this.loggerName = loggerName;
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public String getThreadId() {
        return threadId;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "[" + timestamp + "] [" + logLevel + "] [" + threadId + "] [" + loggerName + "] - " + message;
    }
}
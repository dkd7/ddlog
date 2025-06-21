package com.loganalyzer.analysis;

import com.loganalyzer.model.LogEntry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountByLogLevelStrategy implements LogAnalysisStrategy {
    private final Map<String, Integer> levelCount = new HashMap<>();

    @Override
    public void analyze(List<LogEntry> entries) {
        for (LogEntry entry : entries) {
            levelCount.put(entry.getLogLevel(), levelCount.getOrDefault(entry.getLogLevel(), 0) + 1);
        }
    }

    @Override
    public String getReport() {
        StringBuilder sb = new StringBuilder("=== Log Level Count ===\n");
        levelCount.forEach((level, count) -> sb.append(level).append(": ").append(count).append("\n"));
        return sb.toString();
    }
}
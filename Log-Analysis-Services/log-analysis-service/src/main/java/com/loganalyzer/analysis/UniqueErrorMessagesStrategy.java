package com.loganalyzer.analysis;

import com.loganalyzer.model.LogEntry;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UniqueErrorMessagesStrategy implements LogAnalysisStrategy {
    private final Set<String> uniqueErrors = new HashSet<>();

    @Override
    public void analyze(List<LogEntry> entries) {
        for (LogEntry entry : entries) {
            if ("ERROR".equals(entry.getLogLevel())) {
                uniqueErrors.add(entry.getMessage());
            }
        }
    }

    @Override
    public String getReport() {
        StringBuilder sb = new StringBuilder("=== Unique ERROR Messages ===\n");
        int i = 1;
        for (String msg : uniqueErrors) {
            sb.append(i++).append(". ").append(msg).append("\n");
        }
        return sb.toString();
    }
}
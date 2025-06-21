package com.loganalyzer.analysis;

import com.loganalyzer.model.LogEntry;

import java.util.List;

public interface LogAnalysisStrategy {
    void analyze(List<LogEntry> entries);
    String getReport();
}
package com.loganalyzer;

import com.loganalyzer.analysis.CountByLogLevelStrategy;
import com.loganalyzer.model.LogEntry;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CountByLogLevelStrategyTest {

    @Test
    public void testCountByLogLevel() {
        CountByLogLevelStrategy strategy = new CountByLogLevelStrategy();
        List<LogEntry> logs = List.of(
                new LogEntry(LocalDateTime.now(), "INFO", "main", "App", "Started"),
                new LogEntry(LocalDateTime.now(), "ERROR", "main", "App", "Crashed"),
                new LogEntry(LocalDateTime.now(), "ERROR", "main", "App", "Crashed again")
        );
        strategy.analyze(logs);
        String report = strategy.getReport();
        assertTrue(report.contains("INFO: 1"));
        assertTrue(report.contains("ERROR: 2"));
    }
}
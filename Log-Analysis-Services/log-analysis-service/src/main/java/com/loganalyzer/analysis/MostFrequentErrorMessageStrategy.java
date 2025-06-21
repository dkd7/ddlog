package com.loganalyzer.analysis;

import com.loganalyzer.model.LogEntry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MostFrequentErrorMessageStrategy implements LogAnalysisStrategy {
    private String mostFrequentMessage = "";
    private int maxCount = 0;

    @Override
    public void analyze(List<LogEntry> entries) {
        Map<String, Integer> freqMap = new HashMap<>();
        for (LogEntry entry : entries) {
            if ("ERROR".equals(entry.getLogLevel())) {
                String msg = entry.getMessage();
                int count = freqMap.getOrDefault(msg, 0) + 1;
                freqMap.put(msg, count);
                if (count > maxCount) {
                    maxCount = count;
                    mostFrequentMessage = msg;
                }
            }
        }
    }

    @Override
    public String getReport() {
        return "=== Most Frequent ERROR Message ===\n\"" + mostFrequentMessage + "\" occurred " + maxCount + " times.\n";
    }
}
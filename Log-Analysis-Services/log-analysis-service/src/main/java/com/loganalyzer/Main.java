package com.loganalyzer;

import com.loganalyzer.analysis.*;
import com.loganalyzer.model.LogEntry;
import com.loganalyzer.parser.DefaultLogParser;
import com.loganalyzer.parser.LogParser;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Usage: java -jar log-analyzer.jar <logfile1> <logfile2> ...");
            return;
        }

        LogParser parser = new DefaultLogParser();
        List<LogEntry> allEntries = new ArrayList<>();

        for (String filePath : args) {
            try {
                List<String> lines = Files.readAllLines(Paths.get(filePath));
                for (String line : lines) {
                    parser.parseLine(line).ifPresent(allEntries::add);
                }
            } catch (IOException e) {
                System.err.println("Failed to read file: " + filePath);
            }
        }

        List<LogAnalysisStrategy> strategies = List.of(
                new CountByLogLevelStrategy(),
                new UniqueErrorMessagesStrategy(),
                new MostFrequentErrorMessageStrategy()
        );

        for (LogAnalysisStrategy strategy : strategies) {
            strategy.analyze(allEntries);
            System.out.println(strategy.getReport());
        }
    }
}
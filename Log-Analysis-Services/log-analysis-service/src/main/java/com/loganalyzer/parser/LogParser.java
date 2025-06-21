package com.loganalyzer.parser;

import com.loganalyzer.model.LogEntry;

import java.util.Optional;

public interface LogParser {
    Optional<LogEntry> parseLine(String line);
}
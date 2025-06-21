package com.loganalyzer;

import com.loganalyzer.model.LogEntry;
import com.loganalyzer.parser.DefaultLogParser;
import com.loganalyzer.parser.LogParser;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class LogParserTest {

    LogParser parser = new DefaultLogParser();

    @Test
    public void testValidLineParsing() {
        String line = "[2025-04-29T15:05:45.123Z] [INFO] [main] [com.example.App] - Application starting...";
        Optional<LogEntry> entry = parser.parseLine(line);
        assertTrue(entry.isPresent());
        assertEquals("INFO", entry.get().getLogLevel());
        assertEquals("main", entry.get().getThreadId());
    }

    @Test
    public void testInvalidLineParsing() {
        String line = "Invalid log line";
        Optional<LogEntry> entry = parser.parseLine(line);
        assertTrue(entry.isEmpty());
    }
}
package com.loganalyzer.parser;

import com.loganalyzer.model.LogEntry;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DefaultLogParser implements LogParser {
    private static final Pattern pattern = Pattern.compile("^\\[(.*?)\\] \\[(.*?)\\] \\[(.*?)\\] \\[(.*?)\\] - (.*)$");
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

    @Override
    public Optional<LogEntry> parseLine(String line) {
        Matcher matcher = pattern.matcher(line);
        if (!matcher.matches()) {
            return Optional.empty();
        }
        try {
            LocalDateTime timestamp = LocalDateTime.parse(matcher.group(1).replace("Z", ""));
            String logLevel = matcher.group(2);
            String threadId = matcher.group(3);
            String loggerName = matcher.group(4);
            String message = matcher.group(5);
            return Optional.of(new LogEntry(timestamp, logLevel, threadId, loggerName, message));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
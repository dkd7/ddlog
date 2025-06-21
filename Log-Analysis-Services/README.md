# Log Analysis Service

## Overview
This standalone Java application analyzes structured log files and generates a human-readable summary report. It supports:
- Counting log entries by log level (INFO, WARN, ERROR, DEBUG)
- Listing unique ERROR messages
- Finding the most frequent ERROR message

## Input Format
Each line of the log should follow:
```
[TIMESTAMP] [LOG_LEVEL] [THREAD_ID] [LOGGER_NAME] - [MESSAGE]
```

## Sample
```
[2025-04-29T15:05:45.123Z] [INFO] [main] [com.example.App] - Application starting...
```

## Build and Run

### Prerequisites
- Java 17+
- Maven

### Build
```bash
mvn clean install
```

### Run
```bash
java -cp target/log-analysis-service-1.0.jar com.loganalyzer.Main sample.log
```

## Run Tests
```bash
mvn test
```

##️ Architecture Overview

### Key Classes

| Class | Responsibility |
|-------|----------------|
| **LogEntry** | Represents a single parsed log entry (`timestamp`, `logLevel`, `threadId`, `loggerName`, `message`) |
| **DefaultLogParser** | Parses log lines using regex and datetime parsing. Returns `LogEntry` objects or skips malformed lines |
| **CountByLogLevelStrategy** | Counts log entries grouped by their log levels (INFO, DEBUG, WARN, ERROR) |
| **UniqueErrorMessagesStrategy** | Collects all distinct ERROR messages from the logs |
| **MostFrequentErrorMessageStrategy** | Identifies the most frequent ERROR message and its occurrence count |

---

## Design Patterns

### Strategy Pattern
Each type of analysis (counting log levels, collecting unique errors, etc.) is encapsulated in its own class. This makes it easy to swap in new strategies without changing the core logic.

### Factory-Like Abstraction
The `LogParser` interface allows for switching between different parsing implementations. For example, `DefaultLogParser` can be replaced by a JSON-based parser in the future.

### Builder Pattern (Implied)
Ensures only well-structured and validated data is used when constructing a `LogEntry`.

---

## Core Data Structures

| Structure | Purpose |
|-----------|---------|
| `HashMap` | Fast O(1) lookup for counting log levels or message frequencies |
| `HashSet` | Ensures uniqueness for error message collection |
| `List` | Stores parsed `LogEntry` objects in order for further analysis |

---

## Assumptions

- Log lines follow a predefined, consistent format.
- Malformed lines are skipped without crashing the application.
- Log files are moderate in size and can be processed in memory.
- Logs are in a human-readable text format.

---

## How to Extend

You can easily add new analysis strategies by:

1. Creating a new class that implements the `LogAnalysisStrategy` interface.
2. Implementing the `analyze(List<LogEntry> entries)` method.
3. Plugging it into the main application logic.

## Error Handling
- Skips malformed lines
- Handles file-not-found errors

## Limitations & Future Improvements

### Known Limitations

- **Memory Usage for Large Files**: Currently, the entire log file is loaded into memory, which can cause issues with very large files.
- **Single-Threaded Execution**: All parsing and analysis are done sequentially, limiting performance on multi-core systems.
- **Fixed Log Format**: Only one strict log format is supported. Any variation will result in skipped lines.
- **Silent Error Handling**: Malformed lines are skipped without any feedback or logging.
- **Static Strategy Integration**: Adding a new analysis strategy requires code changes and recompilation.

### Planned Enhancements

- **Stream-Based File Processing**: Use Java streams or `BufferedReader` to process logs line-by-line and reduce memory footprint.
- **Parallel Processing**: Introduce multi-threaded processing using `ForkJoinPool` or Java parallel streams.
- **Support for Multiple Log Formats**: Extend the parser to handle various log structures (JSON, XML, etc.).
- **Dynamic Analysis Pipeline**: Allow users to choose which analysis strategies to apply at runtime.
- **Custom Report Generation**: Output results in multiple formats (JSON, HTML, CSV) and add visualization capabilities.
- **Better Error Feedback**: Provide detailed logs of skipped or malformed lines for easier troubleshooting.

By addressing these limitations, the tool can grow into a highly flexible and enterprise-ready log analysis solution.

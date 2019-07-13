package Logging;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

public class FilterHandler implements Filter {
    private final String name;


    FilterHandler(String name) {
        this.name = name;
    }

    @Override
    public boolean isLoggable(LogRecord record) {
        return record.getSourceClassName().equals(name);
    }
}

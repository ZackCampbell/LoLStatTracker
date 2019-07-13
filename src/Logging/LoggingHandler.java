package Logging;

import java.io.*;
import java.util.logging.*;

public class LoggingHandler {

    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public LoggingHandler() {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS %4$s %2$s %5$s%6$s%n");
        LOGGER.setUseParentHandlers(false);
    }

    public Logger getLogger() {
        return LOGGER;
    }

    public void addHandler(String className) {
        String fileName = className.split("\\.")[0];
        String path = "././logs/" + fileName + ".log";
        try {
            FileHandler fileHandler = new FileHandler(path, true);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setFilter(new FilterHandler(className));
            LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            System.out.println("Failed to initialize handler for " + className);
            LOGGER.setUseParentHandlers(true);
        }
    }

    public void removeHandler(Handler handler) {
        LOGGER.removeHandler(handler);
    }

    public Handler[] getHandlers() {
        return LOGGER.getHandlers();
    }

    public void cleanup() {
        for (Handler handler : getHandlers()) {
            LOGGER.removeHandler(handler);
        }
    }

}

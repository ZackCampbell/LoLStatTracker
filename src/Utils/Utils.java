package Utils;

import Logging.LoggingHandler;

import java.io.File;
import java.util.logging.Logger;

public class Utils {

    public static Logger initializeLogger(String className) {
        LoggingHandler logHandler = new LoggingHandler();
        logHandler.addHandler(className);
        Runtime.getRuntime().addShutdownHook(new Thread(logHandler::cleanup));
        return logHandler.getLogger();
    }

    public static String getRelativePath() {
        return new File("").getAbsolutePath();
    }

}

package share.Logging;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LogHandler {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void log(Level level, String message) {
        LOGGER.log(level, message);
    }
}

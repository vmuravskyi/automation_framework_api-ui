package com.automation.factory;

import com.automation.constants.DriverType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DriverManagerFactory {

    private final static Logger LOGGER = LogManager.getLogger();

    public static DriverManager getDriverManager(DriverType type) {
        switch (type) {
            case CHROME: {
                LOGGER.debug("Getting ChromeDriverManager");
                return new ChromeDriverManager();
            }
            case FIREFOX: {
                LOGGER.debug("FirefoxDriverManager");
                return new FirefoxDriverManager();
            }
            default: {
                LOGGER.debug("DriverManager has not been created due an error");
                throw new IllegalArgumentException(String.format("Failed to create DriverManager [%s] instance", type));
            }
        }
    }

}

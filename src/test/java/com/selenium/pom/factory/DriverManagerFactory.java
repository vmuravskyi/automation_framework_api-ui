package com.selenium.pom.factory;

import com.selenium.pom.constants.DriverType;

public class DriverManagerFactory {

    public static DriverManager getManager(DriverType driverType) {
        switch (driverType) {
            case CHROME: {
                return new ChromeDriverManager();
            }
            case FIREFOX: {
                return new FirefoxDriverManager();
            }
            default:
                throw new IllegalStateException("Unexpected value: " + driverType);
        }
    }
}

package com.automation.pom.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {

    private static final Logger LOGGER = LogManager.getLogger();


    public WebDriver initializeDriver(String browser) {
        WebDriver driver;

        switch (browser) {
            case "chrome": {
                LOGGER.debug("Setting up {} browser", browser);
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            }
            case "firefox": {
                LOGGER.debug("Setting up {} browser", browser);
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            }
            default: {
                throw new IllegalStateException("Invalid browser name " + browser);
            }
        }
        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Currently using explicit waits
        return driver;
    }

}

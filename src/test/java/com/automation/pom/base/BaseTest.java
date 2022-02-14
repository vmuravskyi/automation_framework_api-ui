package com.automation.pom.base;

import com.automation.pom.factory.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    public static Logger LOGGER = LogManager.getLogger();

    protected WebDriver driver;

    @BeforeTest
    public void startDriver() {
        LOGGER.debug("Initializing driver");
        driver = new DriverManager().initializeDriver();
        LOGGER.debug("Maximizing window size");
    }

    @AfterTest
    public void quitDriver() {
        LOGGER.debug("Quitting driver");
        driver.quit();
    }

    @BeforeMethod
    public void navigateToBasePage() {
        LOGGER.debug("Navigating to base page [https://askomdch.com]");
        driver.navigate().to("https://askomdch.com");
    }

}

package com.automation.pom.base;

import com.automation.pom.factory.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    public static Logger LOGGER = LogManager.getLogger();

    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    protected WebDriver getDriver() {
        return this.driver.get();
    }

    public void setDriver(WebDriver driver) {
        this.driver.set(driver);
    }

    @Parameters("browser")
    @BeforeTest
    public void startDriver(@Optional("chrome") String browser) {
        browser = System.getProperty("browser", browser);
        LOGGER.debug("INITIALIZING DRIVER: " + driver.get() + " | CURRENT THREAD: " + Thread.currentThread().getId());
        setDriver(new DriverManager().initializeDriver(browser));
    }

    @AfterTest
    public void quitDriver() {
        LOGGER.debug("QUITTING DRIVER: " + driver.get() + " | CURRENT THREAD: " + Thread.currentThread().getId());
        if (driver.get() != null) {
            getDriver().quit();
        }
    }

    @BeforeMethod
    public void navigateToBasePage() {
        LOGGER.debug("Navigating to base page [https://askomdch.com]");
        getDriver().navigate().to("https://askomdch.com");
    }
}

package com.selenium.pom.base;

import com.selenium.pom.factory.DriverManager;
import com.selenium.pom.utils.CookieUtils;
import io.restassured.http.Cookies;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.List;

public class BaseTest {

    private final static Logger LOGGER = LogManager.getLogger();
    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();


    protected WebDriver getDriver() {
        return this.driver.get();
    }

    private void setDriver(WebDriver driver) {
        this.driver.set(driver);
    }

    @Parameters("browser")
    @BeforeMethod
    public synchronized void startDriver(@Optional String browser) {
        browser = System.getProperty("browser", browser);
        if (browser == null) {
            browser = "CHROME";
        }
        LOGGER.info("Setting the driver for {}", browser);
        setDriver(new DriverManager().initializeDriver(browser));
        LOGGER.info("Current thread: " + Thread.currentThread().getId() + ", " + "Driver: " + getDriver());
    }

    @Parameters("browser")
    @AfterMethod
    public synchronized void quitDriver(@Optional String browser, ITestResult result) {
        // needed for normal quitting of the driver
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("Quitting driver");
        getDriver().quit();
    }

    public void injectCookiesToBrowser(Cookies cookies) {
        List<Cookie> seleniumCookies = new CookieUtils().covertRestAssuredCookiesToSeleniumCookies(cookies);
        LOGGER.info("Injecting cookies into browser");
        for (Cookie seleniumCookie : seleniumCookies) {
            getDriver().manage().addCookie(seleniumCookie);
        }
    }

}

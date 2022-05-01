package com.selenium.pom.base;

import com.codeborne.selenide.Selenide;
import com.selenium.pom.constants.DriverType;
import com.selenium.pom.factory.DriverManagerFactory;
import com.selenium.pom.utils.CookieUtils;
import com.selenium.pom.utils.WaitFactory;
import io.restassured.http.Cookies;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Cookie;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    private final static Logger LOGGER = LogManager.getLogger();

    @Parameters("browser")
    @BeforeMethod
    public synchronized void startDriver(@Optional String browser) {
        browser = System.getProperty("browser", browser);

        // needed because browser will be 'null' if tests are run NOT from terminal or testng.xml
        if (browser == null) {
            browser = "CHROME";
        }
        LOGGER.info("Setting the driver for [{}]", browser);

        // set up web-driver
        DriverManagerFactory
            .getDriverManager(DriverType.valueOf(browser))
            .createDriver()
        ;
        LOGGER.info("Current thread: " + Thread.currentThread().getId() + ", " + "Driver: " +
            Selenide.webdriver().driver().getWebDriver());
    }

    @Parameters("browser")
    @AfterMethod
    public synchronized void quitDriver(@Optional String browser, ITestResult result) {
        // needed for normal quitting of the driver
        WaitFactory.sleep(300);
        // screenshot on failed test saved to build/reports/tests
        LOGGER.info("Quitting driver");
        Selenide.closeWebDriver();
    }

    public void injectCookiesToBrowser(Cookies cookies) {
        List<Cookie> seleniumCookies = new CookieUtils().covertRestAssuredCookiesToSeleniumCookies(cookies);
        LOGGER.info("Injecting cookies into browser");
        seleniumCookies.forEach(
            cookie -> Selenide.webdriver().driver().getWebDriver().manage().addCookie(cookie)
        );
    }


}

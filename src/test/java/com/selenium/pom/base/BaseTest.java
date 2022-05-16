package com.selenium.pom.base;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.selenium.pom.constants.DriverType;
import com.selenium.pom.factory.DriverManagerFactory;
import com.selenium.pom.utils.CookieUtils;
import io.restassured.http.Cookies;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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

        // needed because browser will be 'null' if tests are run NOT from terminal or Web-UI.xml
        if (browser == null) {
            browser = "CHROME";
        }
        LOGGER.info("Setting the driver for [{}]", browser);

        // set up web-driver
        DriverManagerFactory
            .getDriverManager(DriverType.valueOf(browser))
            .createDriver();

        LOGGER.info("Current thread: {}. Driver instance: {}", Thread.currentThread().getId(),
            WebDriverRunner.getWebDriver());
    }

    private void takeScreenShot(File destinationFile) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) WebDriverRunner.getWebDriver();
        File file = takesScreenshot.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, destinationFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Parameters("browser")
    @AfterMethod
    public synchronized void quitDriver(@Optional String browser, ITestResult result) {
        // screenshot on failed test saved to build/reports/tests in Selenide

        // create a file for screenshot
        if (result.getStatus() == ITestResult.FAILURE) {
            File destinationFile = new File(
                "screenshots" + File.separator + browser + File.separator +
                    result.getTestClass().getRealClass().getSimpleName() + "_" +
                    result.getMethod().getMethodName() + ".png");
            takeScreenShot(destinationFile);
            LOGGER.info("Taking a screenshot");
        }

        LOGGER.info("Quitting driver [{}]", browser);
        Selenide.closeWebDriver();
    }

    public void injectCookiesToBrowser(Cookies cookies) {
        List<Cookie> seleniumCookies = new CookieUtils().covertRestAssuredCookiesToSeleniumCookies(cookies);
        LOGGER.info("Injecting cookies into browser");
        seleniumCookies.forEach(
            cookie -> WebDriverRunner.getWebDriver().manage().addCookie(cookie)
        );
    }

}

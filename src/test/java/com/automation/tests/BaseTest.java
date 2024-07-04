package com.automation.tests;

import com.automation.constants.DriverType;
import com.automation.factory.DriverManagerFactory;
import com.automation.utils.CookieUtils;
import com.automation.utils.WaitFactory;
import io.restassured.http.Cookies;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

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
        LOGGER.info("Setting the driver for [{}]", browser);

//        set up webdriver with simple DriverManager
//        setDriver(new DriverManagerOriginal().initializeDriver(browser));

        // set up webdriver with DriverManager through interface
        setDriver(
            DriverManagerFactory
                .getDriverManager(DriverType.valueOf(browser))
                .createDriver()
        );

        // set up webdriver with abstract DriverManager through abstract class
//        setDriver(
//            DriverManagerFactoryAbstract
//                .getManager(DriverType.valueOf(browser))
//                .getDriver()
//        );
        LOGGER.info("Current thread: " + Thread.currentThread().getId() + ", " + "Driver: " + getDriver());
    }

    @Parameters("browser")
    @AfterMethod
    public synchronized void quitDriver(@Optional String browser, ITestResult result) {
        // needed for normal quitting of the driver
        WaitFactory.sleep(300);

        // needed because browser will be 'null' if tests are run NOT from terminal or testng.xml
        if (browser == null) {
            browser = "CHROME";
        }

        // create a file for screenshot
        if (result.getStatus() == ITestResult.FAILURE) {
            File destinationFile = new File(
                "screenshots" + File.separator + browser + File.separator +
                    result.getTestClass().getRealClass().getSimpleName() + "_" +
                    result.getMethod().getMethodName() + ".png");
            takeScreenShot(destinationFile);
//            takeScreenShotUsingAShot(destinationFile);
            LOGGER.info("Taking a screenshot");
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

    private void takeScreenShot(File destinationFile) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) getDriver();
        File file = takesScreenshot.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, destinationFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void takeScreenShotUsingAShot(File destinationFile) {
        Screenshot screenshot = new AShot()
            .shootingStrategy(ShootingStrategies.viewportPasting(100))
            .takeScreenshot(getDriver());

        // convert screenshot to bufferedImage and write to destination file
        try {
            ImageIO.write(screenshot.getImage(), ".PNG", destinationFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

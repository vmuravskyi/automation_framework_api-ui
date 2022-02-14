package com.automation.pom.factory;

import static com.automation.pom.constants.DriverType.FIREFOX;

import com.automation.pom.constants.DriverType;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {

    private static final Logger LOGGER = LogManager.getLogger();

    public WebDriver initializeDriver() {
        WebDriver driver = setUpDriverType(FIREFOX);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    private WebDriver setUpDriverType(DriverType driverType) {
        WebDriver driver;

        switch (driverType) {
            case CHROME: {
                driver = new ChromeDriver();
                WebDriverManager.chromedriver().setup();
            }
            case FIREFOX: {
                driver = new FirefoxDriver();
                WebDriverManager.firefoxdriver().setup();
            }
            default:
                driver = new ChromeDriver();
                WebDriverManager.chromedriver().setup();
                LOGGER.debug("Browser type is set to default [CHROME]");
        }
    }

}

package com.automation.pom.base;

import com.automation.pom.factory.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    protected WebDriver driver;

    @BeforeTest
    public void startDriver() {
        driver = new DriverManager().initializeDriver(driver);
        driver.manage().window().maximize();
    }

    @AfterTest
    public void quitDriver() {
        driver.quit();
    }

    @BeforeMethod
    public void navigateToBasePage() {
        driver.navigate().to("https://askomdch.com");
    }

}

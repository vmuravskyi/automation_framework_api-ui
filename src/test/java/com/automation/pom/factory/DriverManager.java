package com.automation.pom.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {

    public WebDriver initializeDriver(WebDriver driver) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        return driver;
    }

}

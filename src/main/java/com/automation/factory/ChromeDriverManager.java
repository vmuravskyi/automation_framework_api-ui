package com.automation.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverManager implements DriverManager {

    private WebDriver driver;

    @Override
    public WebDriver createDriver() {
        WebDriverManager.chromedriver().cachePath("drivers").setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

}

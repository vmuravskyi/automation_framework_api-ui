package com.automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FirstTest {

    private WebDriver driver;

    @Test
    public void firstTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.quit();
    }

}

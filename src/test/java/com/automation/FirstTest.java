package com.automation;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FirstTest {

    private WebDriver driver;

    @Test
    public void dummyTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://askomdch.com");
        driver.quit();
        Assertions.assertThat("asd")
            .isEqualTo("asd");
    }

}

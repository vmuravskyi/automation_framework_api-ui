package com.automation.pom.base;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected void navigate(String endPoint) {
        driver.navigate().to("https://askomdch.com" + endPoint);
    }
}

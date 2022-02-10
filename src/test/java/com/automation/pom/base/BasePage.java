package com.automation.pom.base;

import java.io.File;
import org.openqa.selenium.WebDriver;

public class BasePage {

    protected String uri;
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToThisPage() {
        driver.navigate().to(this.getThisUrl());
    }

    protected String getThisUrl() {
        return "https://askomdch.com" + File.separator + uri;
    }
}

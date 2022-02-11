package com.automation.pom.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {

    public static Logger LOGGER = LogManager.getLogger();

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected void navigate(String endPoint) {
        LOGGER.debug("Navigate to https://askomdch.com" + endPoint);
        driver.navigate().to("https://askomdch.com" + endPoint);
    }
}

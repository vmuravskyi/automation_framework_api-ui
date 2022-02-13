package com.automation.pom.pages;

import com.automation.pom.base.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private static Logger LOGGER = LogManager.getLogger();

    private final By storeMenuLink = By.cssSelector("#menu-item-1227 > a");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage load() {
        super.navigate("/");
        return this;
    }

    public StorePage navigateToStoreUsingMenu() {
        waitUntilPresenceOfElementLocatedBy(storeMenuLink);
        waitUntilElementToBeClickable(storeMenuLink);
        LOGGER.debug("Clicking storeMenuLink [{}]", storeMenuLink);
        driver.findElement(storeMenuLink).click();
        return new StorePage(driver);
    }

}

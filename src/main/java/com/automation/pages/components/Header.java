package com.automation.pages.components;

import com.automation.pages.BasePage;
import com.automation.pages.StorePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Header extends BasePage {

    private final By storeMenuLink = By.cssSelector("#menu-item-1227 > a");

    public Header(WebDriver driver) {
        super(driver);
    }

    public StorePage navigateToStoreUsingMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(storeMenuLink)).click();
        return new StorePage(driver);
    }

}

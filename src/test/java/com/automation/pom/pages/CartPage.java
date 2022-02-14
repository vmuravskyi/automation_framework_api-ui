package com.automation.pom.pages;

import com.automation.pom.base.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    private static final Logger LOGGER = LogManager.getLogger();

    private By productName = By.xpath("//td[@class='product-name']");
    private By checkoutButton = By.xpath("//a[@class='checkout-button button alt wc-forward']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName() {
        LOGGER.debug("Getting product name");
        waitUntilElementToBeVisible(productName);
        return driver.findElement(productName).getText();
    }

    public CheckOutPage checkout() {
        LOGGER.debug("Checking out");
        waitUntilElementToBeClickable(checkoutButton);
        driver.findElement(checkoutButton).click();
        return new CheckOutPage(driver);
    }

}

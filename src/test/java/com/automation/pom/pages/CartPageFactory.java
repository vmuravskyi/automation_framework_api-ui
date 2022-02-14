package com.automation.pom.pages;

import com.automation.pom.base.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * This is an example of PageFactory class
 */
public class CartPageFactory extends BasePage {

    private static final Logger LOGGER = LogManager.getLogger();

    @FindBy(how = How.XPATH, using = "//td[@class='product-name']")
    @CacheLookup
    private WebElement productName;

    @FindBy(how = How.XPATH, using = "//a[@class='checkout-button button alt wc-forward']")
    @CacheLookup
    private WebElement checkoutButton;

    public CartPageFactory(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getProductName() {
        LOGGER.debug("Getting product name");
        waitUntilElementToBeVisible(productName);
        return productName.getText();
    }

    public CheckOutPage checkout() {
        LOGGER.debug("Checking out");
        waitUntilElementToBeClickable(checkoutButton);
        checkoutButton.click();
        return new CheckOutPage(driver);
    }

}

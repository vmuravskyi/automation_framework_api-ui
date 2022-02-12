package com.automation.pom.pages;

import com.automation.pom.base.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StorePage extends BasePage {

    private static Logger LOGGER = LogManager.getLogger();

    private final By searchField = By.id("woocommerce-product-search-field-0");
    private final By searchButton = By.xpath("//button[@value='Search']");
    private final By title = By.xpath("//h1[@class='woocommerce-products-header__title page-title']");
    private final By resultItem = By.xpath("//ul[@class='products columns-4']/li");
    private final By viewCartLink = By.xpath("//div[@class='ast-cart-menu-wrap']");

    public StorePage(WebDriver driver) {
        super(driver);
    }

    private StorePage enterTextInSearchField(String text) {
        LOGGER.debug("Entering text to searchField [{}]", searchField);
        driver.findElement(searchField).sendKeys(text);
        return this;
    }

    private StorePage clickSearchButton() {
        LOGGER.debug("Clicking searchButton [{}]", searchButton);
        driver.findElement(searchButton).click();
        return this;
    }

    private By getAddToCardButtonElement(String productName) {
        LOGGER.debug("Adding {} to cart", productName);
        return By.xpath("//a[@aria-label='Add “" + productName + "” to your cart']");
    }

    public StorePage clickAddToCard(String productName) {
        By addToCardButton = getAddToCardButtonElement(productName);
        LOGGER.debug("Clicking addToCardButton [{}]", addToCardButton);
        driver.findElement(addToCardButton).click();
        return this;
    }

    public CartPage clickViewCard() {
        LOGGER.debug("Clicking viewCartLink [{}]", viewCartLink);
        driver.findElement(viewCartLink).click();
        return new CartPage(driver);
    }

    public String getTitle() {
        LOGGER.debug("Getting title [{}]", title);
        return driver.findElement(title).getText();
    }

    public StorePage search(String text) {
        LOGGER.debug("Searching {} in", text);
        enterTextInSearchField(text).clickSearchButton();
        return this;
    }


}

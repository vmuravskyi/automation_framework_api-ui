package com.automation.pom.pages;

import com.automation.pom.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StorePage extends BasePage {

    private final By searchField = By.id("woocommerce-product-search-field-0");
    private final By searchButton = By.xpath("//button[@value='Search']");
    private final By title = By.xpath("//h1[@class='woocommerce-products-header__title page-title']");
    private final By resultItem = By.xpath("//ul[@class='products columns-4']/li");

    public StorePage(WebDriver driver) {
        super(driver);
    }

    private StorePage enterTextInSearchField(String text) {
        driver.findElement(searchField).sendKeys(text);
        return this;
    }

    private StorePage clickSearchButton() {
        driver.findElement(searchButton).click();
        return this;
    }

    private By getAddToCardButtonElement(String productName) {
        return By.xpath("//a[@aria-label='Add “" + productName + "” to your cart']");
    }

    public StorePage clickAddToCard(String productName) {
        By addToCardButton = getAddToCardButtonElement(productName);
        driver.findElement(addToCardButton).click();
        return this;
    }

    public String getTitle() {
        return driver.findElement(searchField).getText();
    }

    public StorePage search(String text) {
        enterTextInSearchField(text).clickSearchButton();
        return this;
    }


}

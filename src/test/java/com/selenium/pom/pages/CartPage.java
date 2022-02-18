package com.selenium.pom.pages;

import com.selenium.pom.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage {
    private final String cartPageEndpoint = "/cart";
    private final By productName = By.cssSelector("td[class='product-name'] a");
    private final By checkoutBtn = By.cssSelector(".checkout-button");
    private final By cartHeading = By.cssSelector(".has-text-align-center");

//    @FindBy(css = "td[class='product-name'] a") private WebElement productName;
//    @FindBy(how = How.CSS, using = ".checkout-button") @CacheLookup private WebElement checkoutBtn;

    public CartPage(WebDriver driver) {
        super(driver);
//        PageFactory.initElements(driver, this); // in case of using PageFactory
    }

    public CartPage load() {
        load(cartPageEndpoint);
        return this;
    }

    public String getProductName(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productName)).getText();
    }

    public CheckoutPage checkout(){
        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn)).click();
        return new CheckoutPage(driver);
    }
}

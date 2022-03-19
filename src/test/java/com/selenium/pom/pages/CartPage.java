package com.selenium.pom.pages;

import com.selenium.pom.base.BasePage;
import com.selenium.pom.constants.WebEndpoint;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage {

    private final By productName = By.cssSelector("td[class='product-name'] a");
    private final By checkoutBtn = By.cssSelector(".checkout-button");
    private final By cartHeading = By.cssSelector(".has-text-align-center");
    private final By couponField = By.xpath("//input[@name='coupon_code']");
    private final By applyCouponBtn = By.xpath("//button[@name='apply_coupon']");
    private final By cartTotalsCoupon = By.xpath("//td[@data-title='Coupon: freeship']");

//    @FindBy(css = "td[class='product-name'] a") private WebElement productName;
//    @FindBy(how = How.CSS, using = ".checkout-button") @CacheLookup private WebElement checkoutBtn;

    public CartPage(WebDriver driver) {
        super(driver);
//        PageFactory.initElements(driver, this); // in case of using PageFactory
    }

    public CartPage load() {
        load(WebEndpoint.CART.getValue());
        return this;
    }

    @Step
    public String getProductName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productName)).getText();
    }

    public CheckoutPage checkout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn)).click();
        return new CheckoutPage(driver);
    }

    public CartPage addCoupon(String coupon) {
        wait.until(ExpectedConditions.elementToBeClickable(applyCouponBtn));
        driver.findElement(couponField).sendKeys(coupon);
        driver.findElement(applyCouponBtn).click();
        return this;
    }

    public String getCartTotalCoupon() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartTotalsCoupon));
        return driver.findElement(cartTotalsCoupon).getText();
    }

}

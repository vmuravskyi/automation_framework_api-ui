package com.selenium.pom.pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.selenium.pom.base.BasePage;
import com.selenium.pom.constants.WebEndpoint;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    private final SelenideElement productName = $("td[class='product-name'] a");
    private final SelenideElement checkoutBtn = $(".checkout-button");
    private final SelenideElement cartHeading = $(".has-text-align-center");
    private final SelenideElement couponField = $x("//input[@name='coupon_code']");
    private final SelenideElement applyCouponBtn = $x("//button[@name='apply_coupon']");
    private final SelenideElement cartTotalsCoupon = $x("//td[@data-title='Coupon: freeship']");

//    @FindBy(css = "td[class='product-name'] a") private WebElement productName;
//    @FindBy(how = How.CSS, using = ".checkout-button") @CacheLookup private WebElement checkoutBtn;

    public CartPage() {
//        PageFactory.initElements(driver, this); // in case of using PageFactory
    }

    public CartPage load() {
        load(WebEndpoint.CART.getValue());
        return this;
    }

    @Step
    public String getProductName() {
        return productName.shouldBe(Condition.visible).getText();
    }

    public CheckoutPage checkout() {
        checkoutBtn.shouldBe(Condition.enabled, Condition.visible).click();
        return new CheckoutPage();
    }

    public CartPage addCoupon(String coupon) {
        applyCouponBtn.shouldBe(Condition.enabled, Condition.visible);
        couponField.sendKeys(coupon);
        applyCouponBtn.click();
        return this;
    }

    public String getCartTotalCoupon() {
        return cartTotalsCoupon.shouldBe(Condition.visible).getText();
    }

}

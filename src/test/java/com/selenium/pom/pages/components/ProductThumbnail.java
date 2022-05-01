package com.selenium.pom.pages.components;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.selenium.pom.base.BasePage;
import com.selenium.pom.pages.CartPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class ProductThumbnail extends BasePage {

    private final SelenideElement viewCartLink = $("a[title='View cart']");

    public ProductThumbnail() {
    }

    private SelenideElement getAddToCartBtnElement(String productName) {
        return $("a[aria-label='Add “" + productName + "” to your cart']");
    }

    @Step
    public ProductThumbnail clickAddToCartBtn(String productName) {
        SelenideElement addToCartBtn = getAddToCartBtnElement(productName);
        addToCartBtn.shouldBe(Condition.visible, Condition.enabled).click();
        return this;
    }

    @Step
    public CartPage clickViewCart() {
        viewCartLink.shouldBe(Condition.visible, Condition.enabled).click();
        return new CartPage();
    }

}

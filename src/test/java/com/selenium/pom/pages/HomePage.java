package com.selenium.pom.pages;

import com.selenium.pom.base.BasePage;
import com.selenium.pom.pages.components.Header;
import com.selenium.pom.pages.components.ProductThumbnail;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private final Header header;
    private final ProductThumbnail productThumbnail;

    public HomePage(WebDriver driver) {
        super(driver);
        header = new Header(driver);
        productThumbnail = new ProductThumbnail(driver);
    }

    public Header getHeader() {
        return header;
    }

    @Step
    public ProductThumbnail getProductThumbnail() {
        return productThumbnail;
    }

    public HomePage load() {
        load("/");
        return this;
    }
}

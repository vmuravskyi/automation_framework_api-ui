package com.selenium.pom.pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.webdriver;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverConditions;
import com.selenium.pom.base.BasePage;
import com.selenium.pom.constants.WebEndpoint;
import com.selenium.pom.pages.components.ProductThumbnail;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StorePage extends BasePage {

    private final SelenideElement searchFld = $(By.id("woocommerce-product-search-field-0"));
    private final SelenideElement searchBtn = $(By.cssSelector("button[value='Search']"));
    private final SelenideElement title = $(By.cssSelector(".woocommerce-products-header__title.page-title"));
    private final ProductThumbnail productThumbnail;

    public StorePage() {
        productThumbnail = new ProductThumbnail();
    }

    public ProductThumbnail getProductThumbnail() {
        return productThumbnail;
    }

    public StorePage load() {
        load(WebEndpoint.STORE.getValue());
        return this;
    }

    private StorePage enterTextInSearchFld(String txt) {
        searchFld.shouldBe(Condition.visible).sendKeys(txt);
        return this;
    }

    public StorePage search(String txt) {
        enterTextInSearchFld(txt).clickSearchBtn();
        return this;
    }

    private StorePage clickSearchBtn() {
        searchBtn.shouldBe(Condition.visible, Condition.enabled).click();
        return this;
    }

    public String getTitle() {
        return title.shouldBe(Condition.visible).getText();
    }

    /**
     * Use this method if you need to get a title of the page after searching something in the search field
     *
     * @return The title of the Store page
     */
    public String getTitleAfterSearch() {
        webdriver().shouldHave(WebDriverConditions.urlContaining("&post_type=product"));
        return title.shouldBe(Condition.visible).getText();
    }

}

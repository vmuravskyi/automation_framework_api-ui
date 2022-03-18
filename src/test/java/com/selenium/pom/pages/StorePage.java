package com.selenium.pom.pages;

import com.selenium.pom.base.BasePage;
import com.selenium.pom.constants.WebEndpoint;
import com.selenium.pom.pages.components.ProductThumbnail;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class StorePage extends BasePage {

    private final By searchFld = By.id("woocommerce-product-search-field-0");
    private final By searchBtn = By.cssSelector("button[value='Search']");
    private final By title = By.cssSelector(".woocommerce-products-header__title.page-title");
    private final ProductThumbnail productThumbnail;

    public StorePage(WebDriver driver) {
        super(driver);
        productThumbnail = new ProductThumbnail(driver);
    }

    public ProductThumbnail getProductThumbnail() {
        return productThumbnail;
    }

    public StorePage load() {
        load(WebEndpoint.STORE.getValue());
        return this;
    }

    private StorePage enterTextInSearchFld(String txt) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchFld)).sendKeys(txt);
        return this;
    }

    public StorePage search(String txt) {
        enterTextInSearchFld(txt).clickSearchBtn();
        return this;
    }

    private StorePage clickSearchBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();
        return this;
    }

    public String getTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(title)).getText();
    }

    /**
     * Use this method if you need to get a title of the page after searching something in the search field
     *
     * @return The title of the Store page
     */
    public String getTitleAfterSearch() {
        wait.until(ExpectedConditions.urlContains("&post_type=product"));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(title)).getText();
    }

}

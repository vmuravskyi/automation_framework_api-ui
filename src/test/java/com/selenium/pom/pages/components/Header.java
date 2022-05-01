package com.selenium.pom.pages.components;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.selenium.pom.base.BasePage;
import com.selenium.pom.pages.StorePage;
import org.openqa.selenium.WebDriver;

public class Header extends BasePage {

    private final SelenideElement storeMenuLink = $("#menu-item-1227 > a");

    public Header() {

    }

    public StorePage navigateToStoreUsingMenu() {
        storeMenuLink.shouldBe(Condition.visible, Condition.enabled).click();
        return new StorePage();
    }

}

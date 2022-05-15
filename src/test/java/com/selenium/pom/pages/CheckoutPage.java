package com.selenium.pom.pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.selenium.pom.base.BasePage;
import com.selenium.pom.constants.WebEndpoint;
import com.selenium.pom.objects.BillingAddressDto;
import com.selenium.pom.objects.User;
import org.openqa.selenium.By;

public class CheckoutPage extends BasePage {

    private final SelenideElement firstnameFld = $(By.id("billing_first_name"));
    private final SelenideElement lastNameFld = $(By.id("billing_last_name"));
    private final SelenideElement addressLineOneFld = $(By.id("billing_address_1"));
    private final SelenideElement billingCityFld = $(By.id("billing_city"));
    private final SelenideElement billingPostCodeFld = $(By.id("billing_postcode"));
    private final SelenideElement billingEmailFld = $(By.id("billing_email"));
    private final SelenideElement placeOrderBtn = $(By.id("place_order"));
    private final SelenideElement successNotice = $(By.cssSelector(".woocommerce-notice"));

    private final SelenideElement clickHereToLoginLink = $(By.className("showlogin"));
    private final SelenideElement usernameFld = $(By.id("username"));
    private final SelenideElement passwordFld = $(By.id("password"));
    private final SelenideElement loginBtn = $(By.name("login"));
    private final ElementsCollection overlay = $$(".blockUI.blockOverlay");

    private final SelenideElement countryDropDown = $(By.id("billing_country"));
    private final SelenideElement stateDropDown = $(By.id("billing_state"));

    private final SelenideElement alternateCountryDropDown = $(By.id("select2-billing_country-container"));
    private final SelenideElement alternateStateDropDown = $(By.id("select2-billing_state-container"));

    private final SelenideElement directBankTransferRadioBtn = $(By.id("payment_method_bacs"));

    private final SelenideElement productName = $(By.xpath("//td[@class='product-name']"));

    public CheckoutPage() {
    }

    public CheckoutPage load() {
        load(WebEndpoint.CHECKOUT.getValue());
        return this;
    }

    public CheckoutPage enterFirstName(String firstName) {
        firstnameFld.shouldBe(Condition.visible);
        firstnameFld.clear();
        firstnameFld.sendKeys(firstName);
        return this;
    }

    public CheckoutPage enterLastName(String lastName) {
        lastNameFld.shouldBe(Condition.visible);
        lastNameFld.clear();
        lastNameFld.sendKeys(lastName);
        return this;
    }

    public CheckoutPage selectCountry(String countryName) {

//        does not work for FireFox driver
//        Select select = new Select(driver.findElement(countryDropDown));
//        select.selectByVisibleText(countryName);

        // another approach with js executor
        alternateCountryDropDown.shouldBe(Condition.visible, Condition.enabled).click();
        SelenideElement e = $x("//li[text()='" + countryName + "']");
        Selenide.executeJavaScript("arguments[0].scrollIntoView(true);", e);
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        e.click();
        return this;
    }

    public CheckoutPage enterAddressLineOne(String addressLineOne) {
        addressLineOneFld.shouldBe(Condition.visible);
        addressLineOneFld.clear();
        addressLineOneFld.sendKeys(addressLineOne);
        return this;
    }

    public CheckoutPage enterCity(String city) {
        billingCityFld.shouldBe(Condition.visible);
        billingCityFld.clear();
        billingCityFld.sendKeys(city);
        return this;
    }

    public CheckoutPage selectState(String stateName) {

//        does not work for FireFox driver
//        Select select = new Select(driver.findElement(stateDropDown));
//        select.selectByVisibleText(stateName);

        // another approach with js executor
        alternateStateDropDown.shouldBe(Condition.visible, Condition.enabled).click();
        SelenideElement e = $x("//li[text()='" + stateName + "']");
        Selenide.executeJavaScript("arguments[0].scrollIntoView(true);", e);
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        e.click();
        return this;
    }

    public CheckoutPage enterPostCode(String postCode) {
        billingPostCodeFld.shouldBe(Condition.visible);
        billingPostCodeFld.clear();
        billingPostCodeFld.sendKeys(postCode);
        return this;
    }

    public CheckoutPage enterEmail(String email) {
        billingEmailFld.shouldBe(Condition.visible);
        billingEmailFld.clear();
        billingEmailFld.sendKeys(email);
        return this;
    }

    public CheckoutPage setBillingAddress(BillingAddressDto billingAddress) {
        return enterFirstName(billingAddress.getFirstName())
            .enterLastName(billingAddress.getLastName())
            .selectCountry(billingAddress.getCountry())
            .enterAddressLineOne(billingAddress.getAddressLineOne())
            .enterCity(billingAddress.getCity())
            .selectState(billingAddress.getState())
            .enterPostCode(billingAddress.getPostalCode())
            .enterEmail(billingAddress.getEmail());
    }

    public CheckoutPage placeOrder() {
        waitForOverlaysToDisappear(overlay);
        placeOrderBtn.shouldBe(Condition.visible).click();
        return this;
    }

    public String getNotice() {
        return successNotice.shouldBe(Condition.visible).getText();
    }

    public CheckoutPage clickHereToLoginLink() {
        clickHereToLoginLink.shouldBe(Condition.visible).click();
        return this;
    }

    public CheckoutPage enterUserName(String username) {
        usernameFld.shouldBe(Condition.visible).sendKeys(username);
        return this;
    }

    public CheckoutPage enterPassword(String password) {
        passwordFld.shouldBe(Condition.visible).sendKeys(password);
        return this;
    }

    public CheckoutPage clickLoginBtn() {
        loginBtn.shouldBe(Condition.visible).click();
        return this;
    }

    private CheckoutPage waitForLoginBtnToDisappear() {
        loginBtn.shouldBe(Condition.not(Condition.visible));
        return this;
    }

    public CheckoutPage login(User user) {
        waitUntilPageDownload();
        return clickHereToLoginLink()
            .enterUserName(user.getUsername())
            .enterPassword(user.getPassword())
            .clickLoginBtn()
            .waitForLoginBtnToDisappear();
    }

    public CheckoutPage selectDirectBankTransfer() {
        directBankTransferRadioBtn.shouldBe(Condition.visible, Condition.enabled);
        if (!directBankTransferRadioBtn.isSelected()) {
            directBankTransferRadioBtn.click();
        }
        return this;
    }

    public String getProductName() {
        productName.shouldBe(Condition.appear);
        return productName.shouldBe(Condition.visible).getText();
    }

}

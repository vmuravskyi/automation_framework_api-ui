package com.automation.pages;

import com.automation.constants.WebEndpoint;
import com.automation.objects.BillingAddressDto;
import com.automation.objects.User;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends BasePage {

    private final By firstnameFld = By.id("billing_first_name");
    private final By lastNameFld = By.id("billing_last_name");
    private final By addressLineOneFld = By.id("billing_address_1");
    private final By billingCityFld = By.id("billing_city");
    private final By billingPostCodeFld = By.id("billing_postcode");
    private final By billingEmailFld = By.id("billing_email");
    private final By placeOrderBtn = By.id("place_order");
    private final By successNotice = By.cssSelector(".woocommerce-notice");

    private final By clickHereToLoginLink = By.className("showlogin");
    private final By usernameFld = By.id("username");
    private final By passwordFld = By.id("password");
    private final By loginBtn = By.name("login");
    private final By overlay = By.cssSelector(".blockUI.blockOverlay");

    private final By countryDropDown = By.id("billing_country");
    private final By stateDropDown = By.id("billing_state");

    private final By alternateCountryDropDown = By.id("select2-billing_country-container");
    private final By alternateStateDropDown = By.id("select2-billing_state-container");

    private final By directBankTransferRadioBtn = By.id("payment_method_bacs");

    private final By productName = By.xpath("//td[@class='product-name']");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage load() {
        load(WebEndpoint.CHECKOUT.getValue());
        return this;
    }

    public CheckoutPage enterFirstName(String firstName) {
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(firstnameFld));
        e.clear();
        e.sendKeys(firstName);
        return this;
    }

    public CheckoutPage enterLastName(String lastName) {
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameFld));
        e.clear();
        e.sendKeys(lastName);
        return this;
    }

    public CheckoutPage selectCountry(String countryName) {

//        does not work for FireFox driver
//        Select select = new Select(driver.findElement(countryDropDown));
//        select.selectByVisibleText(countryName);

        // another approach with js executor
        wait.until(ExpectedConditions.elementToBeClickable(alternateCountryDropDown)).click();
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//li[text()='" + countryName + "']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", e);
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        e.click();
        return this;
    }

    public CheckoutPage enterAddressLineOne(String addressLineOne) {
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(addressLineOneFld));
        e.clear();
        e.sendKeys(addressLineOne);
        return this;
    }

    public CheckoutPage enterCity(String city) {
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(billingCityFld));
        e.clear();
        e.sendKeys(city);
        return this;
    }

    public CheckoutPage selectState(String stateName) {

//        does not work for FireFox driver
//        Select select = new Select(driver.findElement(stateDropDown));
//        select.selectByVisibleText(stateName);

        // another approach with js executor
        wait.until(ExpectedConditions.elementToBeClickable(alternateStateDropDown)).click();
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//li[text()='" + stateName + "']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", e);
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        e.click();
        return this;
    }

    public CheckoutPage enterPostCode(String postCode) {
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(billingPostCodeFld));
        e.clear();
        e.sendKeys(postCode);
        return this;
    }

    public CheckoutPage enterEmail(String email) {
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(billingEmailFld));
        e.clear();
        e.sendKeys(email);
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
        driver.findElement(placeOrderBtn).click();
        return this;
    }

    public String getNotice() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successNotice)).getText();
    }

    public CheckoutPage clickHereToLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(clickHereToLoginLink)).click();
        return this;
    }

    public CheckoutPage enterUserName(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameFld)).sendKeys(username);
        return this;
    }

    public CheckoutPage enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordFld)).sendKeys(password);
        return this;
    }

    public CheckoutPage clickLoginBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
        return this;
    }

    private CheckoutPage waitForLoginBtnToDisappear() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loginBtn));
        return this;
    }

    public CheckoutPage login(User user) {
        return clickHereToLoginLink()
            .enterUserName(user.getUsername())
            .enterPassword(user.getPassword())
            .clickLoginBtn()
            .waitForLoginBtnToDisappear();
    }

    public CheckoutPage selectDirectBankTransfer() {
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(directBankTransferRadioBtn));
        if (!e.isSelected()) {
            e.click();
        }
        return this;
    }

    public String getProductName() {
        wait.until(ExpectedConditions.presenceOfElementLocated(productName));
        return driver.findElement(productName).getText();
    }

}

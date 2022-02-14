package com.automation.pom.pages;

import com.automation.pom.base.BasePage;
import com.automation.pom.objects.BillingAddress;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CheckOutPage extends BasePage {

    private static final Logger LOGGER = LogManager.getLogger();

    private By firstNameField = By.xpath("//input[@name='billing_first_name']");
    private By lastNameField = By.xpath("//input[@name='billing_last_name']");
    private By addressLineOneField = By.xpath("//input[@id='billing_address_1']");
    private By city = By.xpath("//input[@id='billing_city']");
    private By postcode = By.xpath("//input[@id='billing_postcode']");
    private By email = By.xpath("//input[@id='billing_email']");
    private By placeOrderButton = By.xpath("//button[@id='place_order']");
    private By countryDropDown = By.id("billing_country");
    private By stateDropDown = By.id("billing_state");
    private By stateDropDownInputField = By.xpath("//input[@class='select2-search__field']");
    private By overlay = By.cssSelector(".blockUI.blockOverlay");
    private By directBankTransferRadioButton = By.id("payment_method_bacs");

    private By successNotice = By.xpath(
            "//p[@class='woocommerce-notice woocommerce-notice--success woocommerce-thankyou-order-received']");

    public CheckOutPage(WebDriver driver) {
        super(driver);
    }

    public CheckOutPage enterFirstName(String text) {
        waitUntilElementToBeClickable(firstNameField);
        driver.findElement(firstNameField).clear();
        driver.findElement(firstNameField).sendKeys(text);
        return this;
    }

    public CheckOutPage enterLastName(String text) {
        driver.findElement(lastNameField).clear();
        driver.findElement(lastNameField).sendKeys(text);
        return this;
    }

    public CheckOutPage enterAddressLineFieldOne(String text) {
        driver.findElement(addressLineOneField).clear();
        driver.findElement(addressLineOneField).sendKeys(text);
        return this;
    }

    public CheckOutPage enterCity(String text) {
        driver.findElement(city).clear();
        driver.findElement(city).sendKeys(text);
        return this;
    }

    public CheckOutPage enterPostcode(String text) {
        driver.findElement(postcode).clear();
        driver.findElement(postcode).sendKeys(text);
        return this;
    }

    public CheckOutPage enterEmail(String text) {
        driver.findElement(email).clear();
        driver.findElement(email).sendKeys(text);
        return this;
    }

    public CheckOutPage placeOrder() {
        waitUntilOverlaysDisappears(overlay);
        driver.findElement(placeOrderButton).click();
        LOGGER.debug("Placing an order");
        return this;
    }

    public CheckOutPage selectCountry(String countryValue) {
        Select select = new Select(driver.findElement(countryDropDown));
        select.selectByValue(countryValue);
        return this;
    }

    public CheckOutPage selectState(String stateValue) {
        Select select = new Select(driver.findElement(countryDropDown));
        select.selectByValue(stateValue);
        return this;
    }

    public String getNotice() {
        waitUntilElementToBeVisible(successNotice);
        return driver.findElement(successNotice).getText();
    }

    public CheckOutPage selectDirectBankTransfer() {
        LOGGER.debug("Selecting direct bank transfer");
        waitUntilElementToBeClickable(directBankTransferRadioButton);
        WebElement radioButton = driver.findElement(directBankTransferRadioButton);
        if (!radioButton.isSelected()) {
            radioButton.click();
        }
        return this;
    }

    public CheckOutPage setBillingAddressWithDefaultState(BillingAddress billingAddress) {
        enterFirstName(billingAddress.getFirstName());
        enterLastName(billingAddress.getLastName());
        selectCountry(billingAddress.getCountry());
        enterAddressLineFieldOne(billingAddress.getAddressLineOne());
        enterCity(billingAddress.getCity());
        selectState(billingAddress.getState());
        enterPostcode(billingAddress.getPostcode());
        enterEmail(billingAddress.getEmail());
        return this;
    }

}

package com.automation.pom.pages;

import com.automation.pom.base.BasePage;
import com.automation.pom.objects.BillingAddress;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class CheckOutPage extends BasePage {

    private static Logger LOGGER = LogManager.getLogger();

    private By firstNameField = By.xpath("//input[@name='billing_first_name']");
    private By lastNameField = By.xpath("//input[@name='billing_last_name']");
    private By addressLineOneField = By.xpath("//input[@id='billing_address_1']");
    private By city = By.xpath("//input[@id='billing_city']");
    private By postcode = By.xpath("//input[@id='billing_postcode']");
    private By email = By.xpath("//input[@id='billing_email']");
    private By placeOrderButton = By.xpath("//button[@id='place_order']");
    private By stateDropDownButton = By.xpath("//span[@id='select2-billing_state-container']");
    private By stateDropDownInputField = By.xpath("//input[@class='select2-search__field']");

    private By successNotice = By.xpath(
        "//p[@class='woocommerce-notice woocommerce-notice--success woocommerce-thankyou-order-received']");

    public CheckOutPage(WebDriver driver) {
        super(driver);
    }

    public CheckOutPage setBillingAddressWithDefaultState(BillingAddress billingAddress) {
        enterFirstName(billingAddress.getFirstName());
        enterLastName(billingAddress.getLastName());
        enterAddressLineFieldOne(billingAddress.getAddressLineOne());
        enterCity(billingAddress.getCity());
        enterPostcode(billingAddress.getPostcode());
        enterEmail(billingAddress.getEmail());
        placeOrder();
        return this;
    }

    public CheckOutPage enterFirstName(String text) {
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
        driver.findElement(placeOrderButton).click();
        return this;
    }

    public CheckOutPage enterState(String text) {
        driver.findElement(stateDropDownButton).click();
        driver.findElement(stateDropDownInputField).clear();
        driver.findElement(stateDropDownInputField).sendKeys(text);
        driver.findElement(stateDropDownInputField).sendKeys(Keys.ENTER);
        return this;
    }

    public String getNotice() {
        return driver.findElement(successNotice).getText();
    }

}

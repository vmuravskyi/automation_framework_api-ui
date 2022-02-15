package com.automation.pom.base;

import java.time.Duration;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

    private static final Logger LOGGER = LogManager.getLogger();
    protected WebDriverWait wait;
    protected WebDriver driver;
    protected Wait<WebDriver> fluentWait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        fluentWait = new FluentWait<WebDriver>(driver);
//        PageFactory.initElements(driver, this); In case of using PageFactory
    }

    protected void navigate(String endPoint) {
        LOGGER.debug("Navigate to https://askomdch.com" + endPoint);
        driver.navigate().to("https://askomdch.com" + endPoint);
    }

    protected void waitUntilOverlaysDisappears(By overlay) {
        List<WebElement> elements = driver.findElements(overlay);
        LOGGER.debug("Overlays size = {}", elements.size());
        if (!elements.isEmpty()) {
            wait.until(ExpectedConditions.invisibilityOfAllElements(elements));
            LOGGER.debug("Waiting until overlays disappear");
        }
    }

    protected void fluentWaitIgnoringStaleElementReferenceException(By by) {
        fluentWait = new FluentWait<WebDriver>(driver)
            .withTimeout(Duration.ofSeconds(5L))
            .pollingEvery(Duration.ofMillis(300L))
            .ignoring(StaleElementReferenceException.class);
    }

    protected void waitUntilElementToBeClickable(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    protected void waitUntilElementToBeClickable(WebElement webElement) {
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    protected void waitUntilElementToBeVisible(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    protected void waitUntilElementToBeVisible(WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    protected void waitUntilPresenceOfElementLocatedBy(By by) {
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    protected void waitUntilPresenceOfAllElementsLocatedBy(By by) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    protected void resetImplicitWait() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
    }

    protected void restoreImplicitWait() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

}

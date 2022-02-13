package com.automation.pom.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {

    private static final Logger LOGGER = LogManager.getLogger();
    protected WebDriverWait wait;
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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

//     Fluent wait examples
//     Waiting 30 seconds for an element to be present on the page, checking
//     for its presence once every 5 seconds.
//    Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
//            .withTimeout(Duration.ofSeconds(30L))
//            .pollingEvery(Duration.ofSeconds(1L))
//            .ignoring(NoSuchElementException.class);
//
//    WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
//        public WebElement apply(WebDriver driver) {
//            return driver.findElement(By.id("foo"));
//        }
//    });

    protected void waitUntilElementToBeClickable(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    protected void waitUntilElementToBeClickable(WebElement webElement) {
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    protected void waitUntilElementToBeVisible(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
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

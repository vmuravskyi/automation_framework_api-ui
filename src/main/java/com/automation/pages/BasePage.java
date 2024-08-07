package com.automation.pages;

import com.automation.utils.ConfigLoader;

import java.time.Duration;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    private final static Logger LOGGER = LogManager.getLogger();
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void load(String endPoint) {
        driver.navigate().to(ConfigLoader.getInstance().getBaseUrl() + endPoint);
    }

    public BasePage refresh() {
        driver.findElement(By.xpath("//body")).sendKeys(Keys.F5);
        return this;
    }

    public void waitForOverlaysToDisappear(By overlay) {
        List<WebElement> overlays = driver.findElements(overlay);
        LOGGER.info("Overlay size {}", overlays.size());
        if (overlays.size() > 0) {
            wait.until(ExpectedConditions.invisibilityOfAllElements(overlays));
            LOGGER.info("Overlays invisible");
        } else {
            LOGGER.info("Overlays have not been found");
        }
    }

}

package com.selenium.pom.base;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideWait;
import com.codeborne.selenide.WebDriverRunner;
import com.selenium.pom.utils.ConfigLoader;
import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    private final static Logger LOGGER = LogManager.getLogger();

    public BasePage() {
    }

    protected void load(String endPoint) {
        Selenide.open(ConfigLoader.getInstance().getBaseUrl() + endPoint);
    }

    protected BasePage refresh() {
        Selenide.refresh();
        return this;
    }

    protected void waitUntilPageDownload() {
        new SelenideWait(WebDriverRunner.getWebDriver(), 15000, 300).until(
            driver ->
                ((JavascriptExecutor) driver)
                    .executeScript("return document.readyState")
                    .equals("complete")
        );
    }

    protected void waitForOverlaysToDisappear(ElementsCollection overlay) {
        ElementsCollection overlays = Selenide.elements(overlay);
        LOGGER.info("Overlay size {}", overlays.size());
        // TODO refactor according to deprecated method
        if (overlays.size() > 0) {
            overlays.shouldBe(CollectionCondition.allMatch("Each element is not visible",
                webElement -> new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(5))
                    .until(ExpectedConditions.invisibilityOf(webElement))));
            LOGGER.info("Overlays invisible");
        } else {
            LOGGER.info("Overlays have not been found");
        }
    }

}

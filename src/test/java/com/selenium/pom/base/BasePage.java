package com.selenium.pom.base;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.selenium.pom.utils.ConfigLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    private final static Logger LOGGER = LogManager.getLogger();

    public BasePage() {
    }

    public void load(String endPoint) {
        Selenide.open(ConfigLoader.getInstance().getBaseUrl() + endPoint);
    }

    public BasePage refresh() {
        Selenide.refresh();
        return this;
    }

    public void waitForOverlaysToDisappear(ElementsCollection overlay) {
        ElementsCollection overlays = Selenide.elements(overlay);
        LOGGER.info("Overlay size {}", overlays.size());
        // TODO refactor according to deprecated method
        if (overlays.size() > 0) {
            overlays.shouldBe(CollectionCondition.allMatch("Each element is not visible",
                webElement -> new WebDriverWait(Selenide.webdriver().driver().getWebDriver(), 5000)
                    .until(ExpectedConditions.invisibilityOf(webElement))));
            LOGGER.info("Overlays invisible");
        } else {
            LOGGER.info("Overlays have not been found");
        }
    }

}

package com.selenium.pom.factory;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;

public class FirefoxDriverManager implements DriverManager {

    @Override
    public void createDriver() {
        Configuration.browser = Browsers.FIREFOX;
        Configuration.browserSize = "1920x1080";
        Selenide.open();
    }

}

package com.selenium.pom.factory;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;

public class ChromeDriverManager implements DriverManager {

    @Override
    public void createDriver() {
        Configuration.browser = Browsers.CHROME;
        Configuration.browserSize = "1920x1080";
        Selenide.open();
    }

}

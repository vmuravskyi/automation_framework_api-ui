package com.selenium.pom.factory;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class ChromeDriverManager implements DriverManager {

    @Override
    public void createDriver() {
        Configuration.browser = Browsers.CHROME;
        Configuration.browserSize = "1920x1080";
        Selenide.open();
    }

    @Override
    public void createRemoteDriver(String completeUrl, MutableCapabilities capabilities) throws MalformedURLException {
        WebDriverRunner.setWebDriver(new RemoteWebDriver(new URL(completeUrl), capabilities));
        Selenide.open();
    }
}

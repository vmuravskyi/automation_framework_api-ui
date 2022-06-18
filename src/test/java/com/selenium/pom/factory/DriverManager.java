package com.selenium.pom.factory;

import org.openqa.selenium.MutableCapabilities;

import java.net.MalformedURLException;

public interface DriverManager {

    void createDriver();

    void createRemoteDriver(String completeUrl, MutableCapabilities capabilities) throws MalformedURLException;

}

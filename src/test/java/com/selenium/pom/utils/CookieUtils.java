package com.selenium.pom.utils;

import io.restassured.http.Cookies;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Cookie;

public class CookieUtils {

    private final static Logger LOGGER = LogManager.getLogger();

    /**
     * Use this method to convert restAssure cookies to list of selenium cookies
     *
     * @param cookies
     * @return List of 'Cookie' for Selenium
     */
    public List<Cookie> covertRestAssuredCookiesToSeleniumCookies(Cookies cookies) {
        List<io.restassured.http.Cookie> restAssuredCookieList = cookies.asList();
        List<Cookie> seleniumCookies = new ArrayList<>();
        LOGGER.info("Converting RestAssured cookies into Selenium Cookies");
        for (io.restassured.http.Cookie cookie : restAssuredCookieList) {
            seleniumCookies.add(new Cookie(
                    cookie.getName(),
                    cookie.getValue(),
                    cookie.getDomain(),
                    cookie.getPath(),
                    cookie.getExpiryDate(),
                    cookie.isSecured(),
                    cookie.isHttpOnly(),
                    cookie.getSameSite()
                )
            );
        }
        return seleniumCookies;
    }

}

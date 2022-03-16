package com.selenium.pom.utils;

import com.github.javafaker.Faker;

public class FakerUtils {

    public Long generateRandomNumber() {
        return new Faker().number().randomNumber(9, false);
    }

}

package com.selenium.pom.utils;

import com.selenium.pom.objects.User;

public class UserUtils {

    public User getRandomAutomationUser() {
        String userName = "AutomationUser_" + new FakerUtils().generateRandomNumber();
        String password = "AutomationUser_";
        String email = userName + "@gmail.com";

        return new User()
            .setUsername(userName)
            .setPassword(password)
            .setEmail(email);
    }

}

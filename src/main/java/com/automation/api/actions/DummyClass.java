package com.automation.api.actions;

import com.automation.objects.Product;
import com.automation.objects.User;
import com.automation.utils.FakerUtils;
import io.restassured.response.Response;

public class DummyClass {

    public static void main(String[] args) {
        String userName = "AutomationUser_" + new FakerUtils().generateRandomNumber();
        String password = "AutomationUser_";
        String email = userName + "@gmail.com";

        User user = new User()
                .setUsername(userName)
                .setPassword(password)
                .setEmail(email);
        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);
        System.out.println("Register cookies: " + signUpApi.getCookies());

        CartApi cartApi = new CartApi(signUpApi.getCookies());
        Response response = cartApi.addToCart(new Product(1215), 1);
        System.out.println(cartApi.getCookies());
    }

}

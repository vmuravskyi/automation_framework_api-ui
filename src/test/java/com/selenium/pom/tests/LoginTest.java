package com.selenium.pom.tests;

import com.selenium.pom.api.actions.CartApi;
import com.selenium.pom.api.actions.SignUpApi;
import com.selenium.pom.base.BaseTest;
import com.selenium.pom.objects.Product;
import com.selenium.pom.objects.User;
import com.selenium.pom.pages.CheckoutPage;
import com.selenium.pom.utils.FakerUtils;
import org.assertj.core.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void loginDuringCheckout() throws Exception {
        String username = "user" + new FakerUtils().generateRandomNumber();
        User user = new User()
            .setUsername(username)
            .setPassword("user")
            .setEmail(username + "@askomdch.com");

        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);
        CartApi cartApi = new CartApi();
        Product product = new Product(1215);
        cartApi.addToCart(product.getId(), 1);

        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        injectCookiesToBrowser(cartApi.getCookies());
        checkoutPage
            .load()
            .clickHereToLoginLink()
            .login(user);

        Assertions.assertThat(checkoutPage.getProductName())
            .contains(product.getName());
    }
}

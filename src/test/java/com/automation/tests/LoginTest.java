package com.automation.tests;

import com.automation.objects.Product;
import com.automation.objects.User;
import com.automation.utils.UserUtils;
import com.automation.api.actions.CartApi;
import com.automation.api.actions.SignUpApi;
import com.automation.pages.CheckoutPage;
import io.restassured.http.Cookies;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private User randomUser = new UserUtils().getRandomAutomationUser();
    private Product product = new Product(1215);
    private Cookies cookies;

    @BeforeMethod
    public void registerUserAndAddProductToCart() {
        randomUser = new UserUtils().getRandomAutomationUser();

        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(randomUser);

        CartApi cartApi = new CartApi();

        cartApi.addToCart(product, 1);
        cookies = cartApi.getCookies();
    }

    @Test
    public void loginDuringCheckout() {
        // this will return to cart page as there's no cookies in the browser
        CheckoutPage checkoutPage = new CheckoutPage(getDriver())
                .load();

        injectCookiesToBrowser(cookies);

        // this time it will load the page as we injected cookies
        checkoutPage.load();
        checkoutPage.login(randomUser);

        // verify that the product name in the user's cart is same as the product added through API
        Assertions.assertThat(checkoutPage.getProductName())
                .contains(product.getName());
    }

}

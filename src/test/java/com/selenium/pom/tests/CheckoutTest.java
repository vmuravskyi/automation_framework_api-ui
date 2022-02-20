package com.selenium.pom.tests;

import com.selenium.pom.api.actions.CartApi;
import com.selenium.pom.api.actions.SignUpApi;
import com.selenium.pom.base.BaseTest;
import com.selenium.pom.objects.BillingAddress;
import com.selenium.pom.objects.Product;
import com.selenium.pom.objects.User;
import com.selenium.pom.pages.CheckoutPage;
import com.selenium.pom.utils.JacksonUtils;
import com.selenium.pom.utils.UserUtils;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    private final String expectedMessage = "Thank you. Your order has been received.";
    private final Product product = new Product(1215);
    private final BillingAddress billingAddress = JacksonUtils
            .deserializeJsonToObject("myBillingAddress", BillingAddress.class);
    private final User user = new UserUtils().getRandomAutomationUser();

    @Test
    public void guestCheckoutUserDirectBankTransfer() {

        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();

        CartApi cartApi = new CartApi();
        cartApi.addToCart(product, 1);
        injectCookiesToBrowser(cartApi.getCookies());

        checkoutPage.load()
                .setBillingAddress(billingAddress)
                .selectDirectBankTransfer()
                .placeOrder();

        Assertions.assertThat(checkoutPage.getNotice())
                .isEqualTo(expectedMessage);
    }

    @Test
    public void loginAndCheckoutUsingDirectBankTransfer() throws InterruptedException {
        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);
        CartApi cartApi = new CartApi(signUpApi.getCookies());
        cartApi.addToCart(product, 1);

        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        Thread.sleep(5000);
        injectCookiesToBrowser(signUpApi.getCookies());
        checkoutPage
                .setBillingAddress(billingAddress)
                .selectDirectBankTransfer()
                .placeOrder();

        Assertions.assertThat(checkoutPage.getNotice())
                .isEqualTo(expectedMessage);


    }
}

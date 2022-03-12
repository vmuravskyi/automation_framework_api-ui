package com.selenium.pom.tests;

import com.selenium.pom.api.actions.CartApi;
import com.selenium.pom.api.actions.SignUpApi;
import com.selenium.pom.base.BaseTest;
import com.selenium.pom.objects.BillingAddressDto;
import com.selenium.pom.objects.Product;
import com.selenium.pom.objects.User;
import com.selenium.pom.pages.CheckoutPage;
import com.selenium.pom.utils.JacksonUtils;
import com.selenium.pom.utils.UserUtils;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    private String expectedMessage = "Thank you. Your order has been received.";
    private Product product = new Product(1215);
    private BillingAddressDto billingAddressDto = JacksonUtils
            .deserializeJsonToObject("myBillingAddress", BillingAddressDto.class);
    private User user = new UserUtils().getRandomAutomationUser();

    @Test
    public void guestCheckoutUserDirectBankTransfer() {

        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();

        CartApi cartApi = new CartApi();
        cartApi.addToCart(product, 1);
        injectCookiesToBrowser(cartApi.getCookies());

        checkoutPage.load()
                .setBillingAddress(billingAddressDto)
                .selectDirectBankTransfer()
                .placeOrder();

        Assertions.assertThat(checkoutPage.getNotice())
                .isEqualTo(expectedMessage);
    }

    @Test
    public void loginAndCheckoutUsingDirectBankTransfer() {
        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);
        CartApi cartApi = new CartApi(signUpApi.getCookies());
        cartApi.addToCart(product, 1);

        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        injectCookiesToBrowser(cartApi.getCookies());
        checkoutPage
                .load()
                .setBillingAddress(billingAddressDto)
                .selectDirectBankTransfer()
                .placeOrder();

        Assertions.assertThat(checkoutPage.getNotice())
                .isEqualTo(expectedMessage);


    }
}

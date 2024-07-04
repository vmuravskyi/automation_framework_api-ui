package com.automation.tests;

import com.automation.objects.BillingAddressDto;
import com.automation.objects.Product;
import com.automation.objects.User;
import com.automation.utils.JacksonUtils;
import com.automation.utils.UserUtils;
import com.automation.api.actions.CartApi;
import com.automation.api.actions.SignUpApi;
import com.automation.pages.CheckoutPage;
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

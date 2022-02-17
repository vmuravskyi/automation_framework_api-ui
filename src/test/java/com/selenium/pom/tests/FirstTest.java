package com.selenium.pom.tests;

import com.selenium.pom.base.BaseTest;
import com.selenium.pom.objects.BillingAddress;
import com.selenium.pom.objects.Product;
import com.selenium.pom.objects.User;
import com.selenium.pom.pages.CartPage;
import com.selenium.pom.pages.CheckoutPage;
import com.selenium.pom.pages.HomePage;
import com.selenium.pom.pages.StorePage;
import com.selenium.pom.utils.ConfigLoader;
import com.selenium.pom.utils.JacksonUtils;
import java.io.IOException;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class FirstTest extends BaseTest {

    private String expectedMessage = "Thank you. Your order has been received.";
    private String searchFor = "Blue";

    @Test
    public void guestCheckoutUsingDirectBankTransfer() throws IOException, InterruptedException {

        BillingAddress billingAddress = JacksonUtils.deserializeJsonToObject("myBillingAddress",
            BillingAddress.class);
        Product product = new Product(1215);

        StorePage storePage = new HomePage(getDriver()).
            load().
            getMyHeader().navigateToStoreUsingMenu()
            .search(searchFor);
        Assertions.assertThat(storePage.getTitle())
            .contains(searchFor);

        storePage.getProductThumbnail().clickAddToCartBtn(product.getName());
        CartPage cartPage = storePage.getProductThumbnail().clickViewCart();
        Assertions.assertThat(cartPage.getProductName())
            .isEqualTo(product.getName());

        CheckoutPage checkoutPage = cartPage.
            checkout().
            setBillingAddress(billingAddress).
            selectDirectBankTransfer().
            placeOrder();
        Assertions.assertThat(checkoutPage.getNotice())
            .isEqualTo(expectedMessage);
    }

    @Test
    public void loginAndCheckoutUsingDirectBankTransfer() throws IOException, InterruptedException {
        BillingAddress billingAddress = JacksonUtils.deserializeJsonToObject("myBillingAddress", BillingAddress.class);
        Product product = new Product(1215);
        User user = new User(ConfigLoader.getInstance().getUsername(),
            ConfigLoader.getInstance().getPassword());

        StorePage storePage = new HomePage(getDriver()).
            load().getMyHeader().
            navigateToStoreUsingMenu().
            search(searchFor);
        Assertions.assertThat(storePage.getTitle())
            .contains(searchFor);

        storePage.getProductThumbnail().clickAddToCartBtn(product.getName());
        CartPage cartPage = storePage.getProductThumbnail().clickViewCart();
        Assertions.assertThat(cartPage.getProductName())
            .isEqualTo(product.getName());

        CheckoutPage checkoutPage = cartPage.checkout();
        checkoutPage.clickHereToLoginLink();

        checkoutPage.
            login(user).
            setBillingAddress(billingAddress).
            selectDirectBankTransfer().
            placeOrder();
        Assertions.assertThat(checkoutPage.getNotice())
            .isEqualTo(expectedMessage);
    }
}

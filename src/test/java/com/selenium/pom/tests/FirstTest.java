package com.selenium.pom.tests;

import com.selenium.pom.base.BaseTest;
import com.selenium.pom.objects.BillingAddressDto;
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

    private final String expectedMessage = "Thank you. Your order has been received.";
    private final String searchFor = "Blue";
    private final BillingAddressDto billingAddress = JacksonUtils.deserializeJsonFileToJsonObject("myBillingAddress",
        BillingAddressDto.class);
    private final Product product = new Product(1215);
    private final User user = new User(ConfigLoader.getInstance().getUsername(),
        ConfigLoader.getInstance().getPassword());

    @Test
    public void guestCheckoutUsingDirectBankTransfer() throws IOException, InterruptedException {
        StorePage storePage = new HomePage(getDriver())
            .load()
            .getHeader()
            .navigateToStoreUsingMenu()
            .search(searchFor);
        Assertions.assertThat(storePage.getTitleAfterSearch())
            .contains(searchFor);

        storePage.getProductThumbnail().clickAddToCartBtn(product.getName());
        CartPage cartPage = storePage.getProductThumbnail().clickViewCart();
        Assertions.assertThat(cartPage.getProductName())
            .isEqualTo(product.getName());

        CheckoutPage checkoutPage = cartPage
            .checkout()
            .setBillingAddress(billingAddress)
            .selectDirectBankTransfer()
            .placeOrder();
        Assertions.assertThat(checkoutPage.getNotice())
            .isEqualTo(expectedMessage);
    }

    @Test
    public void loginAndCheckoutUsingDirectBankTransfer() throws IOException, InterruptedException {
        StorePage storePage = new HomePage(getDriver())
            .load()
            .getHeader()
            .navigateToStoreUsingMenu()
            .search(searchFor);
        Assertions.assertThat(storePage.getTitleAfterSearch())
            .contains(searchFor);

        storePage.getProductThumbnail().clickAddToCartBtn(product.getName());
        CartPage cartPage = storePage
            .getProductThumbnail()
            .clickViewCart();
        Assertions.assertThat(cartPage.getProductName())
            .isEqualTo(product.getName());

        CheckoutPage checkoutPage = cartPage.checkout();
        checkoutPage.clickHereToLoginLink();

        checkoutPage
            .login(user)
            .setBillingAddress(billingAddress)
            .selectDirectBankTransfer()
            .placeOrder();
        Assertions.assertThat(checkoutPage.getNotice())
            .isEqualTo(expectedMessage);
    }

}
